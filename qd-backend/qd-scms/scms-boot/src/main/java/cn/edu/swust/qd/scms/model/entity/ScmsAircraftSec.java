package cn.edu.swust.qd.scms.model.entity;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 飞行器次级信息
 *
 * @TableName scms_aircraft_sec
 */
@TableName(value = "scms_aircraft_sec")
@Data
public class ScmsAircraftSec extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 飞行器主信息id
     */
    private Long aircraftMainId;

    /**
     * 飞行器子级信息id
     */
    private Long aircraftSubId;

    /**
     * 飞行器子级长度（m）
     */
    private Double length;

    /**
     * 飞行器子级控制面数
     */
    private Integer numFace;

    /**
     * 飞行器最大宽度（m）
     */
    private Double maxWidth;

    /**
     * 飞行器子级最大横截面积（m²）
     */
    private Double maxCrossArea;

    /**
     * 飞行器子级底部面积（m²）
     */
    private Double maxBottomArea;

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