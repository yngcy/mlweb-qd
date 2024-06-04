package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsCoordinateType;
import cn.edu.swust.qd.scms.model.form.CoordinateTypeForm;
import cn.edu.swust.qd.scms.model.query.CoordinateTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.CoordinateTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_coordinate_type(坐标系类别表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsCoordinateTypeService extends IService<ScmsCoordinateType> {

    /**
     * 获取坐标系类别分页
     *
     * @param queryParams
     * @return
     */
    Page<CoordinateTypePageVO> getCoordinateTypePage(CoordinateTypePageQuery queryParams);

    /**
     * 获取坐标系类别下拉列表
     *
     * @return
     */
    List<Option> getCoordinateTypeOptions();

    /**
     * 新增坐标系类别
     *
     * @param coordinateTypeForm
     * @return
     */
    boolean saveCoordinateType(CoordinateTypeForm coordinateTypeForm);

    /**
     * 获取坐标系类别表单
     *
     * @param coordinateTypeId
     * @return
     */
    CoordinateTypeForm getCoordinateTypeForm(Long coordinateTypeId);

    /**
     * 删除坐标系类别
     *
     * @param ids
     * @return
     */
    boolean deleteCoordinateTypes(String ids);

    /**
     * 修改坐标系类别数据密级
     *
     * @param coordinateTypeId
     * @param security
     * @return
     */
    boolean updateSecurity(Long coordinateTypeId, Integer security);
}
