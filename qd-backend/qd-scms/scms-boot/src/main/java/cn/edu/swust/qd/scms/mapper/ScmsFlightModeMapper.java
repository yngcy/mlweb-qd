package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.entity.ScmsFlightMode;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_flight_mode(飞行试验方式表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsFlightMode
 */
@Mapper
public interface ScmsFlightModeMapper extends BaseMapper<ScmsFlightMode> {

    @DataPermission(clIdColumnName = "security")
    @Override
    List<ScmsFlightMode> selectList(Wrapper<ScmsFlightMode> queryWrapper);
}




