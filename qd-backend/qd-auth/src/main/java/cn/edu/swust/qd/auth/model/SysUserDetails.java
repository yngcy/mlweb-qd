package cn.edu.swust.qd.auth.model;

import lombok.Data;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 系统用户用户信息（包含用户名、密码和权限）<br/>
 * 用户名和密码用于认证，认证成功之后授予权限
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Data
public class SysUserDetails implements UserDetails, CredentialsContainer {

    /**
     * 扩展字段：用户ID
     */
    private Long userId;

    /**
     * 用户角色数据权限集合
     */
    private Integer dataScope;

    /**
     * 默认字段
     */
    private String username;
    private String password;
    private Boolean enabled;
    private Collection<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private Set<String> perms;

    /**
     * 系统管理用户
     */


    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
