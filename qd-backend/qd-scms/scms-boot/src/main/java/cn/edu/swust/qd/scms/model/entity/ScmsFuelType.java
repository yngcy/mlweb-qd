package cn.edu.swust.qd.scms.model.entity;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 燃油类型表
 *
 * @TableName scms_fuel_type
 */
@TableName(value = "scms_fuel_type")
@Data
public class ScmsFuelType extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 燃油类型名称
     */
    private String name;

    /**
     * 燃油类型描述
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