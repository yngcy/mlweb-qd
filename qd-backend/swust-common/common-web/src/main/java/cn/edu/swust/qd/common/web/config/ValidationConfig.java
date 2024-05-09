package cn.edu.swust.qd.common.web.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

/**
 * 运行时校验快速失败
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Configuration
public class ValidationConfig {

    /**
     * 自定义 Validation 实现失败
     *
     * @param autowireCapableBeanFactory
     * @return
     */
    @Bean
    public Validator validator(AutowireCapableBeanFactory autowireCapableBeanFactory) {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true) // failFast=true 不校验所有参数，只要出现校验失败情况直接返回，不再进行后续参数校验
                .constraintValidatorFactory(new SpringConstraintValidatorFactory(autowireCapableBeanFactory))
                .buildValidatorFactory();

        return validatorFactory.getValidator();
    }
}
