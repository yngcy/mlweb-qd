package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.entity.ScmsCoordSystemType;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_coord_system_type(坐标系系统类别表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsCoordSystemType
 */
@Mapper
public interface ScmsCoordSystemTypeMapper extends BaseMapper<ScmsCoordSystemType> {

    @DataPermission(clIdColumnName = "security")
    @Override
    List<ScmsCoordSystemType> selectList(@Param(Constants.WRAPPER) Wrapper<ScmsCoordSystemType> queryWrapper);
}




