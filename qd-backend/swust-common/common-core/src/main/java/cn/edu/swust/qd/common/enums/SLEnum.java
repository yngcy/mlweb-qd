package cn.edu.swust.qd.common.enums;

import cn.edu.swust.qd.common.base.IBaseEnum;
import lombok.Getter;

/**
 * 人员密级枚举
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
public enum SLEnum implements IBaseEnum {

    SEC_CP(1, "核心"),
    SEC_IP(2, "重要"),
    SEC_GP(3, "一般"),
    SEC_RP(4, "内部"),
    SEC_NP(5, "非密");

    @Getter
    private Integer value;

    @Getter
    private String label;

    SLEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
