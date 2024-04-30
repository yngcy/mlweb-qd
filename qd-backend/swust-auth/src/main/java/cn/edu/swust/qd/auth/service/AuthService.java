package cn.edu.swust.qd.auth.service;

import cn.edu.swust.qd.auth.config.CaptchaProperties;
import cn.edu.swust.qd.auth.model.CaptchaResult;
import cn.edu.swust.qd.common.constant.RedisConstants;
import cn.edu.swust.qd.common.sms.property.AliyunSmsProperties;
import cn.edu.swust.qd.common.sms.service.SmsService;
import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * 认证服务
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final CaptchaProperties captchaProperties;

    private final CaptchaService captchaService;

    private final AliyunSmsProperties aliyunSmsProperties;

    private final SmsService smsService;

    private final StringRedisTemplate redisTemplate;


    /**
     * 获取图片验证码
     *
     * @return
     */
    public CaptchaResult getCaptcha() {
        AbstractCaptcha captcha = captchaService.generate();

        // 验证码文本缓存到Redis，用于登录校验
        String captchaId = IdUtil.fastSimpleUUID();
        redisTemplate.opsForValue().set(
                RedisConstants.CAPTCHA_CODE_PREFIX + captchaId,
                captcha.getCode(),
                captchaProperties.getExpireSeconds(),
                TimeUnit.SECONDS);

        CaptchaResult captchaResult = CaptchaResult.builder()
                .captchaId(captchaId)
                .captchaBase64(captcha.getImageBase64())
                .build();

        return captchaResult;
    }


    /**
     * 发送登录短信验证码
     *
     * @param mobile
     * @return
     */
    public boolean sendLoginSmsCode(String mobile) {
        // 获取短信验证模板代码
        String templateCode = aliyunSmsProperties.getTemplateCodes().get("login");

        // 生成随机4位验证码
        String code = RandomUtil.randomNumbers(4);

        String templateParams = JSONUtil.toJsonStr(Collections.singletonMap("code", code));

        boolean result = smsService.sendSms(mobile, templateCode, templateParams);
        if (result) {
            redisTemplate.opsForValue().set(RedisConstants.REGISTER_SMS_CODE_PREFIX + mobile, code, 5, TimeUnit.MINUTES);
        }
        return result;
    }
}
