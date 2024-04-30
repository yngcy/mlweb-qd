package cn.edu.swust.qd.auth.service;


import cn.edu.swust.qd.auth.model.SysUserDetails;
import cn.edu.swust.qd.common.enums.StatusEnum;
import cn.edu.swust.qd.system.api.UserFeignClient;
import cn.edu.swust.qd.system.dto.UserAuthInfo;
import cn.hutool.core.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * 系统用户信息加载服务
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Service
@RequiredArgsConstructor
public class SysUserDetailsService implements UserDetailsService {

    private final UserFeignClient userFeignClient;

    /**
     * 根据用户名获取用户信息(用户名、密码和角色权限)
     * <p>
     * 用户名、密码用于后续认证，认证成功之后将权限授予用户
     *
     * @param username 用户名
     * @return {@link  SysUserDetails}
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserAuthInfo userAuthInfo = userFeignClient.getUserAuthInfo(username);

        Assert.isTrue(userAuthInfo != null, "用户不存在");

        if (!StatusEnum.ENABLE.getValue().equals(userAuthInfo.getStatus())) {
            throw new DisabledException("该账户已被禁用!");
        }

        return new SysUserDetails(userAuthInfo);
    }


}
