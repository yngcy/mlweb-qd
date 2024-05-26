package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.scms.model.entity.ScmsWingSection;
import cn.edu.swust.qd.scms.model.form.WingSectionForm;
import cn.edu.swust.qd.scms.model.query.WingSectionPageQuery;
import cn.edu.swust.qd.scms.model.vo.WingSectionPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 25055
 * @description 针对表【scms_wing_section(翼型截面信息)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsWingSectionService extends IService<ScmsWingSection> {

    /**
     * 翼型截面分页列表
     *
     * @param queryParams
     * @return
     */
    Page<WingSectionPageVO> getWingSectionPage(WingSectionPageQuery queryParams);

    /**
     * 保存翼型截面
     *
     * @param wingSectionForm
     * @return
     */
    boolean saveWingSection(WingSectionForm wingSectionForm);

    /**
     * 获取翼型截面表单
     *
     * @param wingSectionId
     * @return
     */
    WingSectionForm getWingSectionForm(Long wingSectionId);

    /**
     * 删除翼型截面
     *
     * @param ids
     * @return
     */
    boolean deleteWingSections(String ids);

    /**
     * 判断翼舵构型下是否有翼型截面
     *
     * @param wingflapConfigId 翼舵构型ID
     * @return
     */
    boolean hasWingSection(Long wingflapConfigId);
}
