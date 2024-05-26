package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsCompanyConverter;
import cn.edu.swust.qd.scms.mapper.ScmsCompanyMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsCompany;
import cn.edu.swust.qd.scms.model.form.CompanyForm;
import cn.edu.swust.qd.scms.model.query.CompanyPageQuery;
import cn.edu.swust.qd.scms.model.vo.CompanyPageVO;
import cn.edu.swust.qd.scms.service.ScmsBalanceService;
import cn.edu.swust.qd.scms.service.ScmsCompanyService;
import cn.edu.swust.qd.scms.service.ScmsSampleService;
import cn.edu.swust.qd.scms.service.ScmsWindTunnelService;
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
 * @description 针对表【scms_company(研究机构表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsCompanyServiceImpl extends ServiceImpl<ScmsCompanyMapper, ScmsCompany>
        implements ScmsCompanyService {

    private final ScmsCompanyConverter scmsCompanyConverter;

    private final ScmsBalanceService scmsBalanceService;
    private final ScmsWindTunnelService scmsWindTunnelService;
    private final ScmsSampleService scmsSampleService;

    @Override
    public String getNameById(Long id) {
        ScmsCompany entity = this.getById(id);
        return entity.getName();
    }

    @Override
    public Page<CompanyPageVO> getCompanyPage(CompanyPageQuery queryParams) {
        Page<ScmsCompany> companyPage = this.page(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()));
        Page<CompanyPageVO> page = scmsCompanyConverter.entity2VO(companyPage);
        return page;
    }


    @Override
    public List<Option> getCompanyOptions() {
        List<ScmsCompany> entities = this.list(new LambdaQueryWrapper<ScmsCompany>()
                .select(ScmsCompany::getId, ScmsCompany::getName));
        return scmsCompanyConverter.entities2Options(entities);
    }

    @Override
    public boolean saveCompany(CompanyForm companyForm) {
        Long companyId = companyForm.getId();
        ScmsCompany oldCompany = null;
        if (companyId != null) {
            oldCompany = this.getById(companyId);
            Assert.isTrue(oldCompany != null, "研究机构不存在");
        }

        // todo 逻辑校验

        // 实体转换
        ScmsCompany company = scmsCompanyConverter.form2Entity(companyForm);
        boolean result = this.save(company);
        return result;
    }

    @Override
    public CompanyForm getCompanyForm(Long companyId) {
        ScmsCompany entity = this.getById(companyId);
        return scmsCompanyConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteCompanies(String ids) {
        List<Long> companyIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long companyId : companyIds) {
            ScmsCompany company = this.getById(companyId);
            Assert.isTrue(company != null, "研究机构为空");

            // 无子机构
            boolean isParent = this.isParent(companyId);
            Assert.isTrue(!isParent, "研究机构【{}】下包含子研究机构，请删除所有子研究机构", company.getCode());

            // 无天平信息引用
            boolean isReferenced1 = scmsBalanceService.isCompanyReferenced(companyId);
            Assert.isTrue(!isReferenced1, "研究机构【{}】被天平信息关联，请先删除所有关联的天平信息", company.getCode());
            // 无风洞信息引用
            boolean isReferenced2 = scmsWindTunnelService.isCompanyReferenced(companyId);
            Assert.isTrue(!isReferenced2, "研究机构【{}】被风洞信息关联，请先删除所有关联的风洞信息", company.getCode());
            // 无样本信息应引用
            boolean isReferenced3 = scmsSampleService.isCompanyReferenced(companyId);
            Assert.isTrue(!isReferenced3, "研究机构【{}】被样本信息关联，请先删除所有关联的样本信息", company.getCode());

            this.removeById(company);

        }
        return true;
    }

    @Override
    public boolean isParent(Long id) {
        return this.count(new LambdaQueryWrapper<ScmsCompany>().eq(ScmsCompany::getParentId, id)) > 0;
    }
}




