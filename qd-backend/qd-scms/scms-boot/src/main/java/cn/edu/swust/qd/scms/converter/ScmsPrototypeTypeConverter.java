package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsPrototypeType;
import cn.edu.swust.qd.scms.model.form.PrototypeTypeForm;
import cn.edu.swust.qd.scms.model.vo.PrototypeTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 原型类型转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsPrototypeTypeConverter {

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsPrototypeType entity);

    List<Option> entities2Options(List<ScmsPrototypeType> entities);

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    PrototypeTypePageVO entity2VO(ScmsPrototypeType entity);

    Page<PrototypeTypePageVO> entity2VO(Page<ScmsPrototypeType> entity);

    ScmsPrototypeType form2Entity(PrototypeTypeForm prototypeTypeForm);

    PrototypeTypeForm entity2Form(ScmsPrototypeType entity);
}
