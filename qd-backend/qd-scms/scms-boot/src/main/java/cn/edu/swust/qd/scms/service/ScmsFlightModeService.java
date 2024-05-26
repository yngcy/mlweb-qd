package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsFlightMode;
import cn.edu.swust.qd.scms.model.form.FlightModeForm;
import cn.edu.swust.qd.scms.model.query.FlightModePageQuery;
import cn.edu.swust.qd.scms.model.vo.FlightModePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_flight_mode(飞行试验方式表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsFlightModeService extends IService<ScmsFlightMode> {

    /**
     * 获取飞行试验方式分页列表
     *
     * @param queryParams
     * @return
     */
    Page<FlightModePageVO> getFlightModePage(FlightModePageQuery queryParams);

    /**
     * 获取飞行试验方式下拉列表
     *
     * @return
     */
    List<Option> getFlightModeOptions();

    /**
     * 保存飞行试验方式
     *
     * @param flightModeForm
     * @return
     */
    boolean saveFlightMode(FlightModeForm flightModeForm);

    /**
     * 获取飞行试验方式表单
     *
     * @param flightModeId
     * @return
     */
    FlightModeForm getFlightModeForm(Long flightModeId);

    /**
     * 删除飞行试验方式
     *
     * @param ids
     * @return
     */
    boolean deleteFlightModes(String ids);
}
