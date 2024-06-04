package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.entity.ScmsAirfoilType;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_airfoil_type(翼型类型表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsAirfoilType
 */
@Mapper
public interface ScmsAirfoilTypeMapper extends BaseMapper<ScmsAirfoilType> {

    @DataPermission(clIdColumnName = "security")
    @Override
    List<ScmsAirfoilType> selectList(@Param(Constants.WRAPPER) Wrapper<ScmsAirfoilType> queryWrapper);
}




