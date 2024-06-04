package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.scms.converter.ScmsWingflapConfigConverter;
import cn.edu.swust.qd.scms.mapper.ScmsWingflapConfigMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsWingflapConfig;
import cn.edu.swust.qd.scms.model.form.WingflapConfigForm;
import cn.edu.swust.qd.scms.model.query.WingflapConfigPageQuery;
import cn.edu.swust.qd.scms.model.vo.WingflapConfigPageVO;
import cn.edu.swust.qd.scms.service.ScmsWingSectionService;
import cn.edu.swust.qd.scms.service.ScmsWingflapConfigService;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_wingflap_config(翼舵构型表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsWingflapConfigServiceImpl extends ServiceImpl<ScmsWingflapConfigMapper, ScmsWingflapConfig>
        implements ScmsWingflapConfigService {

    private final ScmsWingflapConfigConverter scmsWingflapConfigConverter;

    private final ScmsWingSectionService scmsWingSectionService;


    @Override
    public Page<WingflapConfigPageVO> getWingflapConfigPage(WingflapConfigPageQuery queryParams) {
        Page<ScmsWingflapConfig> entity = this.page(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        return scmsWingflapConfigConverter.entity2VO(entity);
    }


    @Override
    public boolean saveWingflapConfig(WingflapConfigForm wingflapConfigForm) {
        Long wingflapConfigId = wingflapConfigForm.getId();
        ScmsWingflapConfig oldWingflapConfig = null;
        if (wingflapConfigId != null) {
            oldWingflapConfig = this.getById(wingflapConfigId);
            Assert.isTrue(oldWingflapConfig != null, "翼舵构型不存在");
        }

        ScmsWingflapConfig entity = scmsWingflapConfigConverter.form2Entity(wingflapConfigForm);
        boolean result = this.saveOrUpdate(entity);
        return result;
    }

    @Override
    public WingflapConfigForm getWingflapConfigForm(Long wingflapConfigId) {
        ScmsWingflapConfig entity = this.getById(wingflapConfigId);
        return scmsWingflapConfigConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteWingflapConfigs(String ids) {
        List<Long> wingflapConfigIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long wingflapConfigId : wingflapConfigIds) {
            ScmsWingflapConfig wingflapConfig = this.getById(wingflapConfigId);
            Assert.isTrue(wingflapConfig != null, "翼舵构型不存在");

            // 无翼舵截面信息
            boolean hasWingSection = scmsWingSectionService.hasWingSection(wingflapConfigId);
            Assert.isTrue(!hasWingSection, "翼舵构型下存在翼舵截面信息，请先删除翼舵截面信息");

            this.removeById(wingflapConfig);
        }
        return true;
    }
}




