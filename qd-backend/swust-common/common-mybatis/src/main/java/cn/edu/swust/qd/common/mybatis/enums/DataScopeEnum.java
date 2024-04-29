package cn.edu.swust.qd.common.mybatis.enums;

import cn.edu.swust.qd.common.base.IBaseEnum;
import lombok.Getter;

/**
 * 数据权限枚举
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Getter
public enum DataScopeEnum implements IBaseEnum<Integer> {
    ALL(0, "所有数据"),
    CL_AND_SUB(1, "本密级及子密级数据"),
    CL(2, "本密级数据"),
    SELF(3, "仅本人数据");

    private Integer value;

    private String label;

    DataScopeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
