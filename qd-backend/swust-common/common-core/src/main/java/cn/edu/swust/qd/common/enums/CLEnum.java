package cn.edu.swust.qd.common.enums;

import cn.edu.swust.qd.common.base.IBaseEnum;
import lombok.Getter;

/**
 * 数据密级枚举
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
public enum CLEnum implements IBaseEnum<Integer> {

    SEC_TOP(1, "绝密"),
    SEC_MID(2, "机密"),
    SEC_MIN(3, "秘密"),
    SEC_AUTH(4, "内部"),
    SEC_OPEN(5, "非密"),
    SEC_NONE(6, "无级别");

    @Getter
    private Integer value;

    @Getter
    private String label;

    CLEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
