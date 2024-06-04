package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsTrajectoryConverter;
import cn.edu.swust.qd.scms.mapper.ScmsTrajectoryMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsTrajectory;
import cn.edu.swust.qd.scms.model.form.TrajectoryForm;
import cn.edu.swust.qd.scms.model.query.TrajectoryPageQuery;
import cn.edu.swust.qd.scms.model.vo.TrajectoryPageVO;
import cn.edu.swust.qd.scms.service.ScmsTrajectoryService;
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
 * @description 针对表【scms_trajectory(弹道信息表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsTrajectoryServiceImpl extends ServiceImpl<ScmsTrajectoryMapper, ScmsTrajectory>
        implements ScmsTrajectoryService {

    private final ScmsTrajectoryConverter scmsTrajectoryConverter;

    @Override
    public Page<TrajectoryPageVO> getTrajectoryPage(TrajectoryPageQuery queryParams) {
        Page<ScmsTrajectory> entity = this.page(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        return scmsTrajectoryConverter.entity2VO(entity);
    }


    @Override
    public List<Option> getTrajectoryOptions() {
        List<ScmsTrajectory> entities = this.list(new LambdaQueryWrapper<ScmsTrajectory>()
                .select(ScmsTrajectory::getId, ScmsTrajectory::getName));
        return scmsTrajectoryConverter.entities2Options(entities);
    }

    @Override
    public boolean saveTrajectory(TrajectoryForm trajectoryForm) {
        Long trajectoryId = trajectoryForm.getId();
        ScmsTrajectory oldTrajectory = null;
        if (trajectoryId != null) {
            oldTrajectory = this.getById(trajectoryId);
            Assert.isTrue(oldTrajectory != null, "弹道信息不存在");
        }

        ScmsTrajectory entity = scmsTrajectoryConverter.form2Entity(trajectoryForm);
        boolean result = this.saveOrUpdate(entity);
        return result;
    }

    @Override
    public TrajectoryForm getTrajectoryForm(Long trajectoryId) {
        ScmsTrajectory entity = this.getById(trajectoryId);
        return scmsTrajectoryConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteTrajectories(String ids) {
        List<Long> trajectoryIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long trajectoryId : trajectoryIds) {
            ScmsTrajectory trajectory = this.getById(trajectoryId);
            Assert.isTrue(trajectory != null, "弹道信息不存在");

            this.removeById(trajectory);
        }
        return true;
    }
}




