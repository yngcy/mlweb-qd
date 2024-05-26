package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsInjModeConverter;
import cn.edu.swust.qd.scms.mapper.ScmsInjModeMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsInjMode;
import cn.edu.swust.qd.scms.model.form.InjModeForm;
import cn.edu.swust.qd.scms.model.query.InjModePageQuery;
import cn.edu.swust.qd.scms.model.vo.InjModePageVO;
import cn.edu.swust.qd.scms.service.ScmsInjModeService;
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
 * @description 针对表【scms_inj_mode(注油方式表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsInjModeServiceImpl extends ServiceImpl<ScmsInjModeMapper, ScmsInjMode>
        implements ScmsInjModeService {

    private final ScmsInjModeConverter scmsInjModeConverter;

    @Override
    public Page<InjModePageVO> getInjModePage(InjModePageQuery queryParams) {
        Page<ScmsInjMode> entity = this.page(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        return scmsInjModeConverter.entity2VO(entity);
    }


    @Override
    public List<Option> getInjModeOptions() {
        List<ScmsInjMode> entities = this.list(new LambdaQueryWrapper<ScmsInjMode>()
                .select(ScmsInjMode::getId, ScmsInjMode::getName));
        return scmsInjModeConverter.entities2Options(entities);
    }

    @Override
    public boolean saveInjMode(InjModeForm injModeForm) {
        Long injModeId = injModeForm.getId();
        ScmsInjMode oldInjMode = null;
        if (injModeId != null) {
            oldInjMode = this.getById(injModeId);
            Assert.isTrue(oldInjMode != null, "注油方式不存在");
        }

        ScmsInjMode entity = scmsInjModeConverter.form2Entity(injModeForm);
        boolean result = this.save(entity);
        return result;
    }

    @Override
    public InjModeForm getInjModeForm(Long injModeId) {
        ScmsInjMode entity = this.getById(injModeId);
        return scmsInjModeConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteInjModes(String ids) {
        List<Long> injModeIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long injModeId : injModeIds) {
            ScmsInjMode injMode = this.getById(injModeId);
            Assert.isTrue(injMode != null, "注油方式不存在");

            this.removeById(injMode);
        }
        return true;
    }
}




