package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsCoordinateType;
import cn.edu.swust.qd.scms.model.form.CoordinateTypeForm;
import cn.edu.swust.qd.scms.model.vo.CoordinateTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 坐标系类别转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsCoordinateTypeConverter {

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsCoordinateType entity);

    List<Option> entities2Options(List<ScmsCoordinateType> entities);

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    CoordinateTypePageVO entity2VO(ScmsCoordinateType entity);

    Page<CoordinateTypePageVO> entity2VO(Page<ScmsCoordinateType> scmsCoordinateTypePage);

    ScmsCoordinateType form2Entity(CoordinateTypeForm coordinateTypeForm);

    CoordinateTypeForm entity2Form(ScmsCoordinateType entity);
}
