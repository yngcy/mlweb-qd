package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsSupportModeConverter;
import cn.edu.swust.qd.scms.mapper.ScmsSupportModeMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsSupportMode;
import cn.edu.swust.qd.scms.model.form.SupportModeForm;
import cn.edu.swust.qd.scms.model.query.SupportModePageQuery;
import cn.edu.swust.qd.scms.model.vo.SupportModePageVO;
import cn.edu.swust.qd.scms.service.ScmsSupportModeService;
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
 * @description 针对表【scms_support_mode(模型支撑方式)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsSupportModeServiceImpl extends ServiceImpl<ScmsSupportModeMapper, ScmsSupportMode>
        implements ScmsSupportModeService {

    private final ScmsSupportModeConverter scmsSupportModeConverter;

    @Override
    public Page<SupportModePageVO> getSupportModePage(SupportModePageQuery queryParams) {
        Page<ScmsSupportMode> entity = this.page(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        return scmsSupportModeConverter.entity2VO(entity);
    }

    @Override
    public List<Option> getSupportModeOptions() {
        List<ScmsSupportMode> entities = this.list(new LambdaQueryWrapper<ScmsSupportMode>()
                .select(ScmsSupportMode::getId, ScmsSupportMode::getName));
        return scmsSupportModeConverter.entities2Options(entities);
    }

    @Override
    public boolean saveSupportMode(SupportModeForm supportModeForm) {
        Long supportModeId = supportModeForm.getId();
        ScmsSupportMode oldSupportMode = null;
        if (supportModeId != null) {
            oldSupportMode = this.getById(supportModeId);
            Assert.isTrue(oldSupportMode != null, "模型支撑方式不存在");
        }

        ScmsSupportMode entity = scmsSupportModeConverter.form2Entity(supportModeForm);
        boolean result = this.save(entity);
        return result;
    }

    @Override
    public SupportModeForm getSupportModeForm(Long supportModeId) {
        ScmsSupportMode entity = this.getById(supportModeId);
        return scmsSupportModeConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteSupportModes(String ids) {
        List<Long> supportModeIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long supportModeId : supportModeIds) {
            ScmsSupportMode supportMode = this.getById(supportModeId);
            Assert.isTrue(supportMode != null, "模型支撑方式不存在");

            this.removeById(supportMode);
        }
        return true;
    }
}




