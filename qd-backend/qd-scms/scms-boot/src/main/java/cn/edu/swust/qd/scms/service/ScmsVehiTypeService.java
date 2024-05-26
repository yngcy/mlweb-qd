package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsVehiType;
import cn.edu.swust.qd.scms.model.form.VehiTypeForm;
import cn.edu.swust.qd.scms.model.query.VehiTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.VehiTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_vehi_type(气动布局类型表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsVehiTypeService extends IService<ScmsVehiType> {

    String getNameById(Long id);

    /**
     * 获取气动布局类型分页列表
     *
     * @param queryParams
     * @return
     */
    Page<VehiTypePageVO> getVehiTypePage(VehiTypePageQuery queryParams);

    /**
     * 获取气动布局类型下拉列表
     *
     * @return
     */
    List<Option> getVehiTypeOptions();

    /**
     * 保存气动布局类型
     *
     * @param vehiTypeForm
     * @return
     */
    boolean saveVehiType(VehiTypeForm vehiTypeForm);

    /**
     * 获取气动布局类型
     *
     * @param vehiTypeId
     * @return
     */
    VehiTypeForm getVehiTypeForm(Long vehiTypeId);

    /**
     * 删除气动布局类型
     *
     * @param ids
     * @return
     */
    boolean deleteVehiTypes(String ids);
}
