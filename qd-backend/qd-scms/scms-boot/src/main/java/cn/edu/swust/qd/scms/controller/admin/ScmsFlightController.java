package cn.edu.swust.qd.scms.controller.admin;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */

import cn.edu.swust.qd.common.result.PageResult;
import cn.edu.swust.qd.common.result.Result;
import cn.edu.swust.qd.common.web.annotation.PreventDuplicateResubmit;
import cn.edu.swust.qd.scms.model.form.AircraftMainForm;
import cn.edu.swust.qd.scms.model.form.AircraftSecForm;
import cn.edu.swust.qd.scms.model.query.AircraftMainPageQuery;
import cn.edu.swust.qd.scms.model.query.AircraftSecPageQuery;
import cn.edu.swust.qd.scms.model.vo.AircraftMainPageVO;
import cn.edu.swust.qd.scms.model.vo.AircraftSecPageVO;
import cn.edu.swust.qd.scms.service.ScmsAircraftMainService;
import cn.edu.swust.qd.scms.service.ScmsAircraftSecService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "飞行器信息管理接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/flights")
public class ScmsFlightController {

    private final ScmsAircraftMainService aircraftMainService;

    private final ScmsAircraftSecService aircraftSecService;

    @Operation(summary = "飞行器主信息分页列表")
    @GetMapping("/aircraft_mains/page")
    public PageResult<AircraftMainPageVO> getAircraftMainPage(
            @ParameterObject AircraftMainPageQuery queryParams
    ) {
        Page<AircraftMainPageVO> result = aircraftMainService.getAircraftMainPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增飞行器主信息")
    @PostMapping("/aircraft_mains")
    @PreAuthorize("@ss.hasPerm('scms:aircraft_main:add')")
    @PreventDuplicateResubmit
    public Result addAircraftMain(@Valid @RequestBody AircraftMainForm aircraftMainForm) {
        boolean result = aircraftMainService.saveAircraftMain(aircraftMainForm);
        return Result.judge(result);
    }

    @Operation(summary = "飞行器主信息表单")
    @GetMapping("/aircraft_mains/{aircraftMainId}/form")
    public Result<AircraftMainForm> getAircraftMainForm(
            @Parameter(description = "飞行器主信息ID") @PathVariable Long aircraftMainId
    ) {
        AircraftMainForm aircraftMainForm = aircraftMainService.getAircraftMainForm(aircraftMainId);
        return Result.success(aircraftMainForm);
    }

    @Operation(summary = "修改飞行器主信息")
    @PutMapping("/aircraft_mains/{aircraftMainId}")
    @PreAuthorize("@ss.hasPerm('scms:aircraft_main:edit')")
    public Result updateAircraftMain(@Valid @RequestBody AircraftMainForm aircraftMainForm) {
        boolean result = aircraftMainService.saveAircraftMain(aircraftMainForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除飞行器主信息")
    @DeleteMapping("/aircraft_mains/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:aircraft_main:delete')")
    public Result deleteAircraftMain(
            @Parameter(description = "飞行器主信息ID，多个以英文逗号(,)分隔") @PathVariable String ids
    ) {
        boolean result = aircraftMainService.deleteAircraftMains(ids);
        return Result.judge(result);
    }

    @Operation(summary = "修改飞行器主信息密级")
    @PutMapping(value = "/{aircraftMainId}/security")
    @PreAuthorize("@ss.hasPerm('scms:aircraft_main:security')")
    public Result updateAircraftMainSecurity(
            @Parameter(description = "飞行器主信息ID") @PathVariable Long aircraftMainId,
            @Parameter(description = "数据密级") @RequestParam Integer security
    ) {
        boolean result = aircraftMainService.updateSecurity(aircraftMainId, security);
        return Result.judge(result);
    }

    @Operation(summary = "飞行器次级信息分页列表")
    @GetMapping("/aircraft_secs/page")
    public PageResult<AircraftSecPageVO> getAircraftSecPage(
            @ParameterObject AircraftSecPageQuery queryParams
    ) {
        Page<AircraftSecPageVO> result = aircraftSecService.getAircraftSecPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增飞行器次级信息")
    @PostMapping("/aircraft_secs")
    @PreAuthorize("@ss.hasPerm('scms:aircraft_sec:add')")
    @PreventDuplicateResubmit
    public Result addAircraftSec(@Valid @RequestBody AircraftSecForm aircraftSecForm) {
        boolean result = aircraftSecService.saveAircraftSec(aircraftSecForm);
        return Result.judge(result);
    }

    @Operation(summary = "飞行器次级信息表单")
    @GetMapping("/aircraft_secs/{aircraftSecId}/form")
    public Result<AircraftSecForm> getAircraftSecForm(
            @Parameter(description = "飞行器次级信息ID") @PathVariable Long aircraftSecId
    ) {
        AircraftSecForm aircraftSecForm = aircraftSecService.getAircraftSecForm(aircraftSecId);
        return Result.success(aircraftSecForm);
    }

    @Operation(summary = "修改飞行器次级信息")
    @PutMapping("/aircraft_secs/{aircraftSecId}")
    @PreAuthorize("@ss.hasPerm('scms:aircraft_sec:edit')")
    public Result updateAircraftSec(@Valid @RequestBody AircraftSecForm aircraftSecForm) {
        boolean result = aircraftSecService.saveAircraftSec(aircraftSecForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除飞行器次级信息")
    @DeleteMapping("/aircraft_secs/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:aircraft_sec:delete')")
    public Result deleteAircraftSec(
            @Parameter(description = "飞行器次级信息ID，多个以英文逗号(,)分隔") @PathVariable String ids
    ) {
        boolean result = aircraftSecService.deleteAircraftSecs(ids);
        return Result.judge(result);
    }

    @Operation(summary = "修改飞行器次级信息密级")
    @PutMapping(value = "/{aircraftSecId}/security")
    @PreAuthorize("@ss.hasPerm('scms:aircraft_sec:security')")
    public Result updateAircraftSecSecurity(
            @Parameter(description = "飞行器次级信息ID") @PathVariable Long aircraftSecId,
            @Parameter(description = "数据密级") @RequestParam Integer security
    ) {
        boolean result = aircraftSecService.updateSecurity(aircraftSecId, security);
        return Result.judge(result);
    }
}
