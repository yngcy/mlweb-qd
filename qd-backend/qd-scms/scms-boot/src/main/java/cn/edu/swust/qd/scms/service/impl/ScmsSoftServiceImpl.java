package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsSoftConverter;
import cn.edu.swust.qd.scms.mapper.ScmsSoftMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsSoft;
import cn.edu.swust.qd.scms.model.form.SoftForm;
import cn.edu.swust.qd.scms.model.query.SoftPageQuery;
import cn.edu.swust.qd.scms.model.vo.SoftPageVO;
import cn.edu.swust.qd.scms.service.ScmsSoftService;
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
 * @description 针对表【scms_soft(软件表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsSoftServiceImpl extends ServiceImpl<ScmsSoftMapper, ScmsSoft>
        implements ScmsSoftService {

    private final ScmsSoftConverter scmsSoftConverter;

    @Override
    public Page<SoftPageVO> getSoftPage(SoftPageQuery queryParams) {
        Page<ScmsSoft> entity = this.page(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        return scmsSoftConverter.entity2VO(entity);
    }


    @Override
    public List<Option> getSoftOptions() {
        List<ScmsSoft> entities = this.list(new LambdaQueryWrapper<ScmsSoft>()
                .select(ScmsSoft::getId, ScmsSoft::getName));
        return scmsSoftConverter.entities2Options(entities);
    }

    @Override
    public boolean saveSoft(SoftForm softForm) {
        Long softId = softForm.getId();
        ScmsSoft oldSoft = null;
        if (softId != null) {
            oldSoft = this.getById(softId);
            Assert.isTrue(oldSoft != null, "软件不存在");
        }

        ScmsSoft entity = scmsSoftConverter.form2Entity(softForm);
        boolean result = this.saveOrUpdate(entity);
        return result;
    }

    @Override
    public SoftForm getSoftForm(Long softId) {
        ScmsSoft entity = this.getById(softId);
        return scmsSoftConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteSofts(String ids) {
        List<Long> softIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long softId : softIds) {
            ScmsSoft soft = this.getById(softId);
            Assert.isTrue(soft != null, "软件不存在");

            this.removeById(soft);
        }
        return true;
    }
}




