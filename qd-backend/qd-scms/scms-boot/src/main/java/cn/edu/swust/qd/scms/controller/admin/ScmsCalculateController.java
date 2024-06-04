package cn.edu.swust.qd.scms.controller.admin;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */

import cn.edu.swust.qd.common.result.PageResult;
import cn.edu.swust.qd.common.result.Result;
import cn.edu.swust.qd.common.web.annotation.PreventDuplicateResubmit;
import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.form.CoordSystemTypeForm;
import cn.edu.swust.qd.scms.model.form.CoordinateTypeForm;
import cn.edu.swust.qd.scms.model.query.CoordSystemTypePageQuery;
import cn.edu.swust.qd.scms.model.query.CoordinateTypePageQuery;
import cn.edu.swust.qd.scms.model.query.SamplePageQuery;
import cn.edu.swust.qd.scms.model.vo.CoordSystemTypePageVO;
import cn.edu.swust.qd.scms.model.vo.CoordinateTypePageVO;
import cn.edu.swust.qd.scms.model.vo.SamplePageVO;
import cn.edu.swust.qd.scms.service.ScmsCoordSystemTypeService;
import cn.edu.swust.qd.scms.service.ScmsCoordinateTypeService;
import cn.edu.swust.qd.scms.service.ScmsSampleService;
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

@Tag(name = "计算标准信息管理接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/calculates")
public class ScmsCalculateController {

    private final ScmsSampleService sampleService;

    private final ScmsCoordinateTypeService coordinateTypeService;

    private final ScmsCoordSystemTypeService coordSystemTypeService;

    @Operation(summary = "样本分页列表")
    @GetMapping("/samples/page")
    public PageResult<SamplePageVO> getSamplePage(
            @ParameterObject SamplePageQuery queryParams
    ) {
        Page<SamplePageVO> result = sampleService.getSamplePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "坐标系类别分页列表")
    @GetMapping("/coordinate_types/page")
    public PageResult<CoordinateTypePageVO> getCoordinateTypePage(
            @ParameterObject CoordinateTypePageQuery queryParams
    ) {
        Page<CoordinateTypePageVO> result = coordinateTypeService.getCoordinateTypePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "坐标系类别下拉列表")
    @GetMapping("/coordinate_types/options")
    public Result<List<Option>> getCoordinateTypeOptions() {
        List<Option> list = coordinateTypeService.getCoordinateTypeOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增坐标系类别")
    @PostMapping("/coordinate_types")
    @PreAuthorize("@ss.hasPerm('scms:coordinate_type:add')")
    @PreventDuplicateResubmit
    public Result addCoordinateType(@Valid @RequestBody CoordinateTypeForm coordinateTypeForm) {
        boolean result = coordinateTypeService.saveCoordinateType(coordinateTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "坐标系类别表单")
    @GetMapping("/coordinate_types/{coordinateTypeId}/form")
    public Result<CoordinateTypeForm> getCoordinateTypeForm(
            @Parameter(description = "坐标系类别ID") @PathVariable Long coordinateTypeId
    ) {
        CoordinateTypeForm coordinateTypeForm = coordinateTypeService.getCoordinateTypeForm(coordinateTypeId);
        return Result.success(coordinateTypeForm);
    }

    @Operation(summary = "修改坐标系类别")
    @PutMapping("/coordinate_types/{coordinateTypeId}")
    @PreAuthorize("@ss.hasPerm('scms:coordinate_type:edit')")
    public Result updateCoordinateType(@Valid @RequestBody CoordinateTypeForm coordinateTypeForm) {
        boolean result = coordinateTypeService.saveCoordinateType(coordinateTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除坐标系类别")
    @DeleteMapping("/coordinate_types/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:coordinate_type:delete')")
    public Result deleteCoordinateTypes(
            @Parameter(description = "坐标系类别ID，多个以英文逗号(,)分隔") @PathVariable String ids
    ) {
        boolean result = coordinateTypeService.deleteCoordinateTypes(ids);
        return Result.judge(result);
    }

    @Operation(summary = "修改坐标系类别密级")
    @PutMapping(value = "/coordinate_types/{coordinateTypeId}/security")
    @PreAuthorize("@ss.hasPerm('scms:coordinate_type:security')")
    public Result updateCoordinateTypeSecurity(
            @Parameter(description = "坐标系类别ID") @PathVariable Long coordinateTypeId,
            @Parameter(description = "数据密级") @RequestParam Integer security
    ) {
        boolean result = coordinateTypeService.updateSecurity(coordinateTypeId, security);
        return Result.judge(result);
    }

    @Operation(summary = "坐标系统类别分页列表")
    @GetMapping("/coord_system_types/page")
    public PageResult<CoordSystemTypePageVO> getCoordSystemTypePage(
            @ParameterObject CoordSystemTypePageQuery queryParams
    ) {
        Page<CoordSystemTypePageVO> result = coordSystemTypeService.getCoordSystemTypePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "坐标系统类别下拉列表")
    @GetMapping("/coord_system_types/options")
    public Result<List<Option>> getCoordSystemTypeOptions() {
        List<Option> list = coordSystemTypeService.getCoordSystemTypeOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增坐标系统类别")
    @PostMapping("/coord_system_types")
    @PreAuthorize("@ss.hasPerm('scms:coord_system_type:add')")
    @PreventDuplicateResubmit
    public Result addCoordSystemType(@Valid @RequestBody CoordSystemTypeForm coordSystemTypeForm) {
        boolean result = coordSystemTypeService.saveCoordSystemType(coordSystemTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "坐标系统类别表单")
    @GetMapping("/coord_system_types/{coordSystemTypeId}/form")
    public Result<CoordSystemTypeForm> getCoordSystemTypeForm(
            @Parameter(description = "坐标系统类别ID") @PathVariable Long coordSystemTypeId
    ) {
        CoordSystemTypeForm coordSystemTypeForm = coordSystemTypeService.getCoordSystemTypeForm(coordSystemTypeId);
        return Result.success(coordSystemTypeForm);
    }

    @Operation(summary = "修改坐标系统类别")
    @PutMapping("/coord_system_types/{coordSystemTypeId}")
    @PreAuthorize("@ss.hasPerm('scms:coord_system_type:edit')")
    public Result updateCoordSystemType(@Valid @RequestBody CoordSystemTypeForm coordSystemTypeForm) {
        boolean result = coordSystemTypeService.saveCoordSystemType(coordSystemTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除坐标系统类别")
    @DeleteMapping("/coord_system_types/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:coord_system_type:delete')")
    public Result deleteCoordSystemTypes(
            @Parameter(description = "坐标系统类别ID，多个以英文逗号(,)分隔") @PathVariable String ids
    ) {
        boolean result = coordSystemTypeService.deleteCoordSystemTypes(ids);
        return Result.judge(result);
    }

    @Operation(summary = "修改坐标系类别密级")
    @PutMapping(value = "/coord_system_types/{coordSystemTypeId}/security")
    @PreAuthorize("@ss.hasPerm('scms:coord_system_type:security')")
    public Result updateCoordSystemTypeSecurity(
            @Parameter(description = "坐标系统类别ID") @PathVariable Long coordSystemTypeId,
            @Parameter(description = "数据密级") @RequestParam Integer security
    ) {
        boolean result = coordSystemTypeService.updateSecurity(coordSystemTypeId, security);
        return Result.judge(result);
    }

}
