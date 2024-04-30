package cn.edu.swust.qd.auth.oauth2.extension.captcha;

import cn.edu.swust.qd.auth.utils.OAuth2AuthenticationProviderUtils;
import cn.edu.swust.qd.common.constant.RedisConstants;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.CollectionUtils;

import java.security.Principal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 验证码模式身份验证提供者
 * <br>
 * 处理基于用户名和密码的身份验证
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Slf4j
public class CaptchaAuthenticationProvider implements AuthenticationProvider {

    private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";

    private static final OAuth2TokenType ID_TOKEN_TOKEN_TYPE = new OAuth2TokenType(OidcParameterNames.ID_TOKEN);

    private final AuthenticationManager authenticationManager;

    private final OAuth2AuthorizationService authorizationService;

    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;

    private final StringRedisTemplate redisTemplate;

    private final CodeGenerator codeGenerator;

    /**
     * Constructs an {@code OAuth2ResourceOwnerPasswordAuthenticationProviderNew} using the provided parameters.
     *
     * @param authenticationManager the authentication manager
     * @param authorizationService  the authorization service
     * @param tokenGenerator        the token generator
     * @since 0.2.3
     */
    public CaptchaAuthenticationProvider(AuthenticationManager authenticationManager,
                                         OAuth2AuthorizationService authorizationService,
                                         OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator,
                                         StringRedisTemplate redisTemplate,
                                         CodeGenerator codeGenerator) {
        Assert.notNull(authorizationService, "authorizationService cannot be null");
        Assert.notNull(tokenGenerator, "tokenGenerator cannot be null");
        this.authenticationManager = authenticationManager;
        this.authorizationService = authorizationService;
        this.tokenGenerator = tokenGenerator;
        this.redisTemplate = redisTemplate;
        this.codeGenerator = codeGenerator;
    }

