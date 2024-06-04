package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.bo.AirfoilBO;
import cn.edu.swust.qd.scms.model.entity.ScmsAirfoil;
import cn.edu.swust.qd.scms.model.query.AirfoilPageQuery;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_airfoil(翼型信息表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsAirfoil
 */
@Mapper
public interface ScmsAirfoilMapper extends BaseMapper<ScmsAirfoil> {

    @DataPermission(clIdColumnName = "security")
    @Override
    List<ScmsAirfoil> selectList(@Param(Constants.WRAPPER) Wrapper<ScmsAirfoil> queryWrapper);

    @DataPermission(clAlias = "t1", clIdColumnName = "security", userAlias = "t1")
    Page<AirfoilBO> getAirfoilPage(Page<AirfoilBO> airfoilPageVOPage, AirfoilPageQuery queryParams);
}




