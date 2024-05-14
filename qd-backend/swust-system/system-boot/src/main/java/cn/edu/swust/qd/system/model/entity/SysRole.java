package cn.edu.swust.qd.system.model.entity;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色表
 *
 * @TableName sys_role
 */
@TableName(value = "sys_role")
@Data
public class SysRole extends BaseEntity {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 角色状态(1:正常;2:停用)
     */
    private Integer status;

    /**
     * 逻辑删除标识(0-未删除；1-已删除)
     */
    private Integer deleted;

    /**
     * 数据权限(0-所有数据；1-密级及子密级数据；2-本密级数据；3-本人数据)
     */
    private Integer dataScope;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}