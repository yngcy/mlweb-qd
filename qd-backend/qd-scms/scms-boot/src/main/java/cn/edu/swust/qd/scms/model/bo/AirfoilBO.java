package cn.edu.swust.qd.scms.model.bo;

import cn.edu.swust.qd.common.base.BaseEntity;
import cn.edu.swust.qd.scms.model.entity.ScmsAirfoilCoordinate;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 翼型信息表
 *
 * @TableName scms_airfoil
 */
@Data
public class AirfoilBO extends BaseEntity implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 翼型类型
     */
    private String airfoilType;

    /**
     * 翼型名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

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

    /**
     * 翼型坐标
     */
    List<ScmsAirfoilCoordinate> coordinates;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}