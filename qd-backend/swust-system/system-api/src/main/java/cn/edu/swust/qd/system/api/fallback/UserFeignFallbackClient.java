package cn.edu.swust.qd.system.api.fallback;


import cn.edu.swust.qd.system.api.UserFeignClient;
import cn.edu.swust.qd.system.dto.UserAuthInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 系统用户服务远程调用异常后降级处理
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Component
@Slf4j
public class UserFeignFallbackClient implements UserFeignClient {

    @Override
    public UserAuthInfo getUserAuthInfo(String name) {
        log.error("feign远程调用系统用户服务异常后的降级方法");
        return new UserAuthInfo();
    }
}
