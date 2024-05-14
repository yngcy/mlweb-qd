package cn.edu.swust.qd.ums.service;

import cn.edu.swust.qd.ums.dto.MemberAuthDTO;
import cn.edu.swust.qd.ums.dto.MemberRegisterDTO;
import cn.edu.swust.qd.ums.model.entity.UmsMember;
import cn.edu.swust.qd.ums.model.form.MemberForm;
import cn.edu.swust.qd.ums.model.query.MemberPageQuery;
import cn.edu.swust.qd.ums.model.vo.MemberPageVO;
import cn.edu.swust.qd.ums.model.vo.MemberVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 25055
 * @description 针对表【ums_member】的数据库操作Service
 * @createDate 2024-05-07 14:20:05
 */
public interface UmsMemberService extends IService<UmsMember> {

    /**
     * 获取用户分页列表（管理员）
     *
     * @param queryParams
     * @return
     */
    Page<MemberPageVO> getMemberPage(MemberPageQuery queryParams);

    /**
     * 更新用户
     *
     * @param memberForm
     * @return
     */
    boolean updateMember(MemberForm memberForm);

    /**
     * 更新用户状态
     *
     * @param memberId
     * @param status
     * @return
     */
    boolean updateMemberStatus(Long memberId, Integer status);

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    boolean deleteMembers(String ids);

    /**
     * 新增用户
     *
     * @param member
     * @return
     */
    Long addMember(MemberRegisterDTO member);

    /**
     * 获取当前登录成员信息
     *
     * @return
     */
    MemberVO getCurrentMemberInfo();

    /**
     * 根据 openid 获取成员认证信息
     *
     * @param openid
     * @return
     */
    MemberAuthDTO getMemberByOpenid(String openid);

    /**
     * 根据手机号获取成员认证信息
     *
     * @param mobile
     * @return
     */
    MemberAuthDTO getMemberByMobile(String mobile);
}
