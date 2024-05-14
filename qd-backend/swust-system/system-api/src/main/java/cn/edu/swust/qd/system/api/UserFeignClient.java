package cn.edu.swust.qd.system.api;

import cn.edu.swust.qd.common.web.config.FeignDecoderConfig;
import cn.edu.swust.qd.system.api.fallback.UserFeignFallbackClient;
import cn.edu.swust.qd.system.dto.UserAuthInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@FeignClient(value = "swust-system", fallback = UserFeignFallbackClient.class, configuration = {FeignDecoderConfig.class})
public interface UserFeignClient {

    @GetMapping("/api/v1/users/{username}/auth_info")
    UserAuthInfo getUserAuthInfo(@PathVariable String username);
}
