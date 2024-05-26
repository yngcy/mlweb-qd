package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.scms.model.entity.ScmsAirfoilCoordinate;
import cn.edu.swust.qd.scms.model.form.AirfoilCoordinateForm;
import cn.edu.swust.qd.scms.model.query.AirfoilCoordinatePageQuery;
import cn.edu.swust.qd.scms.model.vo.AirfoilCoordinatePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 25055
 * @description 针对表【scms_airfoil_coordinate(翼型坐标表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsAirfoilCoordinateService extends IService<ScmsAirfoilCoordinate> {

    /**
     * 翼型坐标分页列表
     *
     * @param queryParams
     * @return
     */
    Page<AirfoilCoordinatePageVO> getAirfoilCoordinatePage(AirfoilCoordinatePageQuery queryParams);

    /**
     * 保存翼型坐标信息
     *
     * @param airfoilCoordinateForm
     * @return
     */
    boolean saveAirfoilCoordinate(AirfoilCoordinateForm airfoilCoordinateForm);

    /**
     * 获取翼型坐标表单
     *
     * @param airfoilCoordinateId
     * @return
     */
    AirfoilCoordinateForm getAirfoilCoordinateForm(Long airfoilCoordinateId);

    /**
     * 删除翼型坐标
     *
     * @param ids
     * @return
     */
    boolean deleteAirfoilCoordinates(String ids);

    /**
     * 判断翼型信息下是否有翼型坐标
     *
     * @param airfoilId 翼型信息ID
     * @return 不为空返回 true，否则 false
     */
    boolean hasAirfoilCoordinate(Long airfoilId);
}
