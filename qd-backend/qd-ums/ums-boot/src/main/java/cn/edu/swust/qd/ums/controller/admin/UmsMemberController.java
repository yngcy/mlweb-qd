package cn.edu.swust.qd.ums.controller.admin;

import cn.edu.swust.qd.common.result.PageResult;
import cn.edu.swust.qd.common.result.Result;
import cn.edu.swust.qd.ums.model.form.MemberForm;
import cn.edu.swust.qd.ums.model.query.MemberPageQuery;
import cn.edu.swust.qd.ums.model.vo.MemberPageVO;
import cn.edu.swust.qd.ums.service.UmsMemberService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Tag(name = "成员管理服务")
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class UmsMemberController {

    private final UmsMemberService memberService;

    @Operation(summary = "成员分页列表")
    @GetMapping
    public PageResult<MemberPageVO> getMemberPage(@ParameterObject MemberPageQuery queryParams) {
        Page<MemberPageVO> result = memberService.getMemberPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "修改成员")
    @PutMapping("/{memberId}")
    public Result updateMember(@Valid @RequestBody MemberForm memberForm) {
        boolean result = memberService.updateMember(memberForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改成员状态")
    @PatchMapping("/{memberId}/status")
    public Result updateMemberStatus(
            @Parameter(description = "成员ID") @PathVariable Long memberId,
            @Parameter(description = "状态(1:启用;0:禁用)") @RequestParam Integer status
    ) {
        boolean result = memberService.updateMemberStatus(memberId, status);
        return Result.judge(result);
    }

    @Operation(summary = "删除成员")
    @DeleteMapping("/{ids}")
    public Result deleteMembers(
            @Parameter(description = "删除成员，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = memberService.deleteMembers(ids);
        return Result.judge(result);
    }

}
