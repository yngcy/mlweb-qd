package cn.edu.swust.qd.common.base;

import cn.hutool.core.util.ObjectUtil;

import java.util.EnumSet;

/**
 * 枚举通用接口
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
public interface IBaseEnum<T> {
    T getValue();

    String getLabel();

    /**
     * 根据值获取枚举
     *
     * @param value
     * @param clazz
     * @param <E>
     * @return
     */
    static <E extends Enum<E> & IBaseEnum> E getEnumByValue(Object value, Class<E> clazz) {
        if (value == null) {
            return null;
        }

        EnumSet<E> allEnums = EnumSet.allOf(clazz);
        return allEnums.stream()
                .filter(e -> ObjectUtil.equals(e.getValue(), value))
                .findFirst()
                .orElse(null);
    }

    /**
     * 根据值获取标签
     *
     * @param value
     * @param clazz
     * @param <E>
     * @return
     */
    static <E extends Enum<E> & IBaseEnum> String getLabelByValue(Object value, Class<E> clazz) {
        E matchEnum = IBaseEnum.getEnumByValue(value, clazz);

        String label = null;
        if (matchEnum != null) {
            label = matchEnum.getLabel();
        }
        return label;
    }

    /**
     * 根据标签获取值
     *
     * @param label
     * @param clazz
     * @param <E>
     * @return
     */
    static <E extends Enum<E> & IBaseEnum> Object getValueByLabel(String label, Class<E> clazz) {
        if (label == null) {
            return null;
        }

        EnumSet<E> allEnums = EnumSet.allOf(clazz);
        E matchEnum = allEnums.stream()
                .filter(e -> ObjectUtil.equals(e.getLabel(), label))
                .findFirst()
                .orElse(null);
        return matchEnum == null ? null : matchEnum.getValue();
    }
}
