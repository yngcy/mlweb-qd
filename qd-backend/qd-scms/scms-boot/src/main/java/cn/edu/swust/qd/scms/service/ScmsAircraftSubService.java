package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsAircraftSub;
import cn.edu.swust.qd.scms.model.form.AircraftSubForm;
import cn.edu.swust.qd.scms.model.query.AircraftSubPageQuery;
import cn.edu.swust.qd.scms.model.vo.AircraftSubPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * @author 25055
 * @description 针对表【scms_aircraft_sub(飞行器子级信息表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsAircraftSubService extends IService<ScmsAircraftSub> {

    String getNameById(Long id);

    /**
     * 获取飞行器子级分页列表
     *
     * @param queryParams
     * @return
     */
    Page<AircraftSubPageVO> getAircraftSubPage(AircraftSubPageQuery queryParams);

    /**
     * 获取飞行器子级下拉列表
     *
     * @return
     */
    List<Option> getAircraftSubOptions();

    /**
     * 保存飞行器子级信息
     *
     * @param aircraftSubForm
     * @return
     */
    boolean saveAircraftSub(AircraftSubForm aircraftSubForm);

    /**
     * 获取飞行器子级信息表单
     *
     * @param aircraftSubId
     * @return
     */
    AircraftSubForm getAircraftSubForm(Long aircraftSubId);

    /**
     * 删除飞行器子级信息
     *
     * @param ids
     * @return
     */
    boolean deleteAircraftSubs(String ids);
}
