package cn.edu.swust.qd.system.service.impl;

import cn.edu.swust.qd.common.constant.SystemConstants;
import cn.edu.swust.qd.common.security.utils.SecurityUtils;
import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.system.converter.RoleConverter;
import cn.edu.swust.qd.system.mapper.SysRoleMapper;
import cn.edu.swust.qd.system.model.entity.SysRole;
import cn.edu.swust.qd.system.model.entity.SysRoleMenu;
import cn.edu.swust.qd.system.model.form.RoleForm;
import cn.edu.swust.qd.system.model.query.RolePageQuery;
import cn.edu.swust.qd.system.model.vo.RolePageVO;
import cn.edu.swust.qd.system.service.SysRoleMenuService;
import cn.edu.swust.qd.system.service.SysRoleService;
import cn.edu.swust.qd.system.service.SysUserRoleService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 25055
 * @description 针对表【sys_role(角色表)】的数据库操作Service实现
 * @createDate 2024-05-04 14:40:35
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {

    private final SysUserRoleService userRoleService;
    private final SysRoleMenuService roleMenuService;
    private final RoleConverter roleConverter;

    @Override
    public Page<RolePageVO> getRolePage(RolePageQuery queryParams) {
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keyword = queryParams.getKeywords();

        Page<SysRole> rolePage = this.page(new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<SysRole>()
                        .and(StrUtil.isNotBlank(keyword),
                                wrapper ->
                                        wrapper.like(StrUtil.isNotBlank(keyword), SysRole::getName, keyword)
                                                .or()
                                                .like(StrUtil.isNotBlank(keyword), SysRole::getCode, keyword))
                        .ne(!SecurityUtils.isRoot(), SysRole::getCode, SystemConstants.ROOT_ROLE_CODE) // 非超级管理员不显示管理员
        );

        Page<RolePageVO> result = roleConverter.entity2VO(rolePage);
        return result;
    }

    @Override
    public boolean saveRole(RoleForm roleForm) {

        Long roleId = roleForm.getId();
        // 编辑角色时，查询是否存在
        SysRole oldRole = null;
        if (roleId != null) {
            oldRole = this.getById(roleId);
            Assert.isTrue(oldRole != null, "角色不存在");
        }
        String roleCode = roleForm.getCode();
        long count = this.count(new LambdaQueryWrapper<SysRole>()
                .ne(roleId != null, SysRole::getId, roleId)
                .and(wrapper ->
                        wrapper.eq(SysRole::getCode, roleCode).or().eq(SysRole::getName, roleForm.getName())
                ));
        Assert.isTrue(count == 0, "角色名称或角色编码已存在，请修改后重试！");

        // 实体转换
        SysRole role = roleConverter.form2Entity(roleForm);
        boolean result = this.saveOrUpdate(role);

        if (result) {
            if (oldRole != null && (
                    !StrUtil.equals(oldRole.getCode(), roleCode) ||
                            !ObjectUtil.equals(oldRole.getStatus(), roleForm.getStatus())
            )) {
                roleMenuService.refreshRolePermsCache(oldRole.getCode(), roleCode);
            }
        }

        return result;
    }

    @Override
    public RoleForm getRoleForm(Long roleId) {
        SysRole entity = this.getById(roleId);
        return roleConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteRoles(String ids) {
        List<Long> roleIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long roleId : roleIds) {
            SysRole role = this.getById(roleId);
            Assert.isTrue(role != null, "角色不存在");

            // 判断角色是否被用户关联
            boolean isRoleAssigned = userRoleService.hasAssignedUsers(roleId);
            Assert.isTrue(!isRoleAssigned, "角色【{}】已分配用户，请先解除关联后删除", role.getName());

            boolean deleteResult = this.removeById(roleId);
            if (deleteResult) {
                // 删除成功，刷新权限缓存
                roleMenuService.refreshRolePermsCache(role.getCode());
            }

        }
        return true;
    }

    @Override
    public boolean updateRoleStatus(Long roleId, Integer status) {
        SysRole role = this.getById(roleId);
        Assert.isTrue(role != null, "角色不存在");

        role.setStatus(status);
        boolean result = this.updateById(role);
        if (result) {
            roleMenuService.refreshRolePermsCache(role.getCode());
        }
        return result;
    }

    @Override
    public Integer getMaxDataRangeScope(Set<String> roles) {
        Integer dataScope = this.baseMapper.getMaxDataRangeScope(roles);
        return dataScope;
    }

    @Override
    public List<Option> listRoleOptions() {
        // 查询数据
        List<SysRole> roleList = this.list(new LambdaQueryWrapper<SysRole>()
                .ne(!SecurityUtils.isRoot(), SysRole::getCode, SystemConstants.ROOT_ROLE_CODE)
                .select(SysRole::getId, SysRole::getName)
                .orderByDesc(SysRole::getSort));
        // 实体转换
        return roleConverter.entities2Options(roleList);
    }

    @Override
    public List<Long> getRoleMenuIds(Long roleId) {
        return roleMenuService.listMenuIdsByRoleId(roleId);
    }

    @Override
    public boolean assignMenusToRole(Long roleId, List<Long> menuIds) {
        SysRole role = this.getById(roleId);
        Assert.isTrue(role != null, "角色不存在");

        // 删除角色菜单
        roleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getRoleId, roleId));

        // 新增角色菜单
        if (CollectionUtil.isNotEmpty(menuIds)) {
            List<SysRoleMenu> roleMenus = menuIds.stream()
                    .map(menuId -> new SysRoleMenu(roleId, menuId))
                    .collect(Collectors.toList());
            roleMenuService.saveBatch(roleMenus);
        }

        // 刷新角色的权限缓存
        roleMenuService.refreshRolePermsCache(role.getCode());
        return true;
    }
}




