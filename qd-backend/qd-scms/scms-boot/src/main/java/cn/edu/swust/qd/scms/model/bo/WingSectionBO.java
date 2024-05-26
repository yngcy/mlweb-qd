package cn.edu.swust.qd.scms.model.bo;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 翼型截面信息业务对象
 */
@Data
public class WingSectionBO extends BaseEntity implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 翼型
     */
    private String airfoil;

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