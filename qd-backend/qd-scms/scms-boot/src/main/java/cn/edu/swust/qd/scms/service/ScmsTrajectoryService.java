package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsTrajectory;
import cn.edu.swust.qd.scms.model.form.TrajectoryForm;
import cn.edu.swust.qd.scms.model.query.TrajectoryPageQuery;
import cn.edu.swust.qd.scms.model.vo.TrajectoryPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_trajectory(弹道信息表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsTrajectoryService extends IService<ScmsTrajectory> {

    /**
     * 获取弹道信息分页列表
     *
     * @param queryParams
     * @return
     */
    Page<TrajectoryPageVO> getTrajectoryPage(TrajectoryPageQuery queryParams);

    /**
     * 获取弹道信息下拉列表
     *
     * @return
     */
    List<Option> getTrajectoryOptions();

    /**
     * 保存弹道信息
     *
     * @param trajectoryForm
     * @return
     */
    boolean saveTrajectory(TrajectoryForm trajectoryForm);

    /**
     * 获取弹道信息表单
     *
     * @param trajectoryId
     * @return
     */
    TrajectoryForm getTrajectoryForm(Long trajectoryId);

    /**
     * 删除弹道信息
     *
     * @param ids
     * @return
     */
    boolean deleteTrajectories(String ids);

}
