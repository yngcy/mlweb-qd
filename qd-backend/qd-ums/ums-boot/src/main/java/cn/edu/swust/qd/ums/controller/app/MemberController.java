package cn.edu.swust.qd.ums.controller.app;

import cn.edu.swust.qd.common.result.Result;
import cn.edu.swust.qd.ums.dto.MemberAuthDTO;
import cn.edu.swust.qd.ums.dto.MemberRegisterDTO;
import cn.edu.swust.qd.ums.model.entity.UmsMember;
import cn.edu.swust.qd.ums.model.vo.MemberVO;
import cn.edu.swust.qd.ums.service.UmsMemberService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Tag(name = "ums_app-api", description = "成员接口")
@RestController
@RequestMapping("/app-api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final UmsMemberService memberService;

    @Operation(summary = "根据成员ID获取openid")
    public Result<String> getMemberById(
            @Parameter(description = "成员ID") Long memberId
    ) {
        UmsMember member = memberService.getOne(new LambdaQueryWrapper<UmsMember>()
                .eq(UmsMember::getId, memberId)
                .select(UmsMember::getOpenid));
        String openid = member.getOpenid();
        return Result.success(openid);
    }

    @Operation(summary = "新增成员")
    @PostMapping
    public Result<Long> addMember(@RequestBody MemberRegisterDTO member) {
        Long memberId = memberService.addMember(member);
        return Result.success(memberId);
    }

    @Operation(summary = "获取登录成员信息")
    @GetMapping("/me")
    public Result<MemberVO> getCurrentMemberInfo() {
        MemberVO memberVO = memberService.getCurrentMemberInfo();
        return Result.success(memberVO);
    }

    @Operation(summary = "根据 openid 获取成员认证信息")
    @GetMapping("/openid/{openid}")
    public Result<MemberAuthDTO> getMemberByOpenid(
            @Parameter(description = "微信唯一身份标识") @PathVariable String openid
    ) {
        MemberAuthDTO memberAuthDTO = memberService.getMemberByOpenid(openid);
        return Result.success(memberAuthDTO);
    }

    @Operation(summary = "根据手机号获取成员认证信息")
    public Result<MemberAuthDTO> getMemberByMobile(
            @Parameter(description = "手机号码") @PathVariable String mobile
    ) {
        MemberAuthDTO memberAuthDTO = memberService.getMemberByMobile(mobile);
        return Result.success(memberAuthDTO);
    }

}
