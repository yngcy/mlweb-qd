package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsCoordSystemType;
import cn.edu.swust.qd.scms.model.form.CoordSystemTypeForm;
import cn.edu.swust.qd.scms.model.query.CoordSystemTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.CoordSystemTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_coord_system_type(坐标系系统类别表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsCoordSystemTypeService extends IService<ScmsCoordSystemType> {

    /**
     * 分页查询坐标系系统类别
     *
     * @param queryParams
     * @return
     */
    Page<CoordSystemTypePageVO> getCoordSystemTypePage(CoordSystemTypePageQuery queryParams);

    /**
     * 获取坐标系系统类别下拉列表
     *
     * @return
     */
    List<Option> getCoordSystemTypeOptions();

    /**
     * 保存坐标系系统类别
     *
     * @param coordSystemTypeForm
     * @return
     */
    boolean saveCoordSystemType(CoordSystemTypeForm coordSystemTypeForm);

    /**
     * 获取坐标系系统类别表单
     *
     * @param coordSystemTypeId
     * @return
     */
    CoordSystemTypeForm getCoordSystemTypeForm(Long coordSystemTypeId);

    /**
     * 删除坐标系系统类别
     *
     * @param ids
     * @return
     */
    boolean deleteCoordSystemTypes(String ids);
}
