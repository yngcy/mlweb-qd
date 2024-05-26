package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsAirfoilType;
import cn.edu.swust.qd.scms.model.form.AirfoilTypeForm;
import cn.edu.swust.qd.scms.model.vo.AirfoilTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 翼型类型转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsAirfoilTypeConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    AirfoilTypePageVO entity2VO(ScmsAirfoilType entity);

    Page<AirfoilTypePageVO> entity2VO(Page<ScmsAirfoilType> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsAirfoilType entity);

    List<Option> entities2Options(List<ScmsAirfoilType> entities);

    ScmsAirfoilType form2Entity(AirfoilTypeForm form);

    AirfoilTypeForm entity2Form(ScmsAirfoilType entity);
}
