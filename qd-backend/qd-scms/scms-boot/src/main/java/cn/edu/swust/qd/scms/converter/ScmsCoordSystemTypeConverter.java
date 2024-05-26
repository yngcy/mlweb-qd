package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsCoordSystemType;
import cn.edu.swust.qd.scms.model.form.CoordSystemTypeForm;
import cn.edu.swust.qd.scms.model.vo.CoordSystemTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 坐标系系统类别转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsCoordSystemTypeConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    CoordSystemTypePageVO entity2VO(ScmsCoordSystemType entity);

    Page<CoordSystemTypePageVO> entity2VO(Page<ScmsCoordSystemType> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsCoordSystemType entity);

    List<Option> entities2Options(List<ScmsCoordSystemType> entities);

    ScmsCoordSystemType form2Entity(CoordSystemTypeForm coordSystemTypeForm);

    CoordSystemTypeForm entity2Form(ScmsCoordSystemType coordSystemType);
}
