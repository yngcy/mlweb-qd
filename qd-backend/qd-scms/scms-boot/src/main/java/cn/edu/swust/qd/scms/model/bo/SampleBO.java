package cn.edu.swust.qd.scms.model.bo;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 样本业务对象
 */
@Data
public class SampleBO extends BaseEntity implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 样本名称
     */
    private String name;

    /**
     * 数据类别
     */
    private String dataType;

    /**
     * 0-Excel; 1-Dat
     */
    private String fileFormat;

    /**
     * 单位
     */
    private String company;

    /**
     * 描述
     */
    private String description;

    /**
     * 下载地址
     */
    private String downloadPath;

    /**
     * 解析文件所在位置
     */
    private String locations;

    /**
     * 文件解析时的id
     */
    private Long parseId;

    /**
     * 解析内容所在 sheet（从左向右，0开始）
     */
    private Integer sheetIndex;

    /**
     * 标题行所在位置（第几行，从0开始）
     */
    private Integer titleIndex;

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