package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsInjMode;
import cn.edu.swust.qd.scms.model.form.InjModeForm;
import cn.edu.swust.qd.scms.model.query.InjModePageQuery;
import cn.edu.swust.qd.scms.model.vo.InjModePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_inj_mode(注油方式表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsInjModeService extends IService<ScmsInjMode> {

    /**
     * 获取注油方式分页列表
     *
     * @param queryParams
     * @return
     */
    Page<InjModePageVO> getInjModePage(InjModePageQuery queryParams);

    /**
     * 获取注油方式下拉列表
     *
     * @return
     */
    List<Option> getInjModeOptions();

    /**
     * 保存注油方式
     *
     * @param injModeForm
     * @return
     */
    boolean saveInjMode(InjModeForm injModeForm);

    /**
     * 获取注油方式表单
     *
     * @param injModeId
     * @return
     */
    InjModeForm getInjModeForm(Long injModeId);

    /**
     * 删除注油方式
     *
     * @param ids
     * @return
     */
    boolean deleteInjModes(String ids);
}
