package cn.edu.swust.qd.auth.controller;

import cn.edu.swust.qd.auth.model.CaptchaResult;
import cn.edu.swust.qd.auth.service.AuthService;
import cn.edu.swust.qd.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "获取验证码")
    @GetMapping("/captcha")
    public Result<CaptchaResult> getCaptcha() {
        CaptchaResult captchaResult = authService.getCaptcha();
        return Result.success(captchaResult);
    }

    @Operation(summary = "发送手机短信验证码")
    @PostMapping("/sms_code")
    public Result<Void> sendLoginSmsCode(
            @Parameter(description = "手机号") @RequestParam String mobile
    ) {
        boolean result = authService.sendLoginSmsCode(mobile);
        return Result.judge(result);
    }
}
