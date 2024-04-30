package cn.edu.swust.qd.auth.service;

import cn.edu.swust.qd.auth.model.MemberDetails;
import cn.edu.swust.qd.common.enums.StatusEnum;
import cn.edu.swust.qd.common.result.Result;
import cn.edu.swust.qd.common.result.ResultCode;
import cn.edu.swust.qd.ums.api.MemberFeignClient;
import cn.edu.swust.qd.ums.dto.MemberAuthDTO;
import cn.edu.swust.qd.ums.dto.MemberRegisterDTO;
import cn.hutool.core.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 成员认证服务
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Service
@RequiredArgsConstructor
public class MemberDetailsService {

    private final MemberFeignClient memberFeignClient;

    /**
     * 手机号码认证方式
     *
     * @param mobile 手机号
     * @return 用户信息
     */
    public UserDetails loadUserByMobile(String mobile) {
        Result<MemberAuthDTO> result = memberFeignClient.loadUserByMobile(mobile);

        MemberAuthDTO memberAuthInfo;
        if (!(Result.isSuccess(result) && (memberAuthInfo = result.getData()) != null)) {
            throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMsg());
        }
        MemberDetails userDetails = new MemberDetails(memberAuthInfo);
        if (!userDetails.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return userDetails;
    }


    /**
     * 根据用户名获取用户信息
     *
     * @param openid 微信公众平台唯一身份标识
     * @return {@link MemberDetails}
     */
    public UserDetails loadUserByOpenid(String openid) {
        // 根据 openid 获取微信用户认证信息
        Result<MemberAuthDTO> getMemberAuthInfoResult = memberFeignClient.loadUserByOpenId(openid);

        MemberAuthDTO memberAuthInfo = null;

        // 成员不存在，注册成为新成员
        if (ResultCode.USER_NOT_EXIST.getCode().equals(getMemberAuthInfoResult.getCode())) {
            MemberRegisterDTO memberRegisterInfo = new MemberRegisterDTO();
            memberRegisterInfo.setOpenid(openid);
            memberRegisterInfo.setNickName("微信用户");
            // 注册成员
            Result<Long> registerMemberResult = memberFeignClient.registerMember(memberRegisterInfo);
            // 注册成功将成员信息赋值给成员认证信息
            Long memberId;
            if (Result.isSuccess(registerMemberResult) && (memberId = registerMemberResult.getData()) != null) {
                memberAuthInfo = new MemberAuthDTO(memberId, openid, StatusEnum.ENABLE.getValue());
            }
        } else {
            Assert.isTrue((memberAuthInfo = getMemberAuthInfoResult.getData()) != null, "成员认证信息失败");
        }

        // 用户不存在
        if (memberAuthInfo == null) {
            throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMsg());
        }

        UserDetails userDetails = new MemberDetails(memberAuthInfo);
        if (!userDetails.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return userDetails;
    }
}
