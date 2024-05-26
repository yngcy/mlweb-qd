package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsCompany;
import cn.edu.swust.qd.scms.model.form.CompanyForm;
import cn.edu.swust.qd.scms.model.query.CompanyPageQuery;
import cn.edu.swust.qd.scms.model.vo.CompanyPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_company(研究机构表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsCompanyService extends IService<ScmsCompany> {

    String getNameById(Long id);

    /**
     * 研究机构分页列表
     *
     * @param queryParams
     * @return
     */
    Page<CompanyPageVO> getCompanyPage(CompanyPageQuery queryParams);

    /**
     * 研究机构下拉列表
     *
     * @return
     */
    List<Option> getCompanyOptions();

    /**
     * 保存研究机构
     *
     * @param companyForm
     * @return
     */
    boolean saveCompany(CompanyForm companyForm);

    /**
     * 获取研究机构表单
     *
     * @param companyId
     * @return
     */
    CompanyForm getCompanyForm(Long companyId);

    /**
     * 删除研究机构
     *
     * @param ids
     * @return
     */
    boolean deleteCompanies(String ids);

    /**
     * 判断当前节点是否包含孩子节点
     *
     * @return
     */
    boolean isParent(Long id);
}