    /**
     * 进行用户认证和访问令牌的生成。
     *
     * @param authentication 用户提供的认证信息，包含验证码、用户名和密码等。
     * @return 认证成功后返回包含访问令牌、刷新令牌等信息的对象。
     * @throws AuthenticationException 如果认证过程中出现错误，抛出此异常。
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 将传入的认证信息转换为CaptchaAuthenticationToken类型
        CaptchaAuthenticationToken passwordAuthenticationToken = (CaptchaAuthenticationToken) authentication;
        // 通过验证码认证获取客户端主体信息
        OAuth2ClientAuthenticationToken clientPrincipal = OAuth2AuthenticationProviderUtils
                .getAuthenticatedClientElseThrowInvalidClient(passwordAuthenticationToken);
        RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();

        // 验证客户端是否支持授权类型(grant_type=captcha)
        if (!registeredClient.getAuthorizationGrantTypes().contains(CaptchaAuthenticationToken.CAPTCHA)) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
        }

        Map<String, Object> additionalParameters = passwordAuthenticationToken.getAdditionalParameters();

        // 验证码校验
        String captchaId = (String) additionalParameters.get(CaptchaParameterNames.CAPTCHA_ID);
        String captchaCode = (String) additionalParameters.get(CaptchaParameterNames.CAPTCHA_CODE);

        String cacheCaptchaCode = redisTemplate.opsForValue().get(RedisConstants.CAPTCHA_CODE_PREFIX + captchaId);

        // 验证码比对
        if (!codeGenerator.verify(cacheCaptchaCode, captchaCode)) {
            throw new OAuth2AuthenticationException("验证码错误");
        }

        // 生成用户名密码身份验证令牌
        String username = (String) additionalParameters.get(OAuth2ParameterNames.USERNAME);
        String password = (String) additionalParameters.get(OAuth2ParameterNames.PASSWORD);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // 用户名密码身份验证，成功后返回带有权限的认证信息
        Authentication usernamePasswordAuthentication;
        try {
            usernamePasswordAuthentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (Exception e) {
            // 需要将其他类型的异常转换为 OAuth2AuthenticationException 才能被自定义异常捕获处理，逻辑源码 OAuth2TokenEndpointFilter#doFilterInternal
            throw new OAuth2AuthenticationException(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }

        // 验证申请访问范围(Scope)
        Set<String> authorizedScopes = registeredClient.getScopes();
        Set<String> requestedScopes = passwordAuthenticationToken.getScopes();
        if (!CollectionUtils.isEmpty(requestedScopes)) {
            Set<String> unauthorizedScopes = requestedScopes.stream()
                    .filter(requestedScope -> !registeredClient.getScopes().contains(requestedScope))
                    .collect(Collectors.toSet());
            if (!CollectionUtils.isEmpty(unauthorizedScopes)) {
                throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_SCOPE);
            }
            authorizedScopes = new LinkedHashSet<>(requestedScopes);
        }

        // 访问令牌(Access Token) 构造器
        DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient)
                .principal(usernamePasswordAuthentication) // 身份验证成功的认证信息(用户名、权限等信息)
                .authorizationServerContext(AuthorizationServerContextHolder.getContext())
                .authorizedScopes(authorizedScopes)
                .authorizationGrantType(CaptchaAuthenticationToken.CAPTCHA) // 授权方式
                .authorizationGrant(passwordAuthenticationToken); // 授权具体对象


        // 生成访问令牌(Access Token)
        OAuth2TokenContext tokenContext = tokenContextBuilder.tokenType((OAuth2TokenType.ACCESS_TOKEN)).build();
        OAuth2Token generatedAccessToken = this.tokenGenerator.generate(tokenContext);
        if (generatedAccessToken == null) {
            OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
                    "The token generator failed to generate the access token.", ERROR_URI);
            throw new OAuth2AuthenticationException(error);
        }

        OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(),
                generatedAccessToken.getExpiresAt(), tokenContext.getAuthorizedScopes());

        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
                .principalName(usernamePasswordAuthentication.getName())
                .authorizationGrantType(CaptchaAuthenticationToken.CAPTCHA)
                .authorizedScopes(authorizedScopes)
                .attribute(Principal.class.getName(), usernamePasswordAuthentication); // attribute 字段
        if (generatedAccessToken instanceof ClaimAccessor) {
            authorizationBuilder.token(accessToken, (metadata) ->
                    metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, ((ClaimAccessor) generatedAccessToken).getClaims()));
        } else {
            authorizationBuilder.accessToken(accessToken);
        }

        // 生成刷新令牌(Refresh Token)
        OAuth2RefreshToken refreshToken = null;
        if (registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN) &&
                // Do not issue refresh token to public client
                !clientPrincipal.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE)) {

            tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
            OAuth2Token generatedRefreshToken = this.tokenGenerator.generate(tokenContext);
            if (!(generatedRefreshToken instanceof OAuth2RefreshToken)) {
                OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
                        "The token generator failed to generate the refresh token.", ERROR_URI);
                throw new OAuth2AuthenticationException(error);
            }

            refreshToken = (OAuth2RefreshToken) generatedRefreshToken;
            authorizationBuilder.refreshToken(refreshToken);
        }

        // ----- ID token -----
        OidcIdToken idToken;
        if (requestedScopes.contains(OidcScopes.OPENID)) {
            // @formatter:off
            tokenContext = tokenContextBuilder
                    .tokenType(ID_TOKEN_TOKEN_TYPE)
                    .authorization(authorizationBuilder.build())	// ID token customizer may need access to the access token and/or refresh token
                    .build();
            // @formatter:on
            OAuth2Token generatedIdToken = this.tokenGenerator.generate(tokenContext);
            if (!(generatedIdToken instanceof Jwt)) {
                OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
                        "The token generator failed to generate the ID token.", ERROR_URI);
                throw new OAuth2AuthenticationException(error);
            }

            if (log.isTraceEnabled()) {
                log.trace("Generated id token");
            }

            idToken = new OidcIdToken(generatedIdToken.getTokenValue(), generatedIdToken.getIssuedAt(),
                    generatedIdToken.getExpiresAt(), ((Jwt) generatedIdToken).getClaims());
            authorizationBuilder.token(idToken, (metadata) ->
                    metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, idToken.getClaims()));
        } else {
            idToken = null;
        }

        // 持久化令牌发放记录到数据库
        OAuth2Authorization authorization = authorizationBuilder.build();
        this.authorizationService.save(authorization);

        additionalParameters = (idToken != null)
                ? Collections.singletonMap(OidcParameterNames.ID_TOKEN, idToken.getTokenValue())
                : Collections.emptyMap();

        return new OAuth2AccessTokenAuthenticationToken(registeredClient, clientPrincipal, accessToken, refreshToken, additionalParameters);
    }

    /**
     * 判断传入的 authentication 类型是否与当前认证提供者（AuthenticationProvider）相匹配 - 模板方法
     * <br>
     * ProviderManager#authenticate 遍历 providers 找到支持对应认证请求的 provider - 迭代起模式
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return CaptchaAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
