package cn.edu.swust.qd.scms.model.bo;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 翼型坐标业务对象
 */
@Data
public class AirfoilCoordinateBO extends BaseEntity implements Serializable {
    /**
     * id
     */
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
     * 翼型
     */
    private String airfoil;

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