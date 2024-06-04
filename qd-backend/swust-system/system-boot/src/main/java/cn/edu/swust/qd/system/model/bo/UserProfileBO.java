package cn.edu.swust.qd.system.model.bo;

import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Data
public class UserProfileBO {

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
     * 人员密级
     */
    private String sl;

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
     * 用户角色名称集合
     */
    private Set<String> roleNames;

    /**
     * 创建角色
     */
    private Date createTime;
}
