package cn.edu.swust.qd.system.mapper;

import cn.edu.swust.qd.system.model.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * @author 25055
 * @description 针对表【sys_role(角色表)】的数据库操作Mapper
 * @createDate 2024-05-04 14:40:35
 * @Entity cn.edu.swust.qd.system.model.entity.SysRole
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 获取最大范围的数据权限
     *
     * @param roles
     * @return
     */
    Integer getMaxDataRangeScope(Set<String> roles);
}




