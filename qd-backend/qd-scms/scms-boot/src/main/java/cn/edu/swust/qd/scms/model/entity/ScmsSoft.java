package cn.edu.swust.qd.scms.model.entity;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 软件表
 *
 * @TableName scms_soft
 */
@TableName(value = "scms_soft")
@Data
public class ScmsSoft extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 软件编码
     */
    private String code;

    /**
     * 软件名称
     */
    private String name;

    /**
     * 软件版本
     */
    private String version;

    /**
     * 软件供应商
     */
    private String provider;

    /**
     * 软件功能简介
     */
    private String funcDescription;

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