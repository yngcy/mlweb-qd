package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsBalanceConverter;
import cn.edu.swust.qd.scms.mapper.ScmsBalanceMapper;
import cn.edu.swust.qd.scms.model.bo.BalanceBO;
import cn.edu.swust.qd.scms.model.entity.ScmsBalance;
import cn.edu.swust.qd.scms.model.form.BalanceForm;
import cn.edu.swust.qd.scms.model.query.BalancePageQuery;
import cn.edu.swust.qd.scms.model.vo.BalancePageVO;
import cn.edu.swust.qd.scms.service.ScmsBalanceService;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_balance(天平表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsBalanceServiceImpl extends ServiceImpl<ScmsBalanceMapper, ScmsBalance>
        implements ScmsBalanceService {

    private final ScmsBalanceConverter scmsBalanceConverter;

    @Override
    public Page<BalancePageVO> getBalancePage(BalancePageQuery queryParams) {
        Page<BalanceBO> balancePage = this.baseMapper.getBalancePage(new Page<BalanceBO>(queryParams.getPageNum(), queryParams.getPageSize()));
        Page<BalancePageVO> page = scmsBalanceConverter.bo2VO(balancePage);
        return page;
    }


    @Override
    public List<Option> listBalanceOptions() {
        List<ScmsBalance> entities = this.list(new LambdaQueryWrapper<ScmsBalance>()
                .select(ScmsBalance::getId, ScmsBalance::getName));

        return scmsBalanceConverter.entities2Options(entities);
    }

    @Override
    public boolean saveBalance(BalanceForm balanceForm) {
        Long balanceId = balanceForm.getId();
        ScmsBalance oldBalance = null;
        if (balanceId != null) {
            oldBalance = this.getById(balanceId);
            Assert.isTrue(oldBalance != null, "天平信息不存在");
        }

        ScmsBalance balance = scmsBalanceConverter.form2Entity(balanceForm);
        boolean result = this.saveOrUpdate(balance);
        return result;
    }

    @Override
    public BalanceForm getBalanceForm(Long balanceId) {
        ScmsBalance balance = this.getById(balanceId);
        return scmsBalanceConverter.entity2Form(balance);
    }

    @Override
    public boolean deleteBalances(String ids) {
        List<Long> balanceIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long balanceId : balanceIds) {
            ScmsBalance balance = this.getById(balanceId);
            Assert.isTrue(balance != null, "天平信息不存在");

            this.removeById(balance);
        }
        return true;
    }

    @Override
    public boolean isTypeReferenced(Long balanceTypeId) {

        return this.count(new LambdaQueryWrapper<ScmsBalance>().eq(ScmsBalance::getBalanceTypeId, balanceTypeId)) > 0;
    }

    @Override
    public boolean isCompanyReferenced(Long companyId) {
        return this.count(new LambdaQueryWrapper<ScmsBalance>()
                .eq(ScmsBalance::getCompanyDesignId, companyId)
                .or()
                .eq(ScmsBalance::getCompanyResearchId, companyId)) > 0;
    }

}




