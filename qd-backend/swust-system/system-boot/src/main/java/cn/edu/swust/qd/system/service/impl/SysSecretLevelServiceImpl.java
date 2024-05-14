package cn.edu.swust.qd.system.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.system.converter.SLConverter;
import cn.edu.swust.qd.system.mapper.SysSecretLevelMapper;
import cn.edu.swust.qd.system.model.entity.SysSecretLevel;
import cn.edu.swust.qd.system.model.form.SLForm;
import cn.edu.swust.qd.system.model.query.SLPageQuery;
import cn.edu.swust.qd.system.model.vo.SLPageVO;
import cn.edu.swust.qd.system.service.SysSecretLevelService;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author 25055
 * @description 针对表【sys_secret_level(人员密级表)】的数据库操作Service实现
 * @createDate 2024-05-04 14:41:47
 */
@Service
@RequiredArgsConstructor
public class SysSecretLevelServiceImpl extends ServiceImpl<SysSecretLevelMapper, SysSecretLevel>
        implements SysSecretLevelService {

    private final SLConverter slConverter;


    @Override
    public Page<SLPageVO> getSLPage(SLPageQuery queryParams) {
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keywords = queryParams.getKeywords();

        Page<SysSecretLevel> clPage = this.page(new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<SysSecretLevel>()
                        .and(StrUtil.isNotBlank(keywords),
                                wrapper ->
                                        wrapper.like(StrUtil.isNotBlank(keywords), SysSecretLevel::getName, keywords)
                                                .or()
                                                .like(StrUtil.isNotBlank(keywords), SysSecretLevel::getCode, keywords)
                                                .or()
                                                .like(StrUtil.isNotBlank(keywords), SysSecretLevel::getDescription, keywords)
                        ));

        Page<SLPageVO> result = slConverter.entity2VO(clPage);
        return result;
    }

    @Override
    public boolean saveSL(SLForm slForm) {
        Long slId = slForm.getId();
        // 编辑人员密级时，查询是否存在
        SysSecretLevel oldSL = null;
        if (slId != null) {
            oldSL = this.getById(slId);
            Assert.isTrue(oldSL != null, "人员密级不存在");
        }
        String slCode = slForm.getCode();
        long count = this.count(new LambdaQueryWrapper<SysSecretLevel>()
                .ne(slId != null, SysSecretLevel::getId, slId)
                .and(wrapper ->
                        wrapper.eq(SysSecretLevel::getCode, slCode).or().eq(SysSecretLevel::getName, slForm.getName())
                ));
        Assert.isTrue(count == 0, "人员密级名称或人员密级编码已存在，请修改后重试！");

        // 实体转换
        SysSecretLevel sl = slConverter.form2Entity(slForm);
        boolean result = this.saveOrUpdate(sl);

        return result;
    }

    @Override
    public SLForm getSLForm(Long slId) {
        SysSecretLevel entity = this.getById(slId);
        return slConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteSLs(String ids) {
        List<Long> slIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long slId : slIds) {
            SysSecretLevel sl = this.getById(slId);
            Assert.isTrue(sl != null, "人员密级不存在");

            // todo 判断人员密级是否被用户关联

        }
        return true;
    }

    @Override
    public boolean updateSLStatus(Long slId, Integer status) {
        SysSecretLevel sl = this.getById(slId);
        Assert.isTrue(sl != null, "人员密级不存在");

        sl.setStatus(status);
        boolean result = this.updateById(sl);
        return result;
    }

    @Override
    public List<Option> listSLOptions() {
        // 查询数据
        List<SysSecretLevel> slList = this.list(new LambdaQueryWrapper<SysSecretLevel>()
                .select(SysSecretLevel::getId, SysSecretLevel::getName)
                .orderByDesc(SysSecretLevel::getSort));
        // 实体转换
        return slConverter.entities2Options(slList);
    }

    @Override
    public boolean assignSLtoUser(Integer slId, List<Integer> userIds) {
        // todo 实现密级分配
        return false;
    }
}




