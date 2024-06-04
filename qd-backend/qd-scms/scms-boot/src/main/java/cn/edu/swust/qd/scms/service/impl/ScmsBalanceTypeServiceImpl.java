package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsBalanceTypeConverter;
import cn.edu.swust.qd.scms.mapper.ScmsBalanceTypeMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsBalanceType;
import cn.edu.swust.qd.scms.model.form.BalanceTypeForm;
import cn.edu.swust.qd.scms.model.query.BalanceTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.BalanceTypePageVO;
import cn.edu.swust.qd.scms.service.ScmsBalanceService;
import cn.edu.swust.qd.scms.service.ScmsBalanceTypeService;
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
 * @description 针对表【scms_balance_type(天平类型表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsBalanceTypeServiceImpl extends ServiceImpl<ScmsBalanceTypeMapper, ScmsBalanceType>
        implements ScmsBalanceTypeService {

    private final ScmsBalanceTypeConverter scmsBalanceTypeConverter;

    private final ScmsBalanceService scmsBalanceService;

    @Override
    public String getNameById(Long id) {
        ScmsBalanceType entity = this.getById(id);
        return entity.getName();
    }

    @Override
    public Page<BalanceTypePageVO> getBalanceTypePage(BalanceTypePageQuery queryParams) {
        Page<ScmsBalanceType> balanceTypePage = this.page(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()));
        Page<BalanceTypePageVO> page = scmsBalanceTypeConverter.entity2VO(balanceTypePage);
        return page;
    }

    @Override
    public List<Option> getBalanceTypeOptions() {
        List<ScmsBalanceType> entities = this.list(new LambdaQueryWrapper<ScmsBalanceType>()
                .select(ScmsBalanceType::getId, ScmsBalanceType::getName));
        return scmsBalanceTypeConverter.entities2Options(entities);
    }

    @Override
    public boolean saveBalanceType(BalanceTypeForm balanceTypeForm) {
        Long balanceTypeId = balanceTypeForm.getId();
        ScmsBalanceType oldBalanceType = null;
        if (balanceTypeId != null) {
            oldBalanceType = this.getById(balanceTypeId);
            Assert.isTrue(oldBalanceType != null, "天平类型不存在");
        }

        ScmsBalanceType balanceType = scmsBalanceTypeConverter.form2Entity(balanceTypeForm);
        boolean result = this.saveOrUpdate(balanceType);
        return result;
    }

    @Override
    public boolean deleteBalanceTypes(String ids) {
        List<Long> balanceTypeIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long balanceTypeId : balanceTypeIds) {
            ScmsBalanceType balanceType = this.getById(balanceTypeId);
            Assert.isTrue(balanceType != null, "天平类型不存在");

            // 无天平信息关联
            boolean isReferenced = scmsBalanceService.isTypeReferenced(balanceTypeId);
            Assert.isTrue(!isReferenced, "天平类型被天平引用，不可删除");

            this.removeById(balanceType);
        }
        return false;
    }

    @Override
    public BalanceTypeForm getBalanceTypeForm(Long balanceTypeId) {
        ScmsBalanceType entity = this.getById(balanceTypeId);
        return scmsBalanceTypeConverter.entity2Form(entity);
    }
}




