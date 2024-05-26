package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.scms.model.entity.ScmsWingflapLayout;
import cn.edu.swust.qd.scms.model.form.WingflapLayoutForm;
import cn.edu.swust.qd.scms.model.query.WingflapLayoutPageQuery;
import cn.edu.swust.qd.scms.model.vo.WingflapLayoutPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 25055
 * @description 针对表【scms_wingflap_layout(翼舵布局表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsWingflapLayoutService extends IService<ScmsWingflapLayout> {

    /**
     * 获取翼舵布局分页列表
     *
     * @param queryParams
     * @return
     */
    Page<WingflapLayoutPageVO> getWingflapLayoutPage(WingflapLayoutPageQuery queryParams);

    /**
     * 保存翼舵布局分页列表
     *
     * @param wingflapLayoutForm
     * @return
     */
    boolean saveWingflapLayout(WingflapLayoutForm wingflapLayoutForm);

    /**
     * 获取翼舵布局表单
     *
     * @param wingflapLayoutId
     * @return
     */
    WingflapLayoutForm getWingflapLayoutForm(Long wingflapLayoutId);

    /**
     * 删除翼舵布局
     *
     * @param ids
     * @return
     */
    boolean deleteWingflapLayouts(String ids);
}
