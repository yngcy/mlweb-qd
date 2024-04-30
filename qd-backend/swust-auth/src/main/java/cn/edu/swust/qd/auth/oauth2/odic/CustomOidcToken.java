package cn.edu.swust.qd.auth.oauth2.odic;

import cn.hutool.core.lang.Assert;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;

/**
 * 自定义 OdicToken
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
public class CustomOidcToken extends OidcUserInfoAuthenticationToken {

    private final Authentication principal;

    private final CustomOidcUserInfo userInfo;

    public CustomOidcToken(Authentication principal) {
        super(principal);
        Assert.notNull(principal, "principal cannot be null");
        this.principal = principal;
        this.userInfo = null;
        this.setAuthenticated(false);
    }

    public CustomOidcToken(Authentication principal, CustomOidcUserInfo userInfo) {
        super(principal, userInfo);
        Assert.notNull(principal, "principal cannot be null");
        Assert.notNull(userInfo, "userInfo cannot be null");
        this.principal = principal;
        this.userInfo = userInfo;
        this.setAuthenticated(true);
    }


    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return this.userInfo;
    }
}
