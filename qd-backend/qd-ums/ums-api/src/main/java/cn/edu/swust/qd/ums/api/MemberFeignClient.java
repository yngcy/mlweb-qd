package cn.edu.swust.qd.ums.api;

import cn.edu.swust.qd.common.result.Result;
import cn.edu.swust.qd.ums.dto.MemberAuthDTO;
import cn.edu.swust.qd.ums.dto.MemberRegisterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 成员Feign客户端
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@FeignClient(name = "qd-ums", contextId = "member")
public interface MemberFeignClient {

    /**
     * 新增成员
     *
     * @param member
     * @return
     */
    @PostMapping("/app-api/v1/members")
    Result<Long> registerMember(@RequestBody MemberRegisterDTO member);

    /**
     * 获取成员的 openid
     *
     * @return
     */
    @PostMapping("/app-api/v1/members/{memberId}/openid")
    Result<String> getMemberOpenId(@PathVariable Long memberId);

    /**
     * 根据openId获取成员认证信息
     *
     * @param openid
     * @return
     */
    @GetMapping("/app-api/v1/members/openid/{openid}")
    Result<MemberAuthDTO> loadUserByOpenId(@PathVariable String openid);

    /**
     * 根据手机号获取成员认证信息
     *
     * @param mobile 手机号
     * @return 成员认证信息
     */
    @GetMapping("/app-api/v1/members/mobile/{mobile}")
    Result<MemberAuthDTO> loadUserByMobile(@PathVariable String mobile);
}
