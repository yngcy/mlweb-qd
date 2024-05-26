package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsFlightModeConverter;
import cn.edu.swust.qd.scms.mapper.ScmsFlightModeMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsFlightMode;
import cn.edu.swust.qd.scms.model.form.FlightModeForm;
import cn.edu.swust.qd.scms.model.query.FlightModePageQuery;
import cn.edu.swust.qd.scms.model.vo.FlightModePageVO;
import cn.edu.swust.qd.scms.service.ScmsFlightModeService;
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
 * @description 针对表【scms_flight_mode(飞行试验方式表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsFlightModeServiceImpl extends ServiceImpl<ScmsFlightModeMapper, ScmsFlightMode>
        implements ScmsFlightModeService {

    private final ScmsFlightModeConverter scmsFlightModeConverter;

    @Override
    public Page<FlightModePageVO> getFlightModePage(FlightModePageQuery queryParams) {
        Page<ScmsFlightMode> entity = this.page(new Page(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        return scmsFlightModeConverter.entity2VO(entity);
    }


    @Override
    public List<Option> getFlightModeOptions() {
        List<ScmsFlightMode> entities = this.list(new LambdaQueryWrapper<ScmsFlightMode>()
                .select(ScmsFlightMode::getId, ScmsFlightMode::getName));
        return scmsFlightModeConverter.entities2Options(entities);
    }

    @Override
    public boolean saveFlightMode(FlightModeForm flightModeForm) {
        Long flightModeId = flightModeForm.getId();
        ScmsFlightMode oldFlightMode = null;
        if (flightModeId != null) {
            oldFlightMode = this.getById(flightModeId);
            Assert.isTrue(oldFlightMode != null, "飞行试验方式不存在");
        }

        ScmsFlightMode flightMode = scmsFlightModeConverter.form2Entity(flightModeForm);
        boolean result = this.save(flightMode);

        return result;
    }

    @Override
    public FlightModeForm getFlightModeForm(Long flightModeId) {
        ScmsFlightMode entity = this.getById(flightModeId);
        return scmsFlightModeConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteFlightModes(String ids) {
        List<Long> flightModeIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long flightModeId : flightModeIds) {
            ScmsFlightMode flightMode = this.getById(flightModeId);
            Assert.isTrue(flightMode != null, "飞行试验方式不存在");

            this.removeById(flightMode);
        }

        return true;
    }
}




