package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsFlightMode;
import cn.edu.swust.qd.scms.model.form.FlightModeForm;
import cn.edu.swust.qd.scms.model.vo.FlightModePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 飞行试验方式转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsFlightModeConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    FlightModePageVO entity2VO(ScmsFlightMode entity);

    Page<FlightModePageVO> entity2VO(Page<ScmsFlightMode> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsFlightMode entity);

    List<Option> entities2Options(List<ScmsFlightMode> entities);

    ScmsFlightMode form2Entity(FlightModeForm flightModeForm);

    FlightModeForm entity2Form(ScmsFlightMode entity);
}
