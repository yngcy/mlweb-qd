package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsEngineType;
import cn.edu.swust.qd.scms.model.form.EngineTypeForm;
import cn.edu.swust.qd.scms.model.query.EngineTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.EngineTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_engine_type(发动机类型)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsEngineTypeService extends IService<ScmsEngineType> {

    String getNameById(Long id);

    /**
     * 获取发动机类型分页列表
     *
     * @param queryParams
     * @return
     */
    Page<EngineTypePageVO> getEngineTypePage(EngineTypePageQuery queryParams);

    /**
     * 获取发动机类型下拉列表
     *
     * @return
     */
    List<Option> getEngineTypeOptions();

    /**
     * 保存发动机类型
     *
     * @param engineTypeForm
     * @return
     */
    boolean saveEngineType(EngineTypeForm engineTypeForm);

    /**
     * 获取发动机表单
     *
     * @param engineTypeId
     * @return
     */
    EngineTypeForm getEngineTypeForm(Long engineTypeId);

    /**
     * 删除发动机
     *
     * @param ids
     * @return
     */
    boolean deleteEngineTypes(String ids);
}
