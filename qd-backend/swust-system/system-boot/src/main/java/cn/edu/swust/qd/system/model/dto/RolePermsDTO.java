package cn.edu.swust.qd.system.model.dto;

import lombok.Data;

import java.util.Set;

/**
 * 角色权限业务对象
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Data
public class RolePermsDTO {

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 权限标识集合
     */
    private Set<String> perms;
}
