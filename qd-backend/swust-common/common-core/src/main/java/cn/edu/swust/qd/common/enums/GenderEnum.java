package cn.edu.swust.qd.common.enums;

import cn.edu.swust.qd.common.base.IBaseEnum;
import lombok.Getter;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
public enum GenderEnum implements IBaseEnum<Integer> {

    MALE(1, "男"),
    FEMALE(2, "女"),
    UNKNOWN(0, "未知");

    @Getter
    private Integer value;

    @Getter
    private String label;

    GenderEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
