package cn.edu.swust.qd.system.service;

import cn.edu.swust.qd.system.model.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Service
 * @createDate 2024-05-04 14:42:14
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 判断角色是否存在绑定的用户
     *
     * @param roleId
     * @return
     */
    boolean hasAssignedUsers(Long roleId);

    /**
     * 保存用户角色关联
     *
     * @param userId
     * @param roleIds
     */
    boolean saveUserRoles(Long userId, List<Integer> roleIds);
}
