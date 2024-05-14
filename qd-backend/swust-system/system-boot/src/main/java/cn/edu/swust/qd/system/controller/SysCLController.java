package cn.edu.swust.qd.system.controller;

import cn.edu.swust.qd.common.result.PageResult;
import cn.edu.swust.qd.common.result.Result;
import cn.edu.swust.qd.common.web.annotation.PreventDuplicateResubmit;
import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.system.model.form.CLForm;
import cn.edu.swust.qd.system.model.query.CLPageQuery;
import cn.edu.swust.qd.system.model.vo.CLPageVO;
import cn.edu.swust.qd.system.service.SysConfidentialityLevelService;
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
@Tag(name = "sys_cl-api", description = "数据密级接口")
@RestController
@RequestMapping("/api/v1/cl")
@RequiredArgsConstructor
public class SysCLController {

    private final SysConfidentialityLevelService clService;

    @Operation(summary = "数据密级分页列表")
    @GetMapping("/page")
    public PageResult<CLPageVO> getCLPage(
            @ParameterObject CLPageQuery queryParams
    ) {
        Page<CLPageVO> result = clService.getCLPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "数据密级下拉列表")
    @GetMapping("/options")
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
    @GetMapping("/{clId}/form")
    public Result<CLForm> getCLForm(
            @Parameter(description = "数据密级ID") @PathVariable Long CLId
    ) {
        CLForm CLForm = clService.getCLForm(CLId);
        return Result.success(CLForm);
    }

    @Operation(summary = "修改数据密级")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('sys:cl:edit')")
    public Result updateCL(@Valid @RequestBody CLForm CLForm) {
        boolean result = clService.saveCL(CLForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除数据密级")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('sys:cl:delete')")
    public Result deleteCLs(
            @Parameter(description = "删除数据密级，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = clService.deleteCLs(ids);
        return Result.judge(result);
    }

    @Operation(summary = "修改数据密级状态")
    @PutMapping(value = "/{clId}/status")
    public Result updateCLStatus(
            @Parameter(description = "数据密级ID") @PathVariable Long clId,
            @Parameter(description = "状态(1:启用;0:禁用)") @RequestParam Integer status
    ) {
        boolean result = clService.updateCLStatus(clId, status);
        return Result.judge(result);
    }

    @Operation(summary = "分配数据密级给数据")
    public Result assignCLToUser(
            @PathVariable Integer clId,
            @RequestBody List<Integer> dataIds
    ) {
        boolean result = clService.assignClToData(clId, dataIds);
        return Result.judge(result);
    }
}
