package cn.edu.swust.qd.scms.controller.admin;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */

import cn.edu.swust.qd.common.result.PageResult;
import cn.edu.swust.qd.common.result.Result;
import cn.edu.swust.qd.common.web.annotation.PreventDuplicateResubmit;
import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.form.*;
import cn.edu.swust.qd.scms.model.query.*;
import cn.edu.swust.qd.scms.model.vo.*;
import cn.edu.swust.qd.scms.service.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "布局及外形信息管理接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/layouts")
public class ScmsLayoutController {

    private final ScmsAirfoilService airfoilService;
    private final ScmsAirfoilCoordinateService airfoilCoordinateService;

    private final ScmsWingflapConfigService wingflapConfigService;
    private final ScmsWingSectionService wingSectionService;

    private final ScmsWingflapLayoutService wingflapLayoutService;

    private final ScmsAircraftSubService aircraftSubService;

    private final ScmsFasService fasService;
    private final ScmsVehiTypeService vehiTypeService;
    private final ScmsPrototypeTypeService prototypeTypeService;
    private final ScmsAirfoilTypeService airfoilTypeService;

    @Operation(summary = "翼型信息分页列表")
    @GetMapping("/airfoils/page")
    public PageResult<AirfoilPageVO> getAirfoilPage(
            @ParameterObject AirfoilPageQuery queryParams
    ) {
        Page<AirfoilPageVO> result = airfoilService.getAirfoilPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "翼型信息下拉列表")
    @GetMapping("/airfoils/options")
    public Result<List<Option>> getAirfoilOptions() {
        List<Option> list = airfoilService.getAirfoilOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增翼型信息")
    @PostMapping("/airfoils")
    @PreAuthorize("@ss.hasPerm('scms:airfoil:add')")
    @PreventDuplicateResubmit
    public Result addAirfoil(@Valid @RequestBody AirfoilForm airfoilForm) {
        boolean result = airfoilService.saveAirfoil(airfoilForm);
        return Result.judge(result);
    }

    @Operation(summary = "翼型信息表单")
    @GetMapping("/airfoils/{airfoilId}/form")
    public Result<AirfoilForm> getAirfoilForm(
            @Parameter(description = "翼型信息ID") @PathVariable Long airfoilId
    ) {
        AirfoilForm airfoilForm = airfoilService.getAirfoilForm(airfoilId);
        return Result.success(airfoilForm);
    }

    @Operation(summary = "修改翼型信息")
    @PutMapping("/airfoils/{airfoilId}")
    @PreAuthorize("@ss.has('scms:airfoil:edit')")
    public Result updateAirfoil(@Valid @RequestBody AirfoilForm airfoilForm) {
        boolean result = airfoilService.saveAirfoil(airfoilForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除翼型信息")
    @DeleteMapping("/airfoils/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:airfoil:delete')")
    public Result deleteAirfoils(
            @Parameter(description = "翼型信息ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = airfoilService.deleteAirfoils(ids);
        return Result.judge(result);
    }

    @Operation(summary = "翼型坐标分页列表")
    @GetMapping("/airfoil_coordinates/page")
    public PageResult<AirfoilCoordinatePageVO> getAirfoilCoordinatePage(
            @ParameterObject AirfoilCoordinatePageQuery queryParams
    ) {
        Page<AirfoilCoordinatePageVO> result = airfoilCoordinateService.getAirfoilCoordinatePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增翼型坐标")
    @PostMapping("/airfoil_coordinates")
    @PreAuthorize("@ss.hasPerm('scms:airfoil_coordinate:add')")
    @PreventDuplicateResubmit
    public Result addAirfoilCoordinate(@Valid @RequestBody AirfoilCoordinateForm airfoilCoordinateForm) {
        boolean result = airfoilCoordinateService.saveAirfoilCoordinate(airfoilCoordinateForm);
        return Result.judge(result);
    }

    @Operation(summary = "翼型坐标表单")
    @GetMapping("/airfoil_coordninates/{airfoilCoordinationId}/form")
    public Result<AirfoilCoordinateForm> getAirfoilCoordinateForm(
            @Parameter(description = "翼型坐标ID") @PathVariable Long airfoilCoordinateId
    ) {
        AirfoilCoordinateForm airfoilCoordinateForm = airfoilCoordinateService.getAirfoilCoordinateForm(airfoilCoordinateId);
        return Result.success(airfoilCoordinateForm);
    }

    @Operation(summary = "修改翼型坐标")
    @PutMapping("/airfoil_coordinates/{airfoilCoordinateId}")
    @PreAuthorize("@ss.hasPerm('scms:airfoil_coordinate:edit')")
    public Result updateAirfoilCoordination(@Valid @RequestBody AirfoilCoordinateForm airfoilCoordinateForm) {
        boolean result = airfoilCoordinateService.saveAirfoilCoordinate(airfoilCoordinateForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除翼型坐标")
    @DeleteMapping("/airfoil_coordinates/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:airfoil_coordinate:delete')")
    public Result deleteAirfoilCoordinates(
            @Parameter(description = "翼型坐标ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = airfoilCoordinateService.deleteAirfoilCoordinates(ids);
        return Result.judge(result);
    }

    @Operation(summary = "翼舵构型分页列表")
    @GetMapping("/wingflap_configs/page")
    public PageResult<WingflapConfigPageVO> getWingflapConfigPage(
            @ParameterObject WingflapConfigPageQuery queryParams
    ) {
        Page<WingflapConfigPageVO> result = wingflapConfigService.getWingflapConfigPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增翼舵构型")
    @PostMapping("/wingflap_configs")
    @PreAuthorize("@ss.hasPerm('scms:wingflap_config:add')")
    @PreventDuplicateResubmit
    public Result addWingflapConfig(@Valid @RequestBody WingflapConfigForm wingflapConfigForm) {
        boolean result = wingflapConfigService.saveWingflapConfig(wingflapConfigForm);
        return Result.judge(result);
    }

    @Operation(summary = "翼舵构型表单")
    @GetMapping("/wingflap_configs/{wingflapConfigId}/form")
    public Result<WingflapConfigForm> getWingflapConfigForm(
            @Parameter(description = "翼舵构型ID") @PathVariable Long wingflapConfigId
    ) {
        WingflapConfigForm wingflapConfigForm = wingflapConfigService.getWingflapConfigForm(wingflapConfigId);
        return Result.success(wingflapConfigForm);
    }

    @Operation(summary = "修改翼舵构型")
    @PutMapping("/wingflap_configs/{wingflapConfigId}")
    @PreAuthorize("@ss.hasPerm('scms:wingflap_config:edit')")
    public Result updateWingflapConfig(@Valid @RequestBody WingflapConfigForm wingflapConfigForm) {
        boolean result = wingflapConfigService.saveWingflapConfig(wingflapConfigForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除翼舵构型")
    @DeleteMapping("/wingflap_configs/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:wingflap_config:delete')")
    public Result deleteWingflapConfigs(
            @Parameter(description = "翼舵构型ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = wingflapConfigService.deleteWingflapConfigs(ids);
        return Result.judge(result);
    }

    @Operation(summary = "翼舵截面信息分页列表")
    @GetMapping("/wing_sections/page")
    public PageResult<WingSectionPageVO> getWingSectionPage(
            @ParameterObject WingSectionPageQuery queryParams
    ) {
        Page<WingSectionPageVO> result = wingSectionService.getWingSectionPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增翼舵截面信息")
    @PostMapping("/wing_sections")
    @PreAuthorize("@ss.hasPerm('scms:wing_section:add')")
    @PreventDuplicateResubmit
    public Result addWingSection(@Valid @RequestBody WingSectionForm wingSectionForm) {
        boolean result = wingSectionService.saveWingSection(wingSectionForm);
        return Result.judge(result);
    }

    @Operation(summary = "翼舵截面信息表单")
    @GetMapping("/wing_sections/{wingSectionId}/form")
    public Result<WingSectionForm> getWingSectionForm(
            @Parameter(description = "翼舵截面信息ID") @PathVariable Long wingSectionId
    ) {
        WingSectionForm wingSectionForm = wingSectionService.getWingSectionForm(wingSectionId);
        return Result.success(wingSectionForm);
    }

    @Operation(summary = "修改翼舵截面信息")
    @PutMapping("/wing_sections/{wingSectionId}")
    @PreAuthorize("@ss.hasPerm('scms:wing_section:edit')")
    public Result updateWingSection(@Valid @RequestBody WingSectionForm wingSectionForm) {
        boolean result = wingSectionService.saveWingSection(wingSectionForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除翼舵截面信息")
    @DeleteMapping("/wing_sections/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:wing_section:delete')")
    public Result deleteWingSections(
            @Parameter(description = "翼舵截面信息ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = wingSectionService.deleteWingSections(ids);
        return Result.judge(result);
    }


    @Operation(summary = "翼舵布局分页列表")
    @GetMapping("/wingflap_layouts/page")
    public PageResult<WingflapLayoutPageVO> getWingflapLayoutPage(
            @ParameterObject WingflapLayoutPageQuery queryParams
    ) {
        Page<WingflapLayoutPageVO> result = wingflapLayoutService.getWingflapLayoutPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增翼舵布局")
    @PostMapping("/wingflap_layouts")
    @PreAuthorize("@ss.hasPerm('scms:wingflap_layout:add')")
    @PreventDuplicateResubmit
    public Result addWingflapLayout(@Valid @RequestBody WingflapLayoutForm wingflapLayoutForm) {
        boolean result = wingflapLayoutService.saveWingflapLayout(wingflapLayoutForm);
        return Result.judge(result);
    }

    @Operation(summary = "翼舵布局表单")
    @GetMapping("/wingflap_layouts/{wingflapLayoutId}/form")
    public Result<WingflapLayoutForm> getWingflapLayoutForm(
            @Parameter(description = "翼舵布局ID") @PathVariable Long wingflapLayoutId
    ) {
        WingflapLayoutForm wingflapLayoutForm = wingflapLayoutService.getWingflapLayoutForm(wingflapLayoutId);
        return Result.success(wingflapLayoutForm);
    }

    @Operation(summary = "修改翼舵布局")
    @PutMapping("/wingflap_layouts/{wingflapLayoutId}")
    @PreAuthorize("@ss.hasPerm('scms:wingflap_layout:edit')")
    public Result updateWingflapLayout(@Valid @RequestBody WingflapLayoutForm wingflapLayoutForm) {
        boolean result = wingflapLayoutService.saveWingflapLayout(wingflapLayoutForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除翼舵布局")
    @DeleteMapping("/wingflap_layouts/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:wingflap_layout:delete')")
    public Result deleteWingflapLayouts(
            @Parameter(description = "翼舵布局ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = wingflapLayoutService.deleteWingflapLayouts(ids);
        return Result.judge(result);
    }

    @Operation(summary = "飞行器子级分页表单")
    @GetMapping("/aircraft_subs/page")
    public PageResult<AircraftSubPageVO> getAircraftSubPage(
            @ParameterObject AircraftSubPageQuery queryParams
    ) {
        Page<AircraftSubPageVO> result = aircraftSubService.getAircraftSubPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "飞行器子级下拉列表")
    @GetMapping("/aircraft_subs/options")
    public Result<List<Option>> getAircraftSubOptions() {
        List<Option> list = aircraftSubService.getAircraftSubOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增飞行器子级")
    @PostMapping("/aircraft_subs")
    @PreAuthorize("@ss.hasPerm('scms:aircraft_sub:add')")
    @PreventDuplicateResubmit
    public Result addAircraftSub(@Valid @RequestBody AircraftSubForm aircraftSubForm) {
        boolean result = aircraftSubService.saveAircraftSub(aircraftSubForm);
        return Result.judge(result);
    }

    @Operation(summary = "飞行器子级表单")
    @GetMapping("/aircraft_subs/{aircraftSubId}/form")
    public Result<AircraftSubForm> getAircraftSubForm(
            @Parameter(description = "飞行器子级ID") @PathVariable Long aircraftSubId
    ) {
        AircraftSubForm aircraftSubForm = aircraftSubService.getAircraftSubForm(aircraftSubId);
        return Result.success(aircraftSubForm);
    }

    @Operation(summary = "修改飞行器子级")
    @PutMapping("/aircraft_subs/{aircraftSubId}")
    @PreAuthorize("@ss.hasPerm('scms:aircraft_sub:edit')")
    public Result updateAircraftSub(@Valid @RequestBody AircraftSubForm aircraftSubForm) {
        boolean result = aircraftSubService.saveAircraftSub(aircraftSubForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除飞行器子级")
    @DeleteMapping("/aircraft_subs/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:aircraft_sub:delete')")
    public Result deleteAircraftSubs(
            @Parameter(description = "飞行器子级ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = aircraftSubService.deleteAircraftSubs(ids);
        return Result.judge(result);
    }

    @Operation(summary = "FAS细目类别分页列表")
    @GetMapping("/fases/page")
    public PageResult<FasPageVO> getFasPage(
            @ParameterObject FasPageQuery queryParams
    ) {
        Page<FasPageVO> result = fasService.getFasPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "FAS细目类别下拉列表")
    @GetMapping("/fases/options")
    public Result<List<Option>> getFasOptions() {
        List<Option> list = fasService.getFasOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增FAS细目类别")
    @PostMapping("/fases")
    @PreAuthorize("@ss.hasPerm('scms:fas:add')")
    @PreventDuplicateResubmit
    public Result addFas(@Valid @RequestBody FasForm fasForm) {
        boolean result = fasService.saveFas(fasForm);
        return Result.judge(result);
    }

    @Operation(summary = "FAS细目类别表单")
    @GetMapping("/fases/{fasId}/form")
    public Result<FasForm> getFasForm(
            @Parameter(description = "FAS细目类别ID") @PathVariable Long fasId
    ) {
        FasForm fasForm = fasService.getFasForm(fasId);
        return Result.success(fasForm);
    }

    @Operation(summary = "修改FAS细目类别")
    @PutMapping("/fases/{fasId}")
    @PreAuthorize("@ss.hasPerm('scms:fas:edit')")
    public Result updateFas(@Valid @RequestBody FasForm fasForm) {
        boolean result = fasService.saveFas(fasForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除FAS细目类别")
    @DeleteMapping("/fases/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:fas:delete')")
    public Result deleteFases(
            @Parameter(description = "FAS细目类别ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = fasService.deleteFases(ids);
        return Result.judge(result);
    }

    @Operation(summary = "气动布局类型分页列表")
    @GetMapping("/vehi_types/page")
    public PageResult<VehiTypePageVO> getVehiTypePage(
            @ParameterObject VehiTypePageQuery queryParams
    ) {
        Page<VehiTypePageVO> result = vehiTypeService.getVehiTypePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "气动布局类型下拉列表")
    @GetMapping("/vehi_types/options")
    public Result<List<Option>> getVehiTypeOptions() {
        List<Option> list = vehiTypeService.getVehiTypeOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增气动布局类型")
    @PostMapping("/vehi_types")
    @PreAuthorize("@ss.hasPerm('scms:vehi_type:add')")
    @PreventDuplicateResubmit
    public Result addVehiType(@Valid @RequestBody VehiTypeForm vehiTypeForm) {
        boolean result = vehiTypeService.saveVehiType(vehiTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "气动布局类型表单")
    @GetMapping("/vehi_types/{vehiTypeId}/form")
    public Result<VehiTypeForm> getVehiTypeForm(
            @Parameter(description = "气动布局类型ID") @PathVariable Long vehiTypeId
    ) {
        VehiTypeForm vehiTypeForm = vehiTypeService.getVehiTypeForm(vehiTypeId);
        return Result.success(vehiTypeForm);
    }

    @Operation(summary = "修改气动布局类型")
    @PutMapping("/vehi_types/{vehiTypeId}")
    @PreAuthorize("@ss.hasPerm('scms:vehi_type:edit')")
    public Result updateVehiType(@Valid @RequestBody VehiTypeForm vehiTypeForm) {
        boolean result = vehiTypeService.saveVehiType(vehiTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除气动布局类型")
    @DeleteMapping("/vehi_types/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:vehi_type:delete')")
    public Result deleteVehiTypes(
            @Parameter(description = "气动布局类型ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = vehiTypeService.deleteVehiTypes(ids);
        return Result.judge(result);
    }

    @Operation(summary = "原型类型分页列表")
    @GetMapping("/prototypes/page")
    public PageResult<PrototypeTypePageVO> getPrototypePage(
            @ParameterObject PrototypeTypePageQuery queryParams
    ) {
        Page<PrototypeTypePageVO> result = prototypeTypeService.getPrototypeTypePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "原型类型下拉列表")
    @GetMapping("/prototypes/options")
    public Result<List<Option>> getPrototypeOptions() {
        List<Option> list = prototypeTypeService.getPrototypeTypeOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增原型类型")
    @PostMapping("/prototypes")
    @PreAuthorize("@ss.hasPerm('scms:prototype:add')")
    @PreventDuplicateResubmit
    public Result addPrototype(@Valid @RequestBody PrototypeTypeForm prototypeTypeForm) {
        boolean result = prototypeTypeService.savePrototypeType(prototypeTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "原型类型表单")
    @GetMapping("/prototypes/{prototypeTypeId}/form")
    public Result<PrototypeTypeForm> getPrototypeForm(
            @Parameter(description = "原型类型ID") @PathVariable Long prototypeTypeId
    ) {
        PrototypeTypeForm prototypeTypeForm = prototypeTypeService.getPrototypeTypeForm(prototypeTypeId);
        return Result.success(prototypeTypeForm);
    }

    @Operation(summary = "修改原型类型")
    @PutMapping("/prototypes/{prototypeTypeId}")
    @PreAuthorize("@ss.hasPerm('scms:prototype:edit')")
    public Result updatePrototype(@Valid @RequestBody PrototypeTypeForm prototypeTypeForm) {
        boolean result = prototypeTypeService.savePrototypeType(prototypeTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除原型类型")
    @DeleteMapping("/prototypes/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:prototype:delete')")
    public Result deletePrototypes(
            @Parameter(description = "原型类型ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = prototypeTypeService.deletePrototypeTypes(ids);
        return Result.judge(result);
    }

    @Operation(summary = "翼型类型分页列表")
    @GetMapping("/airfiol_types/page")
    public PageResult<AirfoilTypePageVO> getAirfoilTypePage(
            @ParameterObject AirfoilTypePageQuery queryParams
    ) {
        Page<AirfoilTypePageVO> result = airfoilTypeService.getAirfoilTypePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "翼型类型下拉列表")
    @GetMapping("/airfiol_types/options")
    public Result<List<Option>> getAirfoilTypeOptions() {
        List<Option> list = airfoilTypeService.getAirfoilTypeOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增翼型类型")
    @PostMapping("/airfiol_types")
    @PreAuthorize("@ss.hasPerm('scms:airfoil_type:add')")
    @PreventDuplicateResubmit
    public Result addAirfoilType(@Valid @RequestBody AirfoilTypeForm airfoilTypeForm) {
        boolean result = airfoilTypeService.saveAirfoilType(airfoilTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "翼型类型表单")
    @GetMapping("/airfiol_types/{airfoilTypeId}/form")
    public Result<AirfoilTypeForm> getAirfoilTypeForm(
            @Parameter(description = "翼型类型ID") @PathVariable Long airfoilTypeId
    ) {
        AirfoilTypeForm airfoilTypeForm = airfoilTypeService.getAirfoilTypeForm(airfoilTypeId);
        return Result.success(airfoilTypeForm);
    }

    @Operation(summary = "修改翼型类型")
    @PutMapping("/airfiol_types/{airfoilTypeId}")
    @PreAuthorize("@ss.hasPerm('scms:airfoil_type:edit')")
    public Result updateAirfoilType(@Valid @RequestBody AirfoilTypeForm airfoilTypeForm) {
        boolean result = airfoilTypeService.saveAirfoilType(airfoilTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除翼型类型")
    @DeleteMapping("/airfiol_types/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:airfoil_type:delete')")
    public Result deleteAirfoilTypes(
            @Parameter(description = "翼型类型ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = airfoilTypeService.deleteAirfoilTypes(ids);
        return Result.judge(result);
    }
}
