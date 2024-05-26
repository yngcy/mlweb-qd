package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsFasConverter;
import cn.edu.swust.qd.scms.mapper.ScmsFasMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsFas;
import cn.edu.swust.qd.scms.model.form.FasForm;
import cn.edu.swust.qd.scms.model.query.FasPageQuery;
import cn.edu.swust.qd.scms.model.vo.FasPageVO;
import cn.edu.swust.qd.scms.service.ScmsFasService;
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
 * @description 针对表【scms_fas(FAS细目类别信息表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsFasServiceImpl extends ServiceImpl<ScmsFasMapper, ScmsFas>
        implements ScmsFasService {

    private final ScmsFasConverter scmsFasConverter;

    @Override
    public Page<FasPageVO> getFasPage(FasPageQuery queryParams) {
        Page<ScmsFas> fasPage = this.page(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));

        return scmsFasConverter.entity2VO(fasPage);
    }


    @Override
    public List<Option> getFasOptions() {
        List<ScmsFas> entities = this.list(new LambdaQueryWrapper<ScmsFas>()
                .select(ScmsFas::getId, ScmsFas::getName));
        return scmsFasConverter.entities2Options(entities);
    }

    @Override
    public boolean saveFas(FasForm fasForm) {
        Long fasId = fasForm.getId();
        ScmsFas oldFas = null;
        if (fasId != null) {
            oldFas = this.getById(fasId);
            Assert.isTrue(oldFas != null, "FAS细目类别不存在");
        }

        ScmsFas fas = scmsFasConverter.form2Entity(fasForm);
        boolean result = this.save(fas);

        return result;
    }

    @Override
    public FasForm getFasForm(Long fasId) {
        ScmsFas entity = this.getById(fasId);
        return scmsFasConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteFases(String ids) {
        List<Long> fasIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long fasId : fasIds) {
            ScmsFas fas = this.getById(fasId);
            Assert.isTrue(fas != null, "FAS细目类别不存在");

            this.removeById(fas);
        }
        return true;
    }
}




