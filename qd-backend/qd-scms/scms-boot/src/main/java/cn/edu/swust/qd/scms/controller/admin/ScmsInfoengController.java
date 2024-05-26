package cn.edu.swust.qd.scms.controller.admin;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */

import cn.edu.swust.qd.common.result.PageResult;
import cn.edu.swust.qd.common.result.Result;
import cn.edu.swust.qd.common.web.annotation.PreventDuplicateResubmit;
import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.form.EngineTypeForm;
import cn.edu.swust.qd.scms.model.form.FireModeForm;
import cn.edu.swust.qd.scms.model.form.FuelTypeForm;
import cn.edu.swust.qd.scms.model.form.InjModeForm;
import cn.edu.swust.qd.scms.model.query.EngineTypePageQuery;
import cn.edu.swust.qd.scms.model.query.FireModePageQuery;
import cn.edu.swust.qd.scms.model.query.FuelTypePageQuery;
import cn.edu.swust.qd.scms.model.query.InjModePageQuery;
import cn.edu.swust.qd.scms.model.vo.EngineTypePageVO;
import cn.edu.swust.qd.scms.model.vo.FireModePageVO;
import cn.edu.swust.qd.scms.model.vo.FuelTypePageVO;
import cn.edu.swust.qd.scms.model.vo.InjModePageVO;
import cn.edu.swust.qd.scms.service.ScmsEngineTypeService;
import cn.edu.swust.qd.scms.service.ScmsFireModeService;
import cn.edu.swust.qd.scms.service.ScmsFuelTypeService;
import cn.edu.swust.qd.scms.service.ScmsInjModeService;
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

@Tag(name = "发动机信息管理接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/infoengs")
public class ScmsInfoengController {

    private final ScmsEngineTypeService engineTypeService;

    private final ScmsFuelTypeService fuelTypeService;

    private final ScmsInjModeService injModeService;

    private final ScmsFireModeService fireModeService;

