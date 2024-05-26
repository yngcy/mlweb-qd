package cn.edu.swust.qd.scms.model.entity;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 翼舵布局表
 *
 * @TableName scms_wingflap_layout
 */
@TableName(value = "scms_wingflap_layout")
@Data
public class ScmsWingflapLayout extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 翼舵布局名称
     */
    private String name;

    /**
     * 翼舵布局类型
     */
    private String type;

    /**
     * 翼舵数目
     */
    private Integer wingflapNum;

    /**
     * 固定气动面数
     */
    private Integer fixedPneNum;

    /**
     * 活动气动面数
     */
    private Integer activePneNum;

    /**
     * 俯仰翼舵数目
     */
    private Integer pitchWingflapNum;

    /**
     * 俯仰翼舵编号集
     */
    private String pitchIdSet;

    /**
     * 偏航翼舵数目
     */
    private Integer yawWingflapNum;

    /**
     * 偏航翼舵编号集
     */
    private String yawWingflapSet;

    /**
     * 滚转翼舵数目
     */
    private Integer rollWingflapNum;

    /**
     * 滚转翼舵编号集
     */
    private String rollWingflapSet;

    /**
     * 备注
     */
    private String remark;

    /**
     * 翼舵示意图
     */
    private String wingflapImg;

    /**
     * 升降舵公式
     */
    private String deformula;

    /**
     * 方向舵公式
     */
    private String drformula;

    /**
     * 副翼舵公式
     */
    private String daformula;

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