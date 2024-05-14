package cn.edu.swust.qd.system.service;

import cn.edu.swust.qd.system.dto.UserAuthInfo;
import cn.edu.swust.qd.system.model.entity.SysUser;
import cn.edu.swust.qd.system.model.form.UserForm;
import cn.edu.swust.qd.system.model.form.UserRegisterForm;
import cn.edu.swust.qd.system.model.query.UserPageQuery;
import cn.edu.swust.qd.system.model.vo.UserExportVO;
import cn.edu.swust.qd.system.model.vo.UserInfoVO;
import cn.edu.swust.qd.system.model.vo.UserPageVO;
import cn.edu.swust.qd.system.model.vo.UserProfileVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【sys_user(用户表)】的数据库操作Service
 * @createDate 2024-05-04 14:39:13
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 用户分页列表
     *
     * @param queryParams 查询参数，支持关键词、状态、密级筛选
     * @return {@link Page <UserPageVO>}
     */
    Page<UserPageVO> getUserPage(UserPageQuery queryParams);

    /**
     * 获取用户表单数据
     *
     * @param userId
     * @return
     */
    UserForm getUserForm(Long userId);

    /**
     * 保存用户
     *
     * @param userForm
     * @return
     */
    boolean saveUser(UserForm userForm);

    /**
     * 更新用户（管理员）
     *
     * @param userId
     * @param userForm
     * @return
     */
    boolean updateUser(Long userId, UserForm userForm);

    /**
     * 更新密码
     *
     * @param userId
     * @param password
     * @return
     */
    boolean updatePassword(Long userId, String password);

    /**
     * 获取用户认证信息
     *
     * @param username
     * @return
     */
    UserAuthInfo getUserAuthInfo(String username);

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    UserInfoVO getCurrentUserInfo();

    /**
     * 注册
     *
     * @param userRegisterForm
     * @return
     */
    boolean registerUser(UserRegisterForm userRegisterForm);

    /**
     * 发送注册短信验证码
     *
     * @param mobile
     * @return
     */
    boolean sendRegistrationSmsCode(String mobile);

    /**
     * 获取用户个人中心信息
     *
     * @return
     */
    UserProfileVO getUserProfile();

    /**
     * 获取导出用户列表
     *
     * @param queryParams
     * @return
     */
    List<UserExportVO> listExportUsers(UserPageQuery queryParams);

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    boolean deleteUsers(String ids);

    /**
     * 注销登出
     *
     * @return
     */
    boolean logout();
}
