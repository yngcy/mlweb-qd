package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsBalance;
import cn.edu.swust.qd.scms.model.form.BalanceForm;
import cn.edu.swust.qd.scms.model.query.BalancePageQuery;
import cn.edu.swust.qd.scms.model.vo.BalancePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_balance(天平表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsBalanceService extends IService<ScmsBalance> {

    /**
     * 获取天平信息分页
     *
     * @param queryParams
     * @return
     */
    Page<BalancePageVO> getBalancePage(BalancePageQuery queryParams);

    /**
     * 天平下拉列表
     *
     * @return
     */
    List<Option> listBalanceOptions();

    /**
     * 保存天平信息
     *
     * @param balanceForm
     * @return
     */
    boolean saveBalance(BalanceForm balanceForm);

    /**
     * 获取天平表单数据
     *
     * @param balanceId
     * @return
     */
    BalanceForm getBalanceForm(Long balanceId);

    /**
     * 删除天平信息
     *
     * @param ids
     * @return
     */
    boolean deleteBalances(String ids);

    /**
     * 判断天平类型是否被天平关联
     *
     * @param balanceTypeId 天平类型ID
     * @return true：被关联
     */
    boolean isTypeReferenced(Long balanceTypeId);

    /**
     * 判断研究机构是否被引用
     *
     * @param companyId 研究机构ID
     * @return
     */
    boolean isCompanyReferenced(Long companyId);
}
