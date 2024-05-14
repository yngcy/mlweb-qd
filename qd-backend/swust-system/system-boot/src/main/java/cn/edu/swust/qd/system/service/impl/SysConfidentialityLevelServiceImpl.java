package cn.edu.swust.qd.system.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.system.converter.CLConverter;
import cn.edu.swust.qd.system.mapper.SysConfidentialityLevelMapper;
import cn.edu.swust.qd.system.model.entity.SysConfidentialityLevel;
import cn.edu.swust.qd.system.model.form.CLForm;
import cn.edu.swust.qd.system.model.query.CLPageQuery;
import cn.edu.swust.qd.system.model.vo.CLPageVO;
import cn.edu.swust.qd.system.service.SysConfidentialityLevelService;
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
 * @description 针对表【sys_confidentiality_level(数据密级表)】的数据库操作Service实现
 * @createDate 2024-05-04 14:41:11
 */
@Service
@RequiredArgsConstructor
public class SysConfidentialityLevelServiceImpl extends ServiceImpl<SysConfidentialityLevelMapper, SysConfidentialityLevel>
        implements SysConfidentialityLevelService {

    private final CLConverter clConverter;

    @Override
    public Page<CLPageVO> getCLPage(CLPageQuery queryParams) {
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keywords = queryParams.getKeywords();

        Page<SysConfidentialityLevel> clPage = this.page(new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<SysConfidentialityLevel>()
                        .and(StrUtil.isNotBlank(keywords),
                                wrapper ->
                                        wrapper.like(StrUtil.isNotBlank(keywords), SysConfidentialityLevel::getName, keywords)
                                                .or()
                                                .like(StrUtil.isNotBlank(keywords), SysConfidentialityLevel::getCode, keywords)
                                                .or()
                                                .like(StrUtil.isNotBlank(keywords), SysConfidentialityLevel::getDescription, keywords)
                        ));

        Page<CLPageVO> result = clConverter.entity2VO(clPage);
        return result;
    }

    @Override
    public boolean saveCL(CLForm clForm) {

        Long clId = clForm.getId();
        // 编辑数据密级时，查询是否存在
        SysConfidentialityLevel oldCL = null;
        if (clId != null) {
            oldCL = this.getById(clId);
            Assert.isTrue(oldCL != null, "数据密级不存在");
        }
        String clCode = clForm.getCode();
        long count = this.count(new LambdaQueryWrapper<SysConfidentialityLevel>()
                .ne(clId != null, SysConfidentialityLevel::getId, clId)
                .and(wrapper ->
                        wrapper.eq(SysConfidentialityLevel::getCode, clCode).or().eq(SysConfidentialityLevel::getName, clForm.getName())
                ));
        Assert.isTrue(count == 0, "数据密级名称或数据密级编码已存在，请修改后重试！");

        // 实体转换
        SysConfidentialityLevel cl = clConverter.form2Entity(clForm);
        boolean result = this.saveOrUpdate(cl);

        return result;
    }

    @Override
    public CLForm getCLForm(Long clId) {
        SysConfidentialityLevel entity = this.getById(clId);
        return clConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteCLs(String ids) {
        List<Long> clIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long clId : clIds) {
            SysConfidentialityLevel cl = this.getById(clId);
            Assert.isTrue(cl != null, "数据密级不存在");

            // todo 判断数据密级是否被用户关联

        }
        return true;
    }

    @Override
    public boolean updateCLStatus(Long clId, Integer status) {
        SysConfidentialityLevel cl = this.getById(clId);
        Assert.isTrue(cl != null, "数据密级不存在");

        cl.setStatus(status);
        boolean result = this.updateById(cl);
        return result;
    }

    @Override
    public List<Option> listCLOptions() {
        // 查询数据
        List<SysConfidentialityLevel> clList = this.list(new LambdaQueryWrapper<SysConfidentialityLevel>()
                .select(SysConfidentialityLevel::getId, SysConfidentialityLevel::getName)
                .orderByDesc(SysConfidentialityLevel::getSort));
        // 实体转换
        return clConverter.entities2Options(clList);
    }

    @Override
    public boolean assignClToData(Integer clId, List<Integer> userIds) {
        // todo 实现密级分配
        return false;
    }
}




