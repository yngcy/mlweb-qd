package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsWtType;
import cn.edu.swust.qd.scms.model.form.WtTypeForm;
import cn.edu.swust.qd.scms.model.query.WtTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.WtTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_wt_type(风洞类型表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsWtTypeService extends IService<ScmsWtType> {

    String getNameById(Long id);

    /**
     * 风洞类型分页列表
     *
     * @param queryParams
     * @return
     */
    Page<WtTypePageVO> getWtTypePage(WtTypePageQuery queryParams);

    /**
     * 风洞类型下拉列表
     *
     * @return
     */
    List<Option> getWtTypeOptions();

    /**
     * 新增/修改风洞类型
     *
     * @param wtTypeForm
     * @return
     */
    boolean saveWtType(WtTypeForm wtTypeForm);

    /**
     * 获取风洞类型表单
     *
     * @param wtTypeId
     * @return
     */
    WtTypeForm getWtTypeForm(Long wtTypeId);

    /**
     * 删除风洞类型
     *
     * @param ids
     * @return
     */
    boolean deleteWtTypes(String ids);
}
