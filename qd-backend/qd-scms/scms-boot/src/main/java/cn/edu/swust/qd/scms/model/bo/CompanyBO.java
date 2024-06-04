package cn.edu.swust.qd.scms.model.bo;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 研究机构业务对象
 */
@Data
public class CompanyBO extends BaseEntity implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 单位编码
     */
    private String code;

    /**
     * 单位名称
     */
    private String name;

    /**
     * 单位地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 所属机构
     */
    private String parent;

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