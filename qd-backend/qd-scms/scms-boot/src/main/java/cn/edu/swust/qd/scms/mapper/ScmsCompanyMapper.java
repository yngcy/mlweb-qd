package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.bo.CompanyBO;
import cn.edu.swust.qd.scms.model.entity.ScmsCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 25055
 * @description 针对表【scms_company(研究机构表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsCompany
 */
@Mapper
public interface ScmsCompanyMapper extends BaseMapper<ScmsCompany> {


    @DataPermission(clAlias = "c", clIdColumnName = "security", userAlias = "c")
    Page<CompanyBO> getCompanyPage(Page<CompanyBO> companyBOPage);
}




