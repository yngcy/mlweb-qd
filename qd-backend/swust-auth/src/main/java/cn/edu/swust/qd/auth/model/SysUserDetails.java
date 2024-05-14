package cn.edu.swust.qd.auth.model;

import cn.edu.swust.qd.common.enums.StatusEnum;
import cn.edu.swust.qd.system.dto.UserAuthInfo;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import lombok.Data;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

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
     * 扩展字段：人员密级ID
     */
    private Integer slId;

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
    public SysUserDetails(UserAuthInfo user) {
        this.setUserId(user.getUserId());
        this.setUsername(user.getUsername());
        this.setSlId(user.getSlId());
        this.setDataScope(user.getDataScope());
        this.setPassword("{bcrypt}" + user.getPassword());
        this.setEnabled(StatusEnum.ENABLE.getValue().equals(user.getStatus()));
        if (CollectionUtil.isNotEmpty(user.getRoles())) {
            authorities = user.getRoles().stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }
        this.setPerms(user.getPerms());
    }


    public SysUserDetails(
            Long userId,
            String username,
            String password,
            Integer dataScope,
            Integer slId,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Set<? extends GrantedAuthority> authorities
    ) {
        Assert.isTrue(username != null && !"".equals(username) && password != null, "Cannot pass pull or empty values to constructor");
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.dataScope = dataScope;
        this.slId = slId;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = Collections.unmodifiableSet(authorities);
    }

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
