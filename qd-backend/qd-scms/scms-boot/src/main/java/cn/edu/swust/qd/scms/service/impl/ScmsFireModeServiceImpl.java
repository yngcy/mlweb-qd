package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsFireModeConverter;
import cn.edu.swust.qd.scms.mapper.ScmsFireModeMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsFireMode;
import cn.edu.swust.qd.scms.model.form.FireModeForm;
import cn.edu.swust.qd.scms.model.query.FireModePageQuery;
import cn.edu.swust.qd.scms.model.vo.FireModePageVO;
import cn.edu.swust.qd.scms.service.ScmsFireModeService;
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
 * @description 针对表【scms_fire_mode(点火方式表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsFireModeServiceImpl extends ServiceImpl<ScmsFireModeMapper, ScmsFireMode>
        implements ScmsFireModeService {

    private final ScmsFireModeConverter scmsFireModeConverter;

    @Override
    public Page<FireModePageVO> getFireModePage(FireModePageQuery queryParams) {
        Page<ScmsFireMode> entity = this.page(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        return scmsFireModeConverter.entity2VO(entity);
    }


    @Override
    public List<Option> getFireModeOptions() {
        List<ScmsFireMode> entities = this.list(new LambdaQueryWrapper<ScmsFireMode>()
                .select(ScmsFireMode::getId, ScmsFireMode::getName));
        return scmsFireModeConverter.entities2Options(entities);
    }

    @Override
    public boolean saveFireMode(FireModeForm fireModeForm) {
        Long fireModeId = fireModeForm.getId();
        ScmsFireMode oldFireMode = null;
        if (fireModeId != null) {
            oldFireMode = this.getById(fireModeId);
            Assert.isTrue(oldFireMode != null, "点火方式不存在");
        }

        ScmsFireMode fireMode = scmsFireModeConverter.form2Entity(fireModeForm);
        boolean result = this.save(fireMode);

        return result;
    }

    @Override
    public boolean deleteFireModes(String ids) {
        List<Long> fireModeIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long fireModeId : fireModeIds) {
            ScmsFireMode fireMode = this.getById(fireModeId);
            Assert.isTrue(fireMode != null, "点火方式不存在");

            this.removeById(fireMode);
        }
        return true;
    }

    @Override
    public FireModeForm getFireModeForm(Long fireModeId) {
        ScmsFireMode entity = this.getById(fireModeId);

        return scmsFireModeConverter.entity2Form(entity);
    }
}




