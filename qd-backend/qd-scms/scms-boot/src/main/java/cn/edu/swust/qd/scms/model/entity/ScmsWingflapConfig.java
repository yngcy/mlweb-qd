package cn.edu.swust.qd.scms.model.entity;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 翼舵构型表
 *
 * @TableName scms_wingflap_config
 */
@TableName(value = "scms_wingflap_config")
@Data
public class ScmsWingflapConfig extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 翼舵构型名称
     */
    private String name;

    /**
     * 翼舵面积（㎡）
     */
    private Double area;

    /**
     * 暴露展长（m）
     */
    private Double exposedLength;

    /**
     * 翼根弦长（m）
     */
    private Double chordLength;

    /**
     * 跟弦扭转角
     */
    private Double twistChordAngle;

    /**
     * 前沿后掠角
     */
    private Double leSweepBackAngle;

    /**
     * 后沿后掠角
     */
    private Double teSweepBackAngle;

    /**
     * 上反角
     */
    private Double dihedralAngle;

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