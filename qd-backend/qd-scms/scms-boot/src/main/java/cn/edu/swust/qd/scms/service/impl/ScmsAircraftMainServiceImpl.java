package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.scms.converter.ScmsAircraftMainConverter;
import cn.edu.swust.qd.scms.mapper.ScmsAircraftMainMapper;
import cn.edu.swust.qd.scms.model.bo.AircraftMainBO;
import cn.edu.swust.qd.scms.model.entity.ScmsAircraftMain;
import cn.edu.swust.qd.scms.model.form.AircraftMainForm;
import cn.edu.swust.qd.scms.model.query.AircraftMainPageQuery;
import cn.edu.swust.qd.scms.model.vo.AircraftMainPageVO;
import cn.edu.swust.qd.scms.service.ScmsAircraftMainService;
import cn.edu.swust.qd.scms.service.ScmsAircraftSecService;
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
 * @description 针对表【scms_aircraft_main(飞行器主信息表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsAircraftMainServiceImpl extends ServiceImpl<ScmsAircraftMainMapper, ScmsAircraftMain>
        implements ScmsAircraftMainService {

    private final ScmsAircraftMainConverter scmsaircraftMainConverter;

    private final ScmsAircraftSecService ScmsAircraftSecService;

    @Override
    public Page<AircraftMainPageVO> getAircraftMainPage(AircraftMainPageQuery queryParams) {
        Page<AircraftMainBO> aircraftMainPage = this.baseMapper.getAircraftMainPage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize())
        );

        Page<AircraftMainPageVO> page = scmsaircraftMainConverter.bo2VO(aircraftMainPage);
        return page;
    }

    //    @Deprecated
//    private QueryWrapper<ScmsAircraftMain> getQueryWrapper(AircraftMainPageQuery queryParams) {
//        QueryWrapper<ScmsAircraftMain> queryWrapper = new QueryWrapper<>();
//        if (queryParams == null) {
//            return queryWrapper;
//        }
//
//        Long vehiTypeId = queryParams.getVehiTypeId();
//        Long fuelTypeId = queryParams.getFuelTypeId();
//        Long vehiId = queryParams.getVehiId();
//        String vehiName = queryParams.getVehiName();
//        String designDepartment = queryParams.getDesignDepartment();
//        String designPerson = queryParams.getDesignPerson();
//        Date designDate = queryParams.getDesignDate();
//        Long engineTypeId = queryParams.getEngineTypeId();
//        Long moduleNum = queryParams.getModuleNum();
//        String launchPlatform = queryParams.getLaunchPlatform();
//        Long stagesLv = queryParams.getStagesLv();
//        String machTurn = queryParams.getMachTurn();
//        String altiTurn = queryParams.getAltiTurn();
//        String machCruise = queryParams.getMachCruise();
//        String altiCruise = queryParams.getAltiCruise();
//        String machDive = queryParams.getMachDive();
//        String altiDive = queryParams.getAltiDive();
//        String range = queryParams.getRange();
//        Long createUser = queryParams.getCreateUser();
//
//        // 拼接查询条件
//        queryWrapper.like(StrUtil.isNotBlank(vehiName), "vehi_name", vehiName);
//        queryWrapper.like(StrUtil.isNotBlank(designDepartment), "design_department", designDepartment);
//        queryWrapper.like(StrUtil.isNotBlank(designPerson), "design_person", designPerson);
//        queryWrapper.like(StrUtil.isNotBlank(launchPlatform), "launch_platform", launchPlatform);
//        queryWrapper.like(StrUtil.isNotBlank(machTurn), "mach_turn", machTurn);
//        queryWrapper.like(StrUtil.isNotBlank(altiTurn), "alti_turn", altiTurn);
//        queryWrapper.like(StrUtil.isNotBlank(machCruise), "mach_cruise", machCruise);
//        queryWrapper.like(StrUtil.isNotBlank(altiCruise), "alti_cruise", altiCruise);
//        queryWrapper.like(StrUtil.isNotBlank(machDive), "mach_dive", machDive);
//        queryWrapper.like(StrUtil.isNotBlank(altiDive), "alti_dive", altiDive);
//        queryWrapper.like(StrUtil.isNotBlank(range), "range", range);
//
//        queryWrapper.eq(ObjectUtil.isNotEmpty(vehiTypeId), "vehi_type_id", vehiTypeId);
//        queryWrapper.eq(ObjectUtil.isNotEmpty(fuelTypeId), "fuel_type_id", fuelTypeId);
//        queryWrapper.eq(ObjectUtil.isNotEmpty(vehiId), "vehi_id", vehiId);
//        queryWrapper.eq(ObjectUtil.isNotEmpty(engineTypeId), "engine_type_id", engineTypeId);
//        queryWrapper.eq(ObjectUtil.isNotEmpty(moduleNum), "moduleNum", moduleNum);
//        queryWrapper.eq(ObjectUtil.isNotEmpty(stagesLv), "stages_lv", stagesLv);
//        queryWrapper.eq(ObjectUtil.isNotEmpty(createUser), "create_user", createUser);
//        queryWrapper.ge(ObjectUtil.isNotEmpty(designDate), "design_date", designDate);
//
//        return queryWrapper;
//    }
    @Override
    public boolean saveAircraftMain(AircraftMainForm aircraftMainForm) {
        Long aircraftMainId = aircraftMainForm.getId();
        // 编辑时，查询是否存在
        ScmsAircraftMain oldAircraftMain = null;
        if (aircraftMainId != null) {
            oldAircraftMain = this.getById(aircraftMainId);
            Assert.isTrue(oldAircraftMain != null, "飞行器主信息不存在");
        }

        // todo 校验逻辑

        // 实体转换
        ScmsAircraftMain entity = scmsaircraftMainConverter.form2Entity(aircraftMainForm);
        boolean result = this.save(entity);

        return result;
    }

    @Override
    public AircraftMainForm getAircraftMainForm(Long aircraftMainId) {
        ScmsAircraftMain entity = this.getById(aircraftMainId);
        return scmsaircraftMainConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteAircraftMains(String ids) {
        List<Long> amIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long amId : amIds) {
            ScmsAircraftMain aircraftMain = this.getById(amId);
            Assert.isTrue(aircraftMain != null, "飞行器主信息不存在");

            // 判断主信息下是否有次级信息
            boolean hasSec = ScmsAircraftSecService.hasSec(amId);
            Assert.isTrue(hasSec, "飞行器主信息【{}】包含次级信息，请先清空次级信息后删除", aircraftMain.getVehiId());

            this.removeById(amId);
        }
        return true;
    }

    @Override
    public boolean isEngineTypeReferenced(Long engineTypeId) {
        return this.count(new LambdaQueryWrapper<ScmsAircraftMain>().eq(ScmsAircraftMain::getEngineTypeId, engineTypeId)) > 0;
    }

    @Override
    public boolean isVehiTypeReferenced(Long vehiTypeId) {
        return this.count(new LambdaQueryWrapper<ScmsAircraftMain>().eq(ScmsAircraftMain::getVehiTypeId, vehiTypeId)) > 0;
    }
}




