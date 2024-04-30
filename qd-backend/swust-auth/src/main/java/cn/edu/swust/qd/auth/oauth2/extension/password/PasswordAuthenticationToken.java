package cn.edu.swust.qd.auth.oauth2.extension.password;

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
 * 密码授权模式身份验证令牌（包含用户名和密码等）
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Getter
public class PasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    public static final AuthorizationGrantType PASSWORD = new AuthorizationGrantType("password");

    /**
     * 令牌申请访问范围
     */
    private final Set<String> scopes;

    /**
     * 构造一个PasswordAuthenticationToken对象。
     * 这个构造函数用于创建一个与特定客户端主体、作用域和附加参数相关联的认证令牌。
     *
     * @param clientPrincipal      认证的客户端主体，通常是一个表示用户的身份验证对象。
     * @param scopes               该令牌的作用域，表示该令牌可以访问的资源或权限集合。
     * @param additionalParameters 附加参数，一个可选的键值对集合，用于提供额外的信息或配置。
     */
    protected PasswordAuthenticationToken(
            Authentication clientPrincipal,
            Set<String> scopes,
            @Nullable Map<String, Object> additionalParameters) {
        super(PASSWORD, clientPrincipal, additionalParameters);
        this.scopes = Collections.unmodifiableSet(scopes != null ? new HashSet<>(scopes) : Collections.emptySet());
    }

    @Override
    public Object getCredentials() {
        return this.getAdditionalParameters().get(OAuth2ParameterNames.PASSWORD);
    }

}
