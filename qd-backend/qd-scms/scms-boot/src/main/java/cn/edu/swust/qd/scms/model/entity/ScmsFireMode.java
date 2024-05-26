package cn.edu.swust.qd.scms.model.entity;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 点火方式表
 *
 * @TableName scms_fire_mode
 */
@TableName(value = "scms_fire_mode")
@Data
public class ScmsFireMode extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 点火方式名称
     */
    private String name;

    /**
     * 点火方式描述
     */
    private String description;

    /**
     * 自定密级
     */
    private Integer security;

    /**
     * 创建用户id
     */
    private Long createUser;

    /**
     * 更新用户id
     */
    private Long updateUser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}