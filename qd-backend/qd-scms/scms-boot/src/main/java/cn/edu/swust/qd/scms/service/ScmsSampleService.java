package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.scms.model.entity.ScmsSample;
import cn.edu.swust.qd.scms.model.query.SamplePageQuery;
import cn.edu.swust.qd.scms.model.vo.SamplePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 25055
 * @description 针对表【scms_sample(样本表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsSampleService extends IService<ScmsSample> {

    /**
     * 获取样本分页
     *
     * @param queryParams
     * @return
     */
    Page<SamplePageVO> getSamplePage(SamplePageQuery queryParams);

    /**
     * 判断研究机构是否被引用
     *
     * @param companyId
     * @return
     */
    boolean isCompanyReferenced(Long companyId);
}
