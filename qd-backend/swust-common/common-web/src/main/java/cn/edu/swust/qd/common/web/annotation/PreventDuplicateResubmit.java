package cn.edu.swust.qd.common.web.annotation;

import java.lang.annotation.*;

/**
 * 防止重复提交注解
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PreventDuplicateResubmit {

    /**
     * 防止重复提交锁过期时间（单位：秒）
     * <p>
     * 默认5秒内不允许重复提交
     */
    int expire() default 5;
}
