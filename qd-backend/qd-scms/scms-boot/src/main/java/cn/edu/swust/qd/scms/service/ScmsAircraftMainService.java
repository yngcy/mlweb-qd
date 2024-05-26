package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.scms.model.entity.ScmsAircraftMain;
import cn.edu.swust.qd.scms.model.form.AircraftMainForm;
import cn.edu.swust.qd.scms.model.query.AircraftMainPageQuery;
import cn.edu.swust.qd.scms.model.vo.AircraftMainPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * @author 25055
 * @description 针对表【scms_aircraft_main(飞行器主信息表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsAircraftMainService extends IService<ScmsAircraftMain> {

    /**
     * 获取飞行器主信息分页列表
     *
     * @param queryParams
     * @return
     */
    Page<AircraftMainPageVO> getAircraftMainPage(AircraftMainPageQuery queryParams);

    /**
     * 保存飞行器主信息
     *
     * @param aircraftMainForm
     * @return
     */
    boolean saveAircraftMain(AircraftMainForm aircraftMainForm);

    /**
     * 获取飞行器主信息表单
     *
     * @param aircraftMainId
     * @return
     */
    AircraftMainForm getAircraftMainForm(Long aircraftMainId);

    /**
     * 删除飞行器主信息
     *
     * @param ids
     * @return
     */
    boolean deleteAircraftMains(String ids);

    /**
     * 判断发动机类型是否被引用
     *
     * @param engineTypeId 发动机类型ID
     * @return
     */
    boolean isEngineTypeReferenced(Long engineTypeId);

    /**
     * 判断气动布局类型是否被引用
     *
     * @param vehiTypeId
     * @return
     */
    boolean isVehiTypeReferenced(Long vehiTypeId);
}
