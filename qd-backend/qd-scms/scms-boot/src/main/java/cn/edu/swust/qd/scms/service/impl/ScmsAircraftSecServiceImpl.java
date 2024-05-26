package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.scms.converter.ScmsAircraftSecConverter;
import cn.edu.swust.qd.scms.mapper.ScmsAircraftSecMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsAircraftSec;
import cn.edu.swust.qd.scms.model.form.AircraftSecForm;
import cn.edu.swust.qd.scms.model.query.AircraftSecPageQuery;
import cn.edu.swust.qd.scms.model.vo.AircraftSecPageVO;
import cn.edu.swust.qd.scms.service.ScmsAircraftSecService;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_aircraft_sec(飞行器次级信息)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsAircraftSecServiceImpl extends ServiceImpl<ScmsAircraftSecMapper, ScmsAircraftSec>
        implements ScmsAircraftSecService {

    private final ScmsAircraftSecConverter scmsAircraftSecConverter;


    @Override
    public Page<AircraftSecPageVO> getAircraftSecPage(AircraftSecPageQuery queryParams) {
        Page<ScmsAircraftSec> aircraftSecPage = this.page(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()));

        Page<AircraftSecPageVO> page = scmsAircraftSecConverter.entity2VO(aircraftSecPage);
        return page;
    }


    @Override
    public boolean saveAircraftSec(AircraftSecForm aircraftSecForm) {
        Long aircraftSecId = aircraftSecForm.getId();
        ScmsAircraftSec oldAircraftSec = null;
        if (aircraftSecId != null) {
            oldAircraftSec = this.getById(aircraftSecId);
            Assert.isTrue(oldAircraftSec != null, "飞行器次级信息为空");
        }

        ScmsAircraftSec scmsAircraftSec = scmsAircraftSecConverter.form2Entity(aircraftSecForm);
        boolean result = this.save(scmsAircraftSec);

        return result;
    }

    @Override
    public AircraftSecForm getAircraftSecForm(Long aircraftSecId) {
        ScmsAircraftSec scmsAircraftSec = this.getById(aircraftSecId);
        return scmsAircraftSecConverter.entity2Form(scmsAircraftSec);
    }

    @Override
    public boolean deleteAircraftSecs(String ids) {
        List<Long> asIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long asId : asIds) {
            ScmsAircraftSec aircraftSec = this.getById(asId);
            Assert.isTrue(aircraftSec != null, "飞行器次级信息不存在");

            this.removeById(asId);
        }
        return true;
    }

    @Override
    public boolean hasSec(Long amId) {
        return this.count(new QueryWrapper<ScmsAircraftSec>().eq("aircraft_main_id", amId)) > 0;
    }

    @Override
    public boolean isSubReferenced(Long asId) {
        return this.count(new QueryWrapper<ScmsAircraftSec>().eq("id", asId)) > 0;
    }
}




