package cn.edu.swust.qd.scms.model.entity;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 天平表
 *
 * @TableName scms_balance
 */
@TableName(value = "scms_balance")
@Data
public class ScmsBalance extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 天平设计单位id
     */
    private Long companyDesignId;

    /**
     * 天平机构id
     */
    private Long companyResearchId;

    /**
     * 天平类型id
     */
    private Long balanceTypeId;

    /**
     * 天平编码
     */
    private String code;

    /**
     * 天平名称
     */
    private String name;

    /**
     * 天平设计载荷（kn）
     */
    @TableField(value = "`load`")
    private String load;

    /**
     * 天平外形尺寸（mm）
     */
    private String shapeSize;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}