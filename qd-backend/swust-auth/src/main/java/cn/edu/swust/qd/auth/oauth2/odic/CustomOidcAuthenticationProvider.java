package cn.edu.swust.qd.auth.oauth2.odic;

import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;

import java.util.*;
import java.util.function.Function;

/**
 * 自定义 OIDC 认证提供者
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Slf4j
public class CustomOidcAuthenticationProvider implements AuthenticationProvider {

    private final OAuth2AuthorizationService authorizationService;

    public CustomOidcAuthenticationProvider(OAuth2AuthorizationService authorizationService) {
        Assert.notNull(authorizationService, "authorizationService cannot be null");
        this.authorizationService = authorizationService;
    }

    /**
     * 对用户进行认证。
     * 这个方法会尝试使用提供的 {@link Authentication} 对象（通常是用户信息令牌）来获取访问令牌并验证它。
     * 如果访问令牌有效并激活，将根据授权范围获取用户信息，并返回一个新的认证对象。
     *
     * @param authentication 提供用户信息的 {@link Authentication} 对象。
     * @return 经过认证的 {@link Authentication} 对象，包含用户信息和访问令牌。
     * @throws AuthenticationException 如果认证失败，抛出相应的异常。
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OidcUserInfoAuthenticationToken userInfoAuthentication = (OidcUserInfoAuthenticationToken) authentication;
        AbstractOAuth2TokenAuthenticationToken<?> accessTokenAuthentication = null;
        if (AbstractOAuth2TokenAuthenticationToken.class.isAssignableFrom(userInfoAuthentication.getPrincipal().getClass())) {
            accessTokenAuthentication = (AbstractOAuth2TokenAuthenticationToken) userInfoAuthentication.getPrincipal();
        }

        if (accessTokenAuthentication != null && accessTokenAuthentication.isAuthenticated()) {
            String accessTokenValue = accessTokenAuthentication.getToken().getTokenValue();
            OAuth2Authorization authorization = this.authorizationService.findByToken(accessTokenValue, OAuth2TokenType.ACCESS_TOKEN);
            if (authorization == null) {
                throw new OAuth2AuthenticationException("invalid_token");
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Retrieved authorization with access token");
                }

                OAuth2Authorization.Token<OAuth2AccessToken> authorizedAccessToken = authorization.getAccessToken();
                if (!authorizedAccessToken.isActive()) {
                    throw new OAuth2AuthenticationException("invalid_token");
                } else {
                    // 从认证结果中获取userInfo
                    CustomOidcUserInfo customOidcUserInfo = (CustomOidcUserInfo) userInfoAuthentication.getUserInfo();
                    // 从authorizedAccessToken中获取授权范围
                    Set<String> scopeSet = (HashSet<String>) authorizedAccessToken.getClaims().get("scope");
                    // 获取授权范围对应userInfo的字段信息
                    Map<String, Object> claims = DefaultOidcUserInfoMapper.getClaimsRequestedByScope(customOidcUserInfo.getClaims(), scopeSet);
                    if (log.isTraceEnabled()) {
                        log.trace("Authenticated user info request");
                    }

                    return new CustomOidcToken(accessTokenAuthentication, new CustomOidcUserInfo(claims));
                }
            }
        } else {
            throw new OAuth2AuthenticationException("invalid_token");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OidcUserInfoAuthenticationToken.class.isAssignableFrom(authentication);
    }

    /**
     * 默认的OIDC用户信息映射器，用于将OIDC用户信息认证上下文映射为自定义的OIDC用户信息。
     */
    private static final class DefaultOidcUserInfoMapper implements Function<OidcUserInfoAuthenticationContext, CustomOidcUserInfo> {
        // 邮箱相关声明列表
        private static final List<String> EMAIL_CLAIMS = Arrays.asList("email", "email_verified");
        // 电话相关声明列表
        private static final List<String> PHONE_CLAIMS = Arrays.asList("phone_number", "phone_number_verified");
        // 个人资料相关声明列表
        private static final List<String> PROFILE_CLAIMS = Arrays.asList("username", "nickname", "status", "profile");

        // 私有构造函数，防止外部实例化
        private DefaultOidcUserInfoMapper() {
        }

        /**
         * 根据OIDC用户信息认证上下文，映射并返回自定义的OIDC用户信息。
         *
         * @param authenticationContext OIDC用户信息认证上下文
         * @return 自定义的OIDC用户信息
         */
        @Override
        public CustomOidcUserInfo apply(OidcUserInfoAuthenticationContext authenticationContext) {
            // 获取授权和身份验证信息
            OAuth2Authorization authorization = authenticationContext.getAuthorization();
            OidcIdToken idToken = authorization.getToken(OidcIdToken.class).getToken();
            OAuth2AccessToken accessToken = authenticationContext.getAccessToken();
            // 根据权限范围请求的声明
            Map<String, Object> scopeRequestedClaims = getClaimsRequestedByScope(idToken.getClaims(), accessToken.getScopes());
            return new CustomOidcUserInfo(scopeRequestedClaims);
        }

        /**
         * 根据请求的权限范围，从声明中筛选出所需的数据。
         *
         * @param claims          用户的原始声明
         * @param requestedScopes 请求的权限范围
         * @return 筛选后的声明
         */
        private static Map<String, Object> getClaimsRequestedByScope(Map<String, Object> claims, Set<String> requestedScopes) {
            // 初始化请求的声明名称集合
            Set<String> scopeRequestedClaimNames = new HashSet<>(32);
            scopeRequestedClaimNames.add("sub");

            // 根据请求的权限范围，添加相应的声明名称
            if (requestedScopes.contains("address")) {
                scopeRequestedClaimNames.add("address");
            }

            if (requestedScopes.contains("email")) {
                scopeRequestedClaimNames.addAll(EMAIL_CLAIMS);
            }

            if (requestedScopes.contains("phone")) {
                scopeRequestedClaimNames.addAll(PHONE_CLAIMS);
            }

            if (requestedScopes.contains("profile")) {
                scopeRequestedClaimNames.addAll(PROFILE_CLAIMS);
            }

            // 根据请求的声明名称集合，筛选出相应的声明
            Map<String, Object> requestedClaims = new HashMap<>(claims);
            requestedClaims.keySet().removeIf((claimName) -> !scopeRequestedClaimNames.contains(claimName));
            return requestedClaims;
        }
    }

}
