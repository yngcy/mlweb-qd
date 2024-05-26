package cn.edu.swust.qd.scms.model.entity;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 翼型截面信息
 *
 * @TableName scms_wing_section
 */
@TableName(value = "scms_wing_section")
@Data
public class ScmsWingSection extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 翼型id
     */
    private Long airfoilId;

    /**
     * 翼型截面编号
     */
    private Long sectionNumber;

    /**
     * 截面位置
     */
    private String sectionLocation;

    /**
     * 翼型比例
     */
    private Double wingProportion;

    /**
     * 翼舵构型id
     */
    private Long wingflapConfigId;

    /**
     * 描述
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