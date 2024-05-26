package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsFuelType;
import cn.edu.swust.qd.scms.model.form.FuelTypeForm;
import cn.edu.swust.qd.scms.model.query.FuelTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.FuelTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_fuel_type(燃油类型表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsFuelTypeService extends IService<ScmsFuelType> {

    String getNameById(Long id);

    /**
     * 获取燃油类型分页列表
     *
     * @param queryParams
     * @return
     */
    Page<FuelTypePageVO> getFuelTypePage(FuelTypePageQuery queryParams);

    /**
     * 获取燃油类型下拉列表
     *
     * @return
     */
    List<Option> getFuelTypeOptions();

    /**
     * 保存燃油类型
     *
     * @param fuelTypeForm
     * @return
     */
    boolean saveFuelType(FuelTypeForm fuelTypeForm);

    /**
     * 获取燃油类型表单
     *
     * @param fuelTypeId
     * @return
     */
    FuelTypeForm getFuelTypeForm(Long fuelTypeId);

    /**
     * 删除燃油类型
     *
     * @param ids
     * @return
     */
    boolean deleteFuelTypes(String ids);
}
