package cn.edu.swust.qd.system.model.entity;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 人员密级表
 *
 * @TableName sys_secret_level
 */
@TableName(value = "sys_secret_level")
@Data
public class SysSecretLevel extends BaseEntity {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 人员密级名称
     */
    private String name;

    /**
     * 人员密级编码
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 状态(0:停用;1:正常)
     */
    private Integer status;

    /**
     * 是否删除(0:未删除;1:已删除)
     */
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}