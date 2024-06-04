package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.bo.CompanyBO;
import cn.edu.swust.qd.scms.model.entity.ScmsCompany;
import cn.edu.swust.qd.scms.model.form.CompanyForm;
import cn.edu.swust.qd.scms.model.vo.CompanyPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 研究机构转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsCompanyConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    CompanyPageVO entity2VO(ScmsCompany entity);

    Page<CompanyPageVO> entity2VO(Page<ScmsCompany> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsCompany entity);

    List<Option> entities2Options(List<ScmsCompany> entities);

    ScmsCompany form2Entity(CompanyForm form);

    CompanyForm entity2Form(ScmsCompany entity);

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(companyBO.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    CompanyPageVO bo2VO(CompanyBO companyBO);

    Page<CompanyPageVO> bo2VO(Page<CompanyBO> companyPage);
}
