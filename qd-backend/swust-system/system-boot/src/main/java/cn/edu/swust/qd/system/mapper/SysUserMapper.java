package cn.edu.swust.qd.system.mapper;

import cn.edu.swust.qd.system.dto.UserAuthInfo;
import cn.edu.swust.qd.system.model.bo.UserBO;
import cn.edu.swust.qd.system.model.bo.UserFormBO;
import cn.edu.swust.qd.system.model.bo.UserProfileBO;
import cn.edu.swust.qd.system.model.entity.SysUser;
import cn.edu.swust.qd.system.model.query.UserPageQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 25055
 * @description 针对表【sys_user(用户表)】的数据库操作Mapper
 * @createDate 2024-05-04 14:39:13
 * @Entity cn.edu.swust.qd.system.model.entity.SysUser
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 获取用户详情
     *
     * @param userId
     * @return
     */
    UserProfileBO getUserProfile(Long userId);

    /**
     * 根据用户名获取认证信息
     *
     * @param username
     * @return
     */
    UserAuthInfo getUserAuthInfo(String username);

    /**
     * 分页获取用户列表
     *
     * @param page
     * @param queryParams
     * @return
     */
    Page<UserBO> getUserPage(Page<UserBO> page, UserPageQuery queryParams);

    /**
     * 获取用户表单详情
     *
     * @param userId
     * @return
     */
    UserFormBO getUserDetail(Long userId);
}




