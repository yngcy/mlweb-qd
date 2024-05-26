package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.scms.converter.ScmsWingSectionConverter;
import cn.edu.swust.qd.scms.mapper.ScmsWingSectionMapper;
import cn.edu.swust.qd.scms.model.bo.WingSectionBO;
import cn.edu.swust.qd.scms.model.entity.ScmsWingSection;
import cn.edu.swust.qd.scms.model.form.WingSectionForm;
import cn.edu.swust.qd.scms.model.query.WingSectionPageQuery;
import cn.edu.swust.qd.scms.model.vo.WingSectionPageVO;
import cn.edu.swust.qd.scms.service.ScmsWingSectionService;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_wing_section(翼型截面信息)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsWingSectionServiceImpl extends ServiceImpl<ScmsWingSectionMapper, ScmsWingSection>
        implements ScmsWingSectionService {

    private final ScmsWingSectionConverter scmsWingSectionConverter;


    @Override
    public Page<WingSectionPageVO> getWingSectionPage(WingSectionPageQuery queryParams) {
        Page<WingSectionBO> wingSectionPage = this.baseMapper.getWingSectionPage(new Page<WingSectionBO>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        return scmsWingSectionConverter.bo2VO(wingSectionPage);
    }

    @Override
    public boolean saveWingSection(WingSectionForm wingSectionForm) {
        Long wingSectionId = wingSectionForm.getId();
        ScmsWingSection oldWingSection = null;
        if (wingSectionId != null) {
            oldWingSection = this.getById(wingSectionId);
            Assert.isTrue(ObjectUtil.isNotNull(oldWingSection), "翼型截面信息不存在");
        }

        ScmsWingSection entity = scmsWingSectionConverter.form2Entity(wingSectionForm);
        boolean result = this.save(entity);
        return result;
    }

    @Override
    public WingSectionForm getWingSectionForm(Long wingSectionId) {
        ScmsWingSection entity = this.getById(wingSectionId);
        return scmsWingSectionConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteWingSections(String ids) {
        List<Long> wingSectionIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long wingSectionId : wingSectionIds) {
            ScmsWingSection wingSection = this.getById(wingSectionId);
            Assert.isTrue(ObjectUtil.isNotNull(wingSection), "翼型截面信息不存在");

            this.removeById(wingSection);
        }
        return true;
    }

    @Override
    public boolean hasWingSection(Long wingflapConfigId) {
        return this.count(new LambdaQueryWrapper<ScmsWingSection>().eq(ScmsWingSection::getWingflapConfigId, wingflapConfigId)) > 0;
    }
}




