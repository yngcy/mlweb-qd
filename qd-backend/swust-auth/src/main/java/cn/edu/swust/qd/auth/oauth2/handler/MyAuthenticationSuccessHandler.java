package cn.edu.swust.qd.auth.oauth2.handler;

import com.alibaba.nacos.api.model.v2.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.DefaultOAuth2AccessTokenResponseMapConverter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * 认证成功处理器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final HttpMessageConverter<Object> accessTokenHttpResponseConverter = new MappingJackson2HttpMessageConverter();
    private final Converter<OAuth2AccessTokenResponse, Map<String, Object>> accessTokenResponseParametersConverter = new DefaultOAuth2AccessTokenResponseMapConverter();

    /**
     * 当认证成功时调用的处理器。
     * 该方法会处理OAuth2的访问令牌、刷新令牌，以及额外参数，并构建一个OAuth2访问令牌响应，
     * 然后根据不同的客户端ID，以不同的格式返回令牌响应。
     *
     * @param request        HttpServletRequest对象，代表客户端的HTTP请求
     * @param response       HttpServletResponse对象，用于向客户端发送HTTP响应
     * @param authentication 认证对象，包含用户的认证信息，例如访问令牌、刷新令牌等
     * @throws IOException      如果发生I/O错误
     * @throws ServletException 如果发生Servlet相关错误
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AccessTokenAuthenticationToken accessTokenAuthentication = (OAuth2AccessTokenAuthenticationToken) authentication;

        OAuth2AccessToken accessToken = accessTokenAuthentication.getAccessToken();
        OAuth2RefreshToken refreshToken = accessTokenAuthentication.getRefreshToken();
        Map<String, Object> additionalParameters = accessTokenAuthentication.getAdditionalParameters();

        OAuth2AccessTokenResponse.Builder builder =
                OAuth2AccessTokenResponse.withToken(accessToken.getTokenValue())
                        .tokenType(accessToken.getTokenType());
        if (accessToken.getIssuedAt() != null && accessToken.getExpiresAt() != null) {
            builder.expiresIn(ChronoUnit.SECONDS.between(accessToken.getIssuedAt(), accessToken.getExpiresAt()));
        }
        if (refreshToken != null) {
            builder.refreshToken(refreshToken.getTokenValue());
        }
        if (!CollectionUtils.isEmpty(additionalParameters)) {
            builder.additionalParameters(additionalParameters);
        }
        OAuth2AccessTokenResponse accessTokenResponse = builder.build();
        Map<String, Object> tokenResponseParameters = this.accessTokenResponseParametersConverter
                .convert(accessTokenResponse);
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);

        String clientId = accessTokenAuthentication.getRegisteredClient().getClientId();
        if ("client".equals(clientId)) {
            //  Knife4j测试客户端ID（Knife4j自动填充的 access_token 需原生返回，不能被包装成业务码数据格式）
            this.accessTokenHttpResponseConverter.write(tokenResponseParameters, null, httpResponse);
        } else {
            this.accessTokenHttpResponseConverter.write(Result.success(tokenResponseParameters), null, httpResponse);
        }

    }
}