    @Operation(summary = "发动机类型分页列表")
    @GetMapping("/engine_types/page")
    public PageResult<EngineTypePageVO> getEngineTypePage(
            @ParameterObject EngineTypePageQuery queryParams
    ) {
        Page<EngineTypePageVO> result = engineTypeService.getEngineTypePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "发动机类型下拉列表")
    @GetMapping("/engine_types/options")
    public Result<List<Option>> getEngineTypeOptions() {
        List<Option> list = engineTypeService.getEngineTypeOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增发动机类型")
    @PostMapping("/engine_types")
    @PreAuthorize("@ss.hasPerm('scms:engine_type:add')")
    @PreventDuplicateResubmit
    public Result addEngineType(@Valid @RequestBody EngineTypeForm engineTypeForm) {
        boolean result = engineTypeService.saveEngineType(engineTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "发动机类型表单")
    @GetMapping("/engine_types/{engineTypeId}/form")
    public Result<EngineTypeForm> getEngineTypeForm(
            @Parameter(description = "发动机类型ID") @PathVariable Long engineTypeId
    ) {
        EngineTypeForm engineTypeForm = engineTypeService.getEngineTypeForm(engineTypeId);
        return Result.success(engineTypeForm);
    }

    @Operation(summary = "修改发动机类型")
    @PutMapping("/engine_types/{engineTypeId}")
    @PreAuthorize("@ss.hasPerm('scms:engine_type:edit')")
    public Result updateEngineType(@Valid @RequestBody EngineTypeForm engineTypeForm) {
        boolean result = engineTypeService.saveEngineType(engineTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除发动机类型")
    @DeleteMapping("/engine_types/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:engine_type:delete')")
    public Result deleteEngineTypes(
            @Parameter(description = "发动机类型ID，多个以英文(,)分割") @PathVariable String ids
    ) {
        boolean result = engineTypeService.deleteEngineTypes(ids);
        return Result.judge(result);
    }

    @Operation(summary = "燃油类型分页列表")
    @GetMapping("/fuel_types/page")
    public PageResult<FuelTypePageVO> getFuelTypePage(
            @ParameterObject FuelTypePageQuery queryParams
    ) {
        Page<FuelTypePageVO> result = fuelTypeService.getFuelTypePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "燃油类型下拉列表")
    @GetMapping("/fuel_types/options")
    public Result<List<Option>> getFuelTypeOptions() {
        List<Option> list = fuelTypeService.getFuelTypeOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增燃油类型")
    @PostMapping("/fuel_types")
    @PreAuthorize("@ss.hasPerm('scms:fuel_type:add')")
    @PreventDuplicateResubmit
    public Result addFuelType(@Valid @RequestBody FuelTypeForm fuelTypeForm) {
        boolean result = fuelTypeService.saveFuelType(fuelTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "燃油类型表单")
    @GetMapping("/fuel_types/{fuelTypeId}/form")
    public Result<FuelTypeForm> getFuelTypeForm(
            @Parameter(description = "燃油类型ID") @PathVariable Long fuelTypeId
    ) {
        FuelTypeForm fuelTypeForm = fuelTypeService.getFuelTypeForm(fuelTypeId);
        return Result.success(fuelTypeForm);
    }

    @Operation(summary = "修改燃油类型")
    @PutMapping("/fuel_types/{fuelTypeId}")
    @PreAuthorize("@ss.hasPerm('scms:fuel_type:edit')")
    public Result updateFuelType(@Valid @RequestBody FuelTypeForm fuelTypeForm) {
        boolean result = fuelTypeService.saveFuelType(fuelTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除燃油类型")
    @DeleteMapping("/fuel_types/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:fuel_type:delete')")
    public Result deleteFuelTypes(
            @Parameter(description = "燃油类型ID，多个以英文(,)分割") @PathVariable String ids
    ) {
        boolean result = fuelTypeService.deleteFuelTypes(ids);
        return Result.judge(result);
    }

    @Operation(summary = "注油方式分页列表")
    @GetMapping("/inj_modes/page")
    public PageResult<InjModePageVO> getInjModePage(
            @ParameterObject InjModePageQuery queryParams
    ) {
        Page<InjModePageVO> result = injModeService.getInjModePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "注油方式下拉列表")
    @GetMapping("/inj_modes/options")
    public Result<List<Option>> getInjModeOptions() {
        List<Option> list = injModeService.getInjModeOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增注油方式")
    @PostMapping("/inj_modes")
    @PreAuthorize("@ss.hasPerm('scms:inj_mode:add')")
    @PreventDuplicateResubmit
    public Result addInjMode(@Valid @RequestBody InjModeForm injModeForm) {
        boolean result = injModeService.saveInjMode(injModeForm);
        return Result.judge(result);
    }

    @Operation(summary = "注油方式表单")
    @GetMapping("/inj_modes/{injModeId}/form")
    public Result<InjModeForm> getInjModeForm(
            @Parameter(description = "注油方式ID") @PathVariable Long injModeId
    ) {
        InjModeForm injModeForm = injModeService.getInjModeForm(injModeId);
        return Result.success(injModeForm);
    }

    @Operation(summary = "修改注油方式")
    @PutMapping("/inj_modes/{injModeId}")
    @PreAuthorize("@ss.hasPerm('scms:inj_mode:edit')")
    public Result updateInjMode(@Valid @RequestBody InjModeForm injModeForm) {
        boolean result = injModeService.saveInjMode(injModeForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除注油方式")
    @DeleteMapping("/inj_modes/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:inj_mode:delete')")
    public Result deleteInjModes(
            @Parameter(description = "注油方式ID，多个以英文(,)分割") @PathVariable String ids
    ) {
        boolean result = injModeService.deleteInjModes(ids);
        return Result.judge(result);
    }

    @Operation(summary = "点火方式分页列表")
    @GetMapping("/fire_modes/page")
    public PageResult<FireModePageVO> getFireModePage(
            @ParameterObject FireModePageQuery queryParams
    ) {
        Page<FireModePageVO> result = fireModeService.getFireModePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "点火方式下拉列表")
    @GetMapping("/fire_modes/options")
    public Result<List<Option>> getFireModeOptions() {
        List<Option> list = fireModeService.getFireModeOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增点火方式")
    @PostMapping("/fire_modes")
    @PreAuthorize("@ss.hasPerm('scms:fire_mode:add')")
    @PreventDuplicateResubmit
    public Result addFireMode(@Valid @RequestBody FireModeForm fireModeForm) {
        boolean result = fireModeService.saveFireMode(fireModeForm);
        return Result.judge(result);
    }

    @Operation(summary = "点火方式表单")
    @GetMapping("/fire_modes/{fireModeId}/form")
    public Result<FireModeForm> getFireModeForm(
            @Parameter(description = "点火方式ID") @PathVariable Long fireModeId
    ) {
        FireModeForm fireModeForm = fireModeService.getFireModeForm(fireModeId);
        return Result.success(fireModeForm);
    }

    @Operation(summary = "修改点火方式")
    @PutMapping("/fire_modes/{fireModeId}")
    @PreAuthorize("@ss.hasPerm('scms:fire_mode:edit')")
    public Result updateFireMode(@Valid @RequestBody FireModeForm fireModeForm) {
        boolean result = fireModeService.saveFireMode(fireModeForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除点火方式")
    @DeleteMapping("/fire_modes/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:fire_mode:delete')")
    public Result deleteFireModes(
            @Parameter(description = "点火方式ID，多个以英文(,)分割") @PathVariable String ids
    ) {
        boolean result = fireModeService.deleteFireModes(ids);
        return Result.judge(result);
    }

}
