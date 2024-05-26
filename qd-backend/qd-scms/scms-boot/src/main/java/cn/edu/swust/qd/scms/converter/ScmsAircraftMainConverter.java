package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.scms.model.bo.AircraftMainBO;
import cn.edu.swust.qd.scms.model.entity.ScmsAircraftMain;
import cn.edu.swust.qd.scms.model.form.AircraftMainForm;
import cn.edu.swust.qd.scms.model.vo.AircraftMainPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 飞行器主信息转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsAircraftMainConverter {


    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    AircraftMainPageVO entity2VO(ScmsAircraftMain entity);

    Page<AircraftMainPageVO> entity2VO(Page<ScmsAircraftMain> entity);

    ScmsAircraftMain form2Entity(AircraftMainForm form);

    AircraftMainForm entity2Form(ScmsAircraftMain entity);

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(bo.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    AircraftMainPageVO bo2VO(AircraftMainBO bo);

    Page<AircraftMainPageVO> bo2VO(Page<AircraftMainBO> bo);
}
