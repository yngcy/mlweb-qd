package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.bo.AircraftSubBO;
import cn.edu.swust.qd.scms.model.entity.ScmsAircraftSub;
import cn.edu.swust.qd.scms.model.form.AircraftSubForm;
import cn.edu.swust.qd.scms.model.vo.AircraftSubPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 飞行器子级转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsAircraftSubConverter {
    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    AircraftSubPageVO entity2VO(ScmsAircraftSub entity);

    Page<AircraftSubPageVO> entity2VO(Page<ScmsAircraftSub> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsAircraftSub entity);

    List<Option> entities2Options(List<ScmsAircraftSub> aircraftSubList);

    ScmsAircraftSub form2Entity(AircraftSubForm aircraftSubForm);

    AircraftSubForm entity2Form(ScmsAircraftSub aircraftSub);

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(bo.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    AircraftSubPageVO bo2VO(AircraftSubBO bo);

    Page<AircraftSubPageVO> bo2VO(Page<AircraftSubBO> aircraftSubPage);
}
