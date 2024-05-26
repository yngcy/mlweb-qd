package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "原型类型分页视图对象")
@Data
public class PrototypeTypePageVO extends BaseQdDataVO {

    /**
     * 原型类型编码
     */
    @Schema(description = "原型类型编码")
    private String code;

    /**
     * 原型类型名称
     */
    @Schema(description = "原型类型名称")
    private String name;

    /**
     * 是原型p还是模型m
     */
    @Schema(description = "原型类型名称")
    private String belong;

}