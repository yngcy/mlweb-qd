package cn.edu.swust.qd.scms.model.bo;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 天平业务对象
 */
@Data
public class BalanceBO extends BaseEntity implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 天平设计单位
     */
    private String companyDesign;

    /**
     * 天平机构id
     */
    private String companyResearch;

    /**
     * 天平类型
     */
    private String balanceType;

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