package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsAirfoil;
import cn.edu.swust.qd.scms.model.form.AirfoilForm;
import cn.edu.swust.qd.scms.model.query.AirfoilPageQuery;
import cn.edu.swust.qd.scms.model.vo.AirfoilPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_airfoil(翼型信息表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsAirfoilService extends IService<ScmsAirfoil> {

    /**
     * 获取名称
     *
     * @param id
     * @return
     */
    String getNameById(Long id);

    /**
     * 获取翼型信息分页列表
     *
     * @param queryParams
     * @return
     */
    Page<AirfoilPageVO> getAirfoilPage(AirfoilPageQuery queryParams);

    /**
     * 保存翼型信息
     *
     * @param airfoilForm
     * @return
     */
    boolean saveAirfoil(AirfoilForm airfoilForm);

    /**
     * 获取翼型信息下拉列表
     *
     * @return
     */
    List<Option> getAirfoilOptions();

    /**
     * 获取翼型信息表单
     *
     * @param airfoilId
     * @return
     */
    AirfoilForm getAirfoilForm(Long airfoilId);

    /**
     * 删除翼型信息
     *
     * @param ids
     * @return
     */
    boolean deleteAirfoils(String ids);

    /**
     * 判断翼型类型是否被引用
     *
     * @param airfoilTypeId 翼型类型ID
     * @return 引用返回 true，否则返回 false
     */
    boolean isTypeReferenced(Long airfoilTypeId);
}
