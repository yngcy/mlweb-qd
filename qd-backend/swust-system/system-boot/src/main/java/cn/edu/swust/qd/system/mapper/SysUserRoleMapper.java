package cn.edu.swust.qd.system.mapper;

import cn.edu.swust.qd.system.model.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 25055
 * @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Mapper
 * @createDate 2024-05-04 14:42:14
 * @Entity cn.edu.swust.qd.system.model.entity.SysUserRole
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 获取角色绑定的用户数
     *
     * @param roleId
     * @return
     */
    int countUsersForRole(Long roleId);
}




