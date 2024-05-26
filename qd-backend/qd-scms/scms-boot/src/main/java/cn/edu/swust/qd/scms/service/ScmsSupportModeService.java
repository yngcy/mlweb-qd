package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsSupportMode;
import cn.edu.swust.qd.scms.model.form.SupportModeForm;
import cn.edu.swust.qd.scms.model.query.SupportModePageQuery;
import cn.edu.swust.qd.scms.model.vo.SupportModePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_support_mode(模型支撑方式)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsSupportModeService extends IService<ScmsSupportMode> {

    /**
     * 模型支撑方式分页列表
     *
     * @param queryParams
     * @return
     */
    Page<SupportModePageVO> getSupportModePage(SupportModePageQuery queryParams);

    /**
     * 获取模型支撑方式下拉列表
     *
     * @return
     */
    List<Option> getSupportModeOptions();

    /**
     * 保存模型支撑方式
     *
     * @param supportModeForm
     * @return
     */
    boolean saveSupportMode(SupportModeForm supportModeForm);

    /**
     * 获取模型支撑方式表单
     *
     * @param supportModeId
     * @return
     */
    SupportModeForm getSupportModeForm(Long supportModeId);

    /**
     * 删除模型支撑方式
     *
     * @param ids
     * @return
     */
    boolean deleteSupportModes(String ids);
}
