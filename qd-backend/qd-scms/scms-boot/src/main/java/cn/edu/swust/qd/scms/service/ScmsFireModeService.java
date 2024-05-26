package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsFireMode;
import cn.edu.swust.qd.scms.model.form.FireModeForm;
import cn.edu.swust.qd.scms.model.query.FireModePageQuery;
import cn.edu.swust.qd.scms.model.vo.FireModePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_fire_mode(点火方式表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsFireModeService extends IService<ScmsFireMode> {

    /**
     * 获取点火方式分页列表
     *
     * @param queryParams
     * @return
     */
    Page<FireModePageVO> getFireModePage(FireModePageQuery queryParams);

    /**
     * 获取点火方式下拉列表
     *
     * @return
     */
    List<Option> getFireModeOptions();

    /**
     * 保存点火方式
     *
     * @param fireModeForm
     * @return
     */
    boolean saveFireMode(FireModeForm fireModeForm);

    /**
     * 删除点火方式
     *
     * @param ids
     * @return
     */
    boolean deleteFireModes(String ids);

    /**
     * 获取点火方式表单
     *
     * @param fireModeId
     * @return
     */
    FireModeForm getFireModeForm(Long fireModeId);
}
