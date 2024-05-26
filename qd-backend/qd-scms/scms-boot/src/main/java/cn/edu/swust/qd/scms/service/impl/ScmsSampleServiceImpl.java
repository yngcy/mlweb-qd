package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.scms.converter.ScmsSampleConverter;
import cn.edu.swust.qd.scms.mapper.ScmsSampleMapper;
import cn.edu.swust.qd.scms.model.bo.SampleBO;
import cn.edu.swust.qd.scms.model.entity.ScmsSample;
import cn.edu.swust.qd.scms.model.query.SamplePageQuery;
import cn.edu.swust.qd.scms.model.vo.SamplePageVO;
import cn.edu.swust.qd.scms.service.ScmsSampleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 25055
 * @description 针对表【scms_sample(样本表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsSampleServiceImpl extends ServiceImpl<ScmsSampleMapper, ScmsSample>
        implements ScmsSampleService {

    private final ScmsSampleConverter scmsSampleConverter;

    @Override
    public Page<SamplePageVO> getSamplePage(SamplePageQuery queryParams) {
        Page<SampleBO> entity = this.baseMapper.getSamplePage(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        return scmsSampleConverter.bo2VO(entity);
    }


    @Override
    public boolean isCompanyReferenced(Long companyId) {
        return this.count(new LambdaQueryWrapper<ScmsSample>().eq(ScmsSample::getCompanyId, companyId)) > 0;
    }
}




