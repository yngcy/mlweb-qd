package cn.edu.swust.qd.scms.model.bo;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 风洞业务对象
 */
@Data
public class WindTunnelBO extends BaseEntity implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 风洞编码
     */
    private String code;

    /**
     * 风洞名称
     */
    private String name;

    /**
     * 风洞类型
     */
    private String wtType;

    /**
     * 所属单位
     */
    private String company;

    /**
     * 风洞有效试验时间范围
     */
    private String rangeTestTime;

    /**
     * 风洞试验段长度（m）
     */
    private String lenTetssec;

    /**
     * 风洞试验段横截面尺寸（m）
     */
    private String sizeTestesc;

    /**
     * 风洞喷管出口尺寸（m）
     */
    private String sizeNozexit;

    /**
     * 风洞速度范围（m/s）
     */
    private String rangeV;

    /**
     * 风洞雷诺数范围
     */
    private String rangeRe;

    /**
     * 风洞努森数范围
     */
    private String rangeNusselt;

    /**
     * 风洞总压范围（MPa）
     */
    private String rangePt;

    /**
     * 风洞总温范围（℃）
     */
    private String rangeTt;

    /**
     * 模拟高度范围（m）
     */
    private String rangeAlt;

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