package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.bo.AirfoilCoordinateBO;
import cn.edu.swust.qd.scms.model.entity.ScmsAirfoilCoordinate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 25055
 * @description 针对表【scms_airfoil_coordinate(翼型坐标表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsAirfoilCoordinate
 */
@Mapper
public interface ScmsAirfoilCoordinateMapper extends BaseMapper<ScmsAirfoilCoordinate> {

    @DataPermission(clAlias = "c", clIdColumnName = "security", userAlias = "c")
    Page<AirfoilCoordinateBO> getAirfoilCoordinatePage(Page<AirfoilCoordinateBO> airfoilCoordinateBOPage);

    @DataPermission(clIdColumnName = "security")
    ScmsAirfoilCoordinate getCoordinateByAirfoilId(Long airfoilId);
}




