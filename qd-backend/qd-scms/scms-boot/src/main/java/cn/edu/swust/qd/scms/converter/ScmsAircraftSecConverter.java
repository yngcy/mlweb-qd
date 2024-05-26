package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.scms.model.entity.ScmsAircraftSec;
import cn.edu.swust.qd.scms.model.form.AircraftSecForm;
import cn.edu.swust.qd.scms.model.vo.AircraftSecPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 飞行器次级信息转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsAircraftSecConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    AircraftSecPageVO entity2VO(ScmsAircraftSec entity);

    Page<AircraftSecPageVO> entity2VO(Page<ScmsAircraftSec> entity);

    ScmsAircraftSec form2Entity(AircraftSecForm aircraftSecForm);

    AircraftSecForm entity2Form(ScmsAircraftSec scmsAircraftSec);
}
