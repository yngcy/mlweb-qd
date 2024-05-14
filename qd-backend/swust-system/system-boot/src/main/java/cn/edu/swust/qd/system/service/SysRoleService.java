package cn.edu.swust.qd.system.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.system.model.entity.SysRole;
import cn.edu.swust.qd.system.model.form.RoleForm;
import cn.edu.swust.qd.system.model.query.RolePageQuery;
import cn.edu.swust.qd.system.model.vo.RolePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * @author 25055
 * @description 针对表【sys_role(角色表)】的数据库操作Service
 * @createDate 2024-05-04 14:40:35
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 获取角色分页
     *
     * @param queryParams
     * @return
     */
    Page<RolePageVO> getRolePage(RolePageQuery queryParams);

    /**
     * 保存角色
     *
     * @param roleForm
     * @return
     */
    boolean saveRole(RoleForm roleForm);

    /**
     * 获取角色表单数据
     *
     * @param roleId
     * @return
     */
    RoleForm getRoleForm(Long roleId);

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    boolean deleteRoles(String ids);

    /**
     * 修改角色状态
     *
     * @param roleId
     * @param status
     * @return
     */
    boolean updateRoleStatus(Long roleId, Integer status);

    /**
     * 获得最大数据范围权限
     *
     * @param roles 角色编码集合
     * @return
     */
    Integer getMaxDataRangeScope(Set<String> roles);

    /**
     * 角色下拉列表
     *
     * @return {@link List<Option>} – 角色下拉列表
     */
    List<Option> listRoleOptions();

    /**
     * 获取角色的菜单ID集合
     *
     * @param roleId 角色ID
     * @return 菜单ID集合(包括按钮权限ID)
     */
    List<Long> getRoleMenuIds(Long roleId);

    /**
     * 分配角色资源权限
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID集合
     * @return {@link Boolean}
     */
    boolean assignMenusToRole(Long roleId, List<Long> menuIds);

}
