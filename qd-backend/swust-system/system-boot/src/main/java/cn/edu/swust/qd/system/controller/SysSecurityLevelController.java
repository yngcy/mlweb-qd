package cn.edu.swust.qd.system.controller;

import cn.edu.swust.qd.common.result.PageResult;
import cn.edu.swust.qd.common.result.Result;
import cn.edu.swust.qd.common.web.annotation.PreventDuplicateResubmit;
import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.system.model.form.CLForm;
import cn.edu.swust.qd.system.model.form.SLForm;
import cn.edu.swust.qd.system.model.query.CLPageQuery;
import cn.edu.swust.qd.system.model.query.SLPageQuery;
import cn.edu.swust.qd.system.model.vo.CLPageVO;
import cn.edu.swust.qd.system.model.vo.SLPageVO;
import cn.edu.swust.qd.system.service.SysConfidentialityLevelService;
import cn.edu.swust.qd.system.service.SysSecretLevelService;
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

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Tag(name = "密级接口")
@RestController
@RequestMapping("/api/v1/security_level")
@RequiredArgsConstructor
public class SysSecurityLevelController {

    private final SysConfidentialityLevelService clService;

    private final SysSecretLevelService slService;

    @Operation(summary = "数据密级分页列表")
    @GetMapping("/cl/page")
    public PageResult<CLPageVO> getCLPage(
            @ParameterObject CLPageQuery queryParams
    ) {
        Page<CLPageVO> result = clService.getCLPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "数据密级下拉列表")
    @GetMapping("/cl/options")
    public Result<List<Option>> listCLOption() {
        List<Option> list = clService.listCLOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增数据密级")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:cl:add')")
    @PreventDuplicateResubmit
    public Result addCL(@Valid @RequestBody CLForm clForm) {
        boolean result = clService.saveCL(clForm);
        return Result.judge(result);
    }

    @Operation(summary = "数据密级表单数据")
    @GetMapping("/cl/{clId}/form")
    public Result<CLForm> getCLForm(
            @Parameter(description = "数据密级ID") @PathVariable Long CLId
    ) {
        CLForm CLForm = clService.getCLForm(CLId);
        return Result.success(CLForm);
    }

    @Operation(summary = "修改数据密级")
    @PutMapping(value = "/cl/{id}")
    @PreAuthorize("@ss.hasPerm('sys:cl:edit')")
    public Result updateCL(@Valid @RequestBody CLForm CLForm) {
        boolean result = clService.saveCL(CLForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除数据密级")
    @DeleteMapping("/cl/{ids}")
    @PreAuthorize("@ss.hasPerm('sys:cl:delete')")
    public Result deleteCLs(
            @Parameter(description = "删除数据密级，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = clService.deleteCLs(ids);
        return Result.judge(result);
    }

    @Operation(summary = "修改数据密级状态")
    @PutMapping(value = "/cl/{clId}/status")
    public Result updateCLStatus(
            @Parameter(description = "数据密级ID") @PathVariable Long clId,
            @Parameter(description = "状态(1:启用;0:禁用)") @RequestParam Integer status
    ) {
        boolean result = clService.updateCLStatus(clId, status);
        return Result.judge(result);
    }

    @Operation(summary = "分配数据密级给数据")
    @PutMapping("/cl/{clId}/data")
    public Result assignCLToUser(
            @PathVariable Integer clId,
            @RequestBody List<Integer> dataIds
    ) {
        boolean result = clService.assignClToData(clId, dataIds);
        return Result.judge(result);
    }


    @Operation(summary = "人员密级分页列表")
    @GetMapping("/sl/page")
    public PageResult<SLPageVO> getSLPage(
            @ParameterObject SLPageQuery queryParams
    ) {
        Page<SLPageVO> result = slService.getSLPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "数据密级下拉列表")
    @GetMapping("/sl/options")
    public Result<List<Option>> listSLOption() {
        List<Option> list = slService.listSLOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增人员密级")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:sl:add')")
    @PreventDuplicateResubmit
    public Result addSL(@Valid @RequestBody SLForm slForm) {
        boolean result = slService.saveSL(slForm);
        return Result.judge(result);
    }

    @Operation(summary = "人员密级表单人员")
    @GetMapping("/sl/{slId}/form")
    public Result<SLForm> getSLForm(
            @Parameter(description = "人员密级ID") @PathVariable Long slId
    ) {
        SLForm SLForm = slService.getSLForm(slId);
        return Result.success(SLForm);
    }

    @Operation(summary = "修改人员密级")
    @PutMapping(value = "/sl/{id}")
    @PreAuthorize("@ss.hasPerm('sys:sl:edit')")
    public Result updateSL(@Valid @RequestBody SLForm SLForm) {
        boolean result = slService.saveSL(SLForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除人员密级")
    @DeleteMapping("/sl/{ids}")
    @PreAuthorize("@ss.hasPerm('sys:sl:delete')")
    public Result deleteSLs(
            @Parameter(description = "删除人员密级，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = slService.deleteSLs(ids);
        return Result.judge(result);
    }

    @Operation(summary = "修改人员密级状态")
    @PutMapping(value = "/sl/{slId}/status")
    public Result updateSLStatus(
            @Parameter(description = "人员密级ID") @PathVariable Long slId,
            @Parameter(description = "状态(1:启用;0:禁用)") @RequestParam Integer status
    ) {
        boolean result = slService.updateSLStatus(slId, status);
        return Result.judge(result);
    }

    @Operation(summary = "分配人员密级给人员")
    @PutMapping("/sl/{slId}/data")
    public Result assignSLToUser(
            @PathVariable Integer slId,
            @RequestBody List<Integer> dataIds
    ) {
        boolean result = slService.assignSLtoUser(slId, dataIds);
        return Result.judge(result);
    }
}
