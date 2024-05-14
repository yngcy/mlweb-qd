package cn.edu.swust.qd.auth.oauth2.handler;


import cn.edu.swust.qd.common.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final HttpMessageConverter<Object> accessTokenHttpResponseConverter = new MappingJackson2HttpMessageConverter();

    /**
     * 当认证失败时被调用，将认证异常转换为OAuth2错误响应。
     *
     * @param request   HttpServletRequest对象，表示客户端的请求。
     * @param response  HttpServletResponse对象，用于向客户端发送响应。
     * @param exception 认证异常对象，封装了认证失败的详细信息。
     * @throws IOException      当处理HTTP响应时发生I/O错误时抛出。
     * @throws ServletException 当处理HTTP请求时发生Servlet相关错误时抛出。
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        OAuth2Error error = ((OAuth2AuthenticationException) exception).getError();
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
        Result result = Result.failed(error.getErrorCode());
        accessTokenHttpResponseConverter.write(result, null, httpResponse);
    }
}
