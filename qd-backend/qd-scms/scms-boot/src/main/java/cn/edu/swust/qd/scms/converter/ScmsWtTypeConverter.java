package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsWtType;
import cn.edu.swust.qd.scms.model.form.WtTypeForm;
import cn.edu.swust.qd.scms.model.vo.WtTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 风洞类型转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsWtTypeConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    WtTypePageVO entity2VO(ScmsWtType entity);

    Page<WtTypePageVO> entity2VO(Page<ScmsWtType> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsWtType entity);

    List<Option> entities2Options(List<ScmsWtType> entities);

    ScmsWtType form2Entity(WtTypeForm wtTypeForm);

    WtTypeForm entity2Form(ScmsWtType entity);
}
