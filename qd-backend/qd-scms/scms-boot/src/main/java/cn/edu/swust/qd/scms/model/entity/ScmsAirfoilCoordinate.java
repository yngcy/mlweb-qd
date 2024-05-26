package cn.edu.swust.qd.scms.model.entity;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 翼型坐标表
 *
 * @TableName scms_airfoil_coordinate
 */
@TableName(value = "scms_airfoil_coordinate")
@Data
public class ScmsAirfoilCoordinate extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 翼型表面标识
     */
    private Integer surfaceIdent;

    /**
     * 沿弦向x值
     */
    private Double alongStringX;

    /**
     * 翼型表面y值
     */
    private Double airfoilFaceY;

    /**
     * 翼型id
     */
    private Long airfoilId;

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