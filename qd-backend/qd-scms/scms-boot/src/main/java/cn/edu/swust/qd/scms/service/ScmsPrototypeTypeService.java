package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsPrototypeType;
import cn.edu.swust.qd.scms.model.form.PrototypeTypeForm;
import cn.edu.swust.qd.scms.model.query.PrototypeTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.PrototypeTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_prototype_type(原型类型表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsPrototypeTypeService extends IService<ScmsPrototypeType> {

    /**
     * 获取原型类型分页列表
     *
     * @param queryParams
     * @return
     */
    Page<PrototypeTypePageVO> getPrototypeTypePage(PrototypeTypePageQuery queryParams);

    /**
     * 获取原型下拉列表
     *
     * @return
     */
    List<Option> getPrototypeTypeOptions();

    /**
     * 保存原型类型
     *
     * @param prototypeTypeForm
     * @return
     */
    boolean savePrototypeType(PrototypeTypeForm prototypeTypeForm);

    /**
     * 获取原型类型表单
     *
     * @param prototypeTypeId
     * @return
     */
    PrototypeTypeForm getPrototypeTypeForm(Long prototypeTypeId);

    /**
     * 删除原型类型
     *
     * @param ids
     * @return
     */
    boolean deletePrototypeTypes(String ids);
}
