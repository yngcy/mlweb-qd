package cn.edu.swust.qd.system.enums;

import cn.edu.swust.qd.common.base.IBaseEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 菜单类型枚举
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
public enum MenuTypeEnum implements IBaseEnum<Integer> {
    NULL(0, null),
    MENU(1, "菜单"),
    CATALOG(2, "目录"),
    EXTLINK(3, "外链"),

    BUTTON(4, "按钮");

    @Getter
    @EnumValue //  Mybatis-Plus 提供注解表示插入数据库时插入该值
    private Integer value;

    @Getter
    // @JsonValue //  表示对枚举序列化时返回此字段
    private String label;

    MenuTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
