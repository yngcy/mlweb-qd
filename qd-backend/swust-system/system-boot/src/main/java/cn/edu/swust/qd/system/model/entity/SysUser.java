package cn.edu.swust.qd.system.model.entity;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户表
 *
 * @TableName sys_user
 */
@TableName(value = "sys_user")
@Data
public class SysUser extends BaseEntity {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
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
     * 是否删除(0:未删除;1:已删除)
     */
    private Integer deleted;

    /**
     * 创建者ID
     */
    private Long createUser;

    /**
     * 更新者ID
     */
    private Long updateUser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}