package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.scms.model.bo.AircraftSubBO;
import cn.edu.swust.qd.scms.model.bo.AirfoilCoordinateBO;
import cn.edu.swust.qd.scms.model.entity.ScmsAirfoilCoordinate;
import cn.edu.swust.qd.scms.model.form.AirfoilCoordinateForm;
import cn.edu.swust.qd.scms.model.vo.AirfoilCoordinatePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsAirfoilCoordinateConverter {
    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    AirfoilCoordinatePageVO entity2VO(ScmsAirfoilCoordinate entity);

    Page<AirfoilCoordinatePageVO> entity2VO(Page<ScmsAirfoilCoordinate> entity);

    ScmsAirfoilCoordinate form2Entity(AirfoilCoordinateForm form);

    AirfoilCoordinateForm entity2Form(ScmsAirfoilCoordinate entity);

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(bo.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    AirfoilCoordinatePageVO bo2VO(AircraftSubBO bo);

    Page<AirfoilCoordinatePageVO> bo2VO(Page<AirfoilCoordinateBO> airfoilCoordinatePage);
}
