package cn.edu.swust.qd.system.model.bo;

import lombok.Data;

import java.util.List;

/**
 * 用户持久化表单对象
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Data
public class UserFormBO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别(1:男;2:女)
     */
    private Integer gender;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 人员密级ID
     */
    private Integer slId;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 状态(1:正常;0:禁用)
     */
    private Integer status;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 角色ID集合
     */
    private List<Integer> roleIds;

}
