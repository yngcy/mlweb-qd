package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.scms.model.entity.ScmsWingflapConfig;
import cn.edu.swust.qd.scms.model.form.WingflapConfigForm;
import cn.edu.swust.qd.scms.model.query.WingflapConfigPageQuery;
import cn.edu.swust.qd.scms.model.vo.WingflapConfigPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 25055
 * @description 针对表【scms_wingflap_config(翼舵构型表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsWingflapConfigService extends IService<ScmsWingflapConfig> {

    /**
     * 翼舵构型分页列表
     *
     * @param queryParams
     * @return
     */
    Page<WingflapConfigPageVO> getWingflapConfigPage(WingflapConfigPageQuery queryParams);

    /**
     * 保存翼舵构型
     *
     * @param wingflapConfigForm
     * @return
     */
    boolean saveWingflapConfig(WingflapConfigForm wingflapConfigForm);

    /**
     * 获取翼舵构型表单
     *
     * @param wingflapConfigId
     * @return
     */
    WingflapConfigForm getWingflapConfigForm(Long wingflapConfigId);

    /**
     * 删除翼舵构型
     *
     * @param ids
     * @return
     */
    boolean deleteWingflapConfigs(String ids);
}
