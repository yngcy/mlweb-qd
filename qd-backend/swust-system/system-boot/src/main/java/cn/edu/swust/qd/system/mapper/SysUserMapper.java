package cn.edu.swust.qd.system.mapper;

import cn.edu.swust.qd.system.dto.UserAuthInfo;
import cn.edu.swust.qd.system.model.dto.UserProfileDTO;
import cn.edu.swust.qd.system.model.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
    UserProfileDTO getUserProfile(Long userId);

    /**
     * 根据用户名获取认证信息
     *
     * @param username
     * @return
     */
    UserAuthInfo getUserAuthInfo(String username);
}




