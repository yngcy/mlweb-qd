package cn.edu.swust.qd.system.service.impl;

import cn.edu.swust.qd.system.mapper.SysUserRoleMapper;
import cn.edu.swust.qd.system.model.entity.SysUserRole;
import cn.edu.swust.qd.system.service.SysUserRoleService;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 25055
 * @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Service实现
 * @createDate 2024-05-04 14:42:14
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole>
        implements SysUserRoleService {

    @Override
    public boolean hasAssignedUsers(Long roleId) {
        int count = this.baseMapper.countUsersForRole(roleId);
        return count > 0;
    }

    @Override
    public boolean saveUserRoles(Long userId, List<Integer> roleIds) {

        if (userId == null || CollectionUtil.isEmpty(roleIds)) {
            return false;
        }

        // 用户原角色ID集合
        List<Integer> userRoleIds = this.list(new LambdaQueryWrapper<SysUserRole>()
                        .eq(SysUserRole::getUserId, userId))
                .stream()
                .map(item -> item.getRoleId())
                .collect(Collectors.toList());
        // 新增用户角色
        List<Integer> saveRoleIds;
        if (CollectionUtil.isEmpty(userRoleIds)) {
            saveRoleIds = roleIds;
        } else {
            saveRoleIds = roleIds.stream()
                    .filter(roleId -> !userRoleIds.contains(roleId))
                    .collect(Collectors.toList());
        }

        List<SysUserRole> saveUserRoles = saveRoleIds
                .stream()
                .map(roleId -> new SysUserRole(userId, roleId))
                .collect(Collectors.toList());
        this.saveBatch(saveUserRoles);

        // 删除用户角色
        if (CollectionUtil.isNotEmpty(userRoleIds)) {
            List<Integer> removeRoleIds = userRoleIds.stream()
                    .filter(roleId -> !roleIds.contains(roleId))
                    .collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(removeRoleIds)) {
                this.remove(new LambdaQueryWrapper<SysUserRole>()
                        .eq(SysUserRole::getUserId, userId)
                        .in(SysUserRole::getRoleId, removeRoleIds));
            }
        }
        return true;
    }
}




