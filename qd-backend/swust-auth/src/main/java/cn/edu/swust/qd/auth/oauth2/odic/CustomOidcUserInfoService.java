package cn.edu.swust.qd.auth.oauth2.odic;


import cn.edu.swust.qd.system.api.UserFeignClient;
import cn.edu.swust.qd.system.dto.UserAuthInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 自定义 OIDC 用户信息服务
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Service
@Slf4j
public class CustomOidcUserInfoService {

    private final UserFeignClient userFeignClient;

    public CustomOidcUserInfoService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    /**
     * 根据用户名加载用户信息。
     *
     * @param username 用户名。
     * @return CustomOidcUserInfo 自定义的OIDC用户信息对象。如果无法加载或发生异常，则返回null。
     */
    public CustomOidcUserInfo loadUserByUsername(String username) {
        UserAuthInfo userAuthInfo = null;
        try {
            userAuthInfo = userFeignClient.getUserAuthInfo(username);
            if (userAuthInfo == null) {
                return null;
            }
            return new CustomOidcUserInfo(createUser(userAuthInfo));
        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            return null;
        }
    }

    private Map<String, Object> createUser(UserAuthInfo userAuthInfo) {
        return CustomOidcUserInfo.customBuilder()
                .username(userAuthInfo.getUsername())
                .nickname(userAuthInfo.getNickname())
                .status(userAuthInfo.getStatus())
                .phoneNumber(userAuthInfo.getMobile())
                .email(userAuthInfo.getEmail())
                .profile(userAuthInfo.getAvatar())
                .build()
                .getClaims();
    }
}
