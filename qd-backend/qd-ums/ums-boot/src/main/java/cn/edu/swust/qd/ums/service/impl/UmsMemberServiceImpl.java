package cn.edu.swust.qd.ums.service.impl;

import cn.edu.swust.qd.common.result.ResultCode;
import cn.edu.swust.qd.common.security.utils.SecurityUtils;
import cn.edu.swust.qd.common.web.exception.BizException;
import cn.edu.swust.qd.ums.converter.MemberConverter;
import cn.edu.swust.qd.ums.dto.MemberAuthDTO;
import cn.edu.swust.qd.ums.dto.MemberRegisterDTO;
import cn.edu.swust.qd.ums.mapper.UmsMemberMapper;
import cn.edu.swust.qd.ums.model.entity.UmsMember;
import cn.edu.swust.qd.ums.model.form.MemberForm;
import cn.edu.swust.qd.ums.model.query.MemberPageQuery;
import cn.edu.swust.qd.ums.model.vo.MemberPageVO;
import cn.edu.swust.qd.ums.model.vo.MemberVO;
import cn.edu.swust.qd.ums.service.UmsMemberService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author 25055
 * @description 针对表【ums_member】的数据库操作Service实现
 * @createDate 2024-05-07 14:20:05
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember>
        implements UmsMemberService {

    private final RedisTemplate redisTemplate;
    private final MemberConverter memberConverter;


    @Override
    public Page<MemberPageVO> getMemberPage(MemberPageQuery queryParams) {
        int pageSize = queryParams.getPageSize();
        int pageNum = queryParams.getPageNum();
        String nickname = queryParams.getNickName();

        Page<UmsMember> memberPage = this.page(new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<UmsMember>()
                        .and(StrUtil.isNotBlank(nickname),
                                wrapper ->
                                        wrapper.like(StrUtil.isNotBlank(nickname), UmsMember::getNickName, nickname)) // 非超级管理员不显示管理员
        );

        Page<MemberPageVO> result = memberConverter.entity2VO(memberPage);
        return result;
    }

    @Override
    public boolean updateMember(MemberForm memberForm) {
        Long memberId = memberForm.getId();
        UmsMember oldMember = this.getById(memberId);
        Assert.isTrue(oldMember != null, "成员不存在");

        String mobile = memberForm.getMobile();
        long count = this.count(new LambdaQueryWrapper<UmsMember>()
                .ne(memberId != null, UmsMember::getId, memberId)
                .and(wrapper ->
                        wrapper.eq(UmsMember::getMobile, mobile)
                                .or()
                                .eq(UmsMember::getOpenid, memberForm.getOpenid())
                ));
        Assert.isTrue(count == 0, "手机号或OpenId已存在，请修改后重试！");

        // 实体转换
        UmsMember member = memberConverter.form2Entity(memberForm);
        boolean result = this.updateById(member);

        return result;
    }

    @Override
    public boolean updateMemberStatus(Long memberId, Integer status) {
        UmsMember member = this.getById(memberId);
        Assert.isTrue(member != null, "成员不存在");

        member.setStatus(status);
        boolean result = this.updateById(member);
        return result;
    }

    @Override
    public boolean deleteMembers(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除数据为空");

        List<Long> memberIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        // todo 删除工程中的成员

        boolean result = this.removeByIds(memberIds);
        return result;
    }

    @Override
    public Long addMember(MemberRegisterDTO member) {
        UmsMember umsMember = memberConverter.dto2Entity(member);
        boolean result = this.save(umsMember);
        Assert.isTrue(result, "新增成员失败");
        return umsMember.getId();
    }

    @Override
    public MemberVO getCurrentMemberInfo() {
        Long memberId = SecurityUtils.getMemberId();
        UmsMember umsMember = this.getOne(new LambdaQueryWrapper<UmsMember>()
                .eq(UmsMember::getId, memberId)
                .select(
                        UmsMember::getId,
                        UmsMember::getNickName,
                        UmsMember::getAvatarUrl,
                        UmsMember::getMobile
                ));
        MemberVO memberVO = new MemberVO();
        BeanUtil.copyProperties(umsMember, memberVO);
        return memberVO;
    }

    @Override
    public MemberAuthDTO getMemberByOpenid(String openid) {
        UmsMember entity = this.getOne(new LambdaQueryWrapper<UmsMember>()
                .eq(UmsMember::getOpenid, openid)
                .select(
                        UmsMember::getId,
                        UmsMember::getOpenid,
                        UmsMember::getStatus
                ));
        if (entity == null) {
            throw new BizException(ResultCode.USER_NOT_EXIST);
        }
        return memberConverter.entity2OpenidAuthDTO(entity);
    }

    @Override
    public MemberAuthDTO getMemberByMobile(String mobile) {
        UmsMember entity = this.getOne(new LambdaQueryWrapper<UmsMember>()
                .eq(UmsMember::getMobile, mobile)
                .select(
                        UmsMember::getId,
                        UmsMember::getMobile,
                        UmsMember::getStatus
                ));
        if (entity == null) {
            throw new BizException(ResultCode.USER_NOT_EXIST);
        }
        return memberConverter.entity2MobileAuthDTO(entity);
    }
}




