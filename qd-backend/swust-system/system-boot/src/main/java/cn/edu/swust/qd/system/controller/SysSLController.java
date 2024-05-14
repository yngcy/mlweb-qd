package cn.edu.swust.qd.system.controller;

import cn.edu.swust.qd.common.result.PageResult;
import cn.edu.swust.qd.common.result.Result;
import cn.edu.swust.qd.common.web.annotation.PreventDuplicateResubmit;
import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.system.model.form.SLForm;
import cn.edu.swust.qd.system.model.query.SLPageQuery;
import cn.edu.swust.qd.system.model.vo.SLPageVO;
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
@Tag(name = "sys_sl-api", description = "人员密级接口")
@RestController
@RequestMapping("/api/v1/sl")
@RequiredArgsConstructor
public class SysSLController {

    private final SysSecretLevelService slService;

    @Operation(summary = "人员密级分页列表")
    @GetMapping("/page")
    public PageResult<SLPageVO> getSLPage(
            @ParameterObject SLPageQuery queryParams
    ) {
        Page<SLPageVO> result = slService.getSLPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "数据密级下拉列表")
    @GetMapping("/options")
    public Result<List<Option>> listCLOption() {
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
    @GetMapping("/{slId}/form")
    public Result<SLForm> getSLForm(
            @Parameter(description = "人员密级ID") @PathVariable Long slId
    ) {
        SLForm SLForm = slService.getSLForm(slId);
        return Result.success(SLForm);
    }

    @Operation(summary = "修改人员密级")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('sys:sl:edit')")
    public Result updateSL(@Valid @RequestBody SLForm SLForm) {
        boolean result = slService.saveSL(SLForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除人员密级")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('sys:sl:delete')")
    public Result deleteSLs(
            @Parameter(description = "删除人员密级，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = slService.deleteSLs(ids);
        return Result.judge(result);
    }

    @Operation(summary = "修改人员密级状态")
    @PutMapping(value = "/{slId}/status")
    public Result updateSLStatus(
            @Parameter(description = "人员密级ID") @PathVariable Long slId,
            @Parameter(description = "状态(1:启用;0:禁用)") @RequestParam Integer status
    ) {
        boolean result = slService.updateSLStatus(slId, status);
        return Result.judge(result);
    }


}
