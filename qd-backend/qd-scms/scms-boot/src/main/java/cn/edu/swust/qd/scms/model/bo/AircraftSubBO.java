package cn.edu.swust.qd.scms.model.bo;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 飞行器子级业务对象
 */
@Data
public class AircraftSubBO extends BaseEntity implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 飞行器子级名称
     */
    private String name;

    /**
     * 飞行器子级描述
     */
    private String description;

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