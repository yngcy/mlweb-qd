package cn.edu.swust.qd.auth.oauth2.extension.captcha;

import jakarta.annotation.Nullable;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 验证码授权模式身份验证令牌（包含用户名和密码等）
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Getter
public class CaptchaAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    /**
     * 令牌申请访问范围
     */
    private final Set<String> scopes;

    /**
     * 授权模式（验证码：captcha）
     */
    public static final AuthorizationGrantType CAPTCHA = new AuthorizationGrantType("captcha");


    protected CaptchaAuthenticationToken(
            Authentication clientPrincipal,
            Set<String> scopes,
            @Nullable Map<String, Object> additionalParameters) {
        super(CAPTCHA, clientPrincipal, additionalParameters);
        this.scopes = Collections.unmodifiableSet(scopes != null ? new HashSet<>(scopes) : Collections.emptySet());
    }

    /**
     * 用户凭证（密码）
     *
     * @return
     */
    @Override
    public Object getCredentials() {
        return this.getAdditionalParameters().get(OAuth2ParameterNames.PASSWORD);
    }

}
