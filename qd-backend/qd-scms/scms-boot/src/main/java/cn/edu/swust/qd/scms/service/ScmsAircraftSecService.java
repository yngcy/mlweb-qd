package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.scms.model.entity.ScmsAircraftSec;
import cn.edu.swust.qd.scms.model.form.AircraftSecForm;
import cn.edu.swust.qd.scms.model.query.AircraftSecPageQuery;
import cn.edu.swust.qd.scms.model.vo.AircraftSecPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 25055
 * @description 针对表【scms_aircraft_sec(飞行器次级信息)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsAircraftSecService extends IService<ScmsAircraftSec> {

    /**
     * 获取飞行器次级信息分页列表
     *
     * @param queryParams
     * @return
     */
    Page<AircraftSecPageVO> getAircraftSecPage(AircraftSecPageQuery queryParams);

    /**
     * 保存飞行器次级信息
     *
     * @param aircraftSecForm
     * @return
     */
    boolean saveAircraftSec(AircraftSecForm aircraftSecForm);

    /**
     * 获取飞行器次级信息表单
     *
     * @param aircraftSecId
     * @return
     */
    AircraftSecForm getAircraftSecForm(Long aircraftSecId);

    /**
     * 删除飞行器次级信息
     *
     * @param ids
     * @return
     */
    boolean deleteAircraftSecs(String ids);

    /**
     * 判断主信息下有次级信息
     *
     * @param amId 飞行器主信息ID
     * @return
     */
    boolean hasSec(Long amId);

    /**
     * 判断飞行器子级是否被次级信息引用
     *
     * @param aircraftSubId 飞行子子级ID
     * @return 引用返回 true，否则 false
     */
    boolean isSubReferenced(Long aircraftSubId);
}
