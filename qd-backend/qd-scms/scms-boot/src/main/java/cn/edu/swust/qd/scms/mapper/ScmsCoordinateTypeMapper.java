package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.entity.ScmsCoordinateType;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_coordinate_type(坐标系类别表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsCoordinateType
 */
@Mapper
public interface ScmsCoordinateTypeMapper extends BaseMapper<ScmsCoordinateType> {
    @DataPermission(clIdColumnName = "security")
    @Override
    List<ScmsCoordinateType> selectList(@Param(Constants.WRAPPER) Wrapper<ScmsCoordinateType> queryWrapper);
}




