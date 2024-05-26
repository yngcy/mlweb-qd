package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsBalanceType;
import cn.edu.swust.qd.scms.model.form.BalanceTypeForm;
import cn.edu.swust.qd.scms.model.query.BalanceTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.BalanceTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_balance_type(天平类型表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsBalanceTypeService extends IService<ScmsBalanceType> {

    String getNameById(Long id);

    /**
     * 天平类型分页列表
     *
     * @param queryParams
     * @return
     */
    Page<BalanceTypePageVO> getBalanceTypePage(BalanceTypePageQuery queryParams);

    /**
     * 获取天平类型下拉列表
     *
     * @return
     */
    List<Option> getBalanceTypeOptions();

    /**
     * 保存天平类型
     *
     * @param balanceTypeForm
     * @return
     */
    boolean saveBalanceType(BalanceTypeForm balanceTypeForm);

    /**
     * 删除天平类型
     *
     * @param ids
     * @return
     */
    boolean deleteBalanceTypes(String ids);

    /**
     * 获取天平类型表单
     *
     * @param balanceTypeId
     * @return
     */
    BalanceTypeForm getBalanceTypeForm(Long balanceTypeId);
}
