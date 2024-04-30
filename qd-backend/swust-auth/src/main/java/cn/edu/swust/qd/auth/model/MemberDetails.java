package cn.edu.swust.qd.auth.model;

import cn.edu.swust.qd.common.constant.GlobalConstants;
import cn.edu.swust.qd.ums.dto.MemberAuthDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * 成员信息
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Data
public class MemberDetails implements UserDetails {
    /**
     * 成员ID
     */
    private Long id;

    /**
     * 成员用户名(openid/mobile)
     */
    private String username;

    /**
     * 成员状态
     */
    private Boolean enabled;

    /**
     * 扩展字段：认证身份标识，枚举值如下：
     */
    private String authenticationIdentity;

    /**
     * 成员信息构造
     *
     * @param memAuthInfo 成员认证信息
     */
    public MemberDetails(MemberAuthDTO memAuthInfo) {
        this.setId(memAuthInfo.getId());
        this.setUsername(memAuthInfo.getUsername());
        this.setEnabled(GlobalConstants.STATUS_YES.equals(memAuthInfo.getStatus()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_SET;
    }

    @Override
    public String getPassword() {
        return null;
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
