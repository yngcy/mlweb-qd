package cn.edu.swust.qd.scms.model.bo;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 飞行器主信息业务对象
 * <p>
 * 连表查询
 */
@Data
public class AircraftMainBO extends BaseEntity implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 气动布局类型
     */
    private String vehiType;

    /**
     * 燃油类型id
     */
    private String fuelType;

    /**
     * 飞行器id
     */
    private Long vehiId;

    /**
     * 飞行器名称
     */
    private String vehiName;

    /**
     * 设计单位
     */
    private String designDepartment;

    /**
     * 设计负责人
     */
    private String designPerson;

    /**
     * 设计日期
     */
    private Date designDate;

    /**
     * 发动机类型
     */
    private String engineType;

    /**
     * 发动机模块数
     */
    private Long moduleNum;

    /**
     * 飞行器发射平台
     */
    private String launchPlatform;

    /**
     * 飞行器总级数
     */
    private Long stagesLv;

    /**
     * 转级马赫数范围
     */
    private String machTurn;

    /**
     * 转级高度范围(km)
     */
    private String altiTurn;

    /**
     * 巡航马赫数范围
     */
    private String machCruise;

    /**
     * 巡航高度范围（km）
     */
    private String altiCruise;

    /**
     * 俯冲马赫数范围
     */
    private String machDive;

    /**
     * 俯冲高度范围（km）
     */
    private String altiDive;

    /**
     * 射程（km）
     */
    private String range;

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