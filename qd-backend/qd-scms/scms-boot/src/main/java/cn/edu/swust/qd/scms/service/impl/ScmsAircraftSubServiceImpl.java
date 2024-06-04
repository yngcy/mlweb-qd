package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsAircraftSubConverter;
import cn.edu.swust.qd.scms.mapper.ScmsAircraftSubMapper;
import cn.edu.swust.qd.scms.model.bo.AircraftSubBO;
import cn.edu.swust.qd.scms.model.entity.ScmsAircraftSub;
import cn.edu.swust.qd.scms.model.form.AircraftSubForm;
import cn.edu.swust.qd.scms.model.query.AircraftSubPageQuery;
import cn.edu.swust.qd.scms.model.vo.AircraftSubPageVO;
import cn.edu.swust.qd.scms.service.ScmsAircraftSecService;
import cn.edu.swust.qd.scms.service.ScmsAircraftSubService;
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
 * @description 针对表【scms_aircraft_sub(飞行器子级信息表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsAircraftSubServiceImpl extends ServiceImpl<ScmsAircraftSubMapper, ScmsAircraftSub>
        implements ScmsAircraftSubService {

    private final ScmsAircraftSubConverter scmsAircraftSubConverter;

    private final ScmsAircraftSecService scmsAircraftSecService;

    @Override
    public String getNameById(Long id) {
        return this.getById(id).getName();
    }

    @Override
    public Page<AircraftSubPageVO> getAircraftSubPage(AircraftSubPageQuery queryParams) {
        Page<AircraftSubBO> aircraftSubPage = this.baseMapper.getAircraftSubPage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()));
        Page<AircraftSubPageVO> page = scmsAircraftSubConverter.bo2VO(aircraftSubPage);
        return page;
    }


    @Override
    public List<Option> getAircraftSubOptions() {
        List<ScmsAircraftSub> aircraftSubList = this.list(
                new LambdaQueryWrapper<ScmsAircraftSub>()
                        .select(ScmsAircraftSub::getId, ScmsAircraftSub::getName));
        return scmsAircraftSubConverter.entities2Options(aircraftSubList);
    }

    @Override
    public boolean saveAircraftSub(AircraftSubForm aircraftSubForm) {
        Long aircraftSubId = aircraftSubForm.getId();
        ScmsAircraftSub oldAircraftSub = null;
        if (aircraftSubId != null) {
            oldAircraftSub = this.getById(aircraftSubId);
            Assert.isTrue(oldAircraftSub != null, "飞行器子级不存在");
        }

        ScmsAircraftSub aircraftSub = scmsAircraftSubConverter.form2Entity(aircraftSubForm);
        boolean result = this.saveOrUpdate(aircraftSub);

        return result;
    }

    @Override
    public AircraftSubForm getAircraftSubForm(Long aircraftSubId) {
        ScmsAircraftSub aircraftSub = this.getById(aircraftSubId);
        return scmsAircraftSubConverter.entity2Form(aircraftSub);
    }

    @Override
    public boolean deleteAircraftSubs(String ids) {
        List<Long> asIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long asId : asIds) {
            ScmsAircraftSub aircraftSub = this.getById(asId);
            Assert.isTrue(aircraftSub != null, "飞行器子级不存在");

            // 判断是否有关联次级信息
            boolean isReferenced = scmsAircraftSecService.isSubReferenced(asId);
            Assert.isTrue(!isReferenced, "存在关联次级信息，无法删除");
        }
        return true;
    }
}




