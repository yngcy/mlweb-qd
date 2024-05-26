package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsTrajectory;
import cn.edu.swust.qd.scms.model.form.TrajectoryForm;
import cn.edu.swust.qd.scms.model.vo.TrajectoryPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 弹道信息转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsTrajectoryConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    TrajectoryPageVO entity2VO(ScmsTrajectory entity);

    Page<TrajectoryPageVO> entity2VO(Page<ScmsTrajectory> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsTrajectory entity);

    List<Option> entities2Options(List<ScmsTrajectory> entities);

    ScmsTrajectory form2Entity(TrajectoryForm trajectoryForm);

    TrajectoryForm entity2Form(ScmsTrajectory entity);
}
