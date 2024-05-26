package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsAirfoilType;
import cn.edu.swust.qd.scms.model.form.AirfoilTypeForm;
import cn.edu.swust.qd.scms.model.query.AirfoilTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.AirfoilTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_airfoil_type(翼型类型表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsAirfoilTypeService extends IService<ScmsAirfoilType> {

    String getNameById(Long id);

    /**
     * 获取翼型类型分页列表
     *
     * @param queryParams
     * @return
     */
    Page<AirfoilTypePageVO> getAirfoilTypePage(AirfoilTypePageQuery queryParams);

    /**
     * 获取翼型类型下拉列表
     *
     * @return
     */
    List<Option> getAirfoilTypeOptions();

    /**
     * 保存翼型类型
     *
     * @param airfoilTypeForm
     * @return
     */
    boolean saveAirfoilType(AirfoilTypeForm airfoilTypeForm);

    /**
     * 获取翼型类型表单
     *
     * @param airfoilTypeId
     * @return
     */
    AirfoilTypeForm getAirfoilTypeForm(Long airfoilTypeId);

    /**
     * 删除翼型类型
     *
     * @param ids
     * @return
     */
    boolean deleteAirfoilTypes(String ids);
}
