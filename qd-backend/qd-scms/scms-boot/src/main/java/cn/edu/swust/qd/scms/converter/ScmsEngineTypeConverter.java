package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsEngineType;
import cn.edu.swust.qd.scms.model.form.EngineTypeForm;
import cn.edu.swust.qd.scms.model.vo.EngineTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 发动机类型转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsEngineTypeConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    EngineTypePageVO entity2VO(ScmsEngineType entity);

    Page<EngineTypePageVO> entity2VO(Page<ScmsEngineType> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsEngineType entity);

    List<Option> entities2Options(List<ScmsEngineType> entities);

    ScmsEngineType form2Entity(EngineTypeForm engineTypeForm);

    EngineTypeForm entity2Form(ScmsEngineType entity);
}
