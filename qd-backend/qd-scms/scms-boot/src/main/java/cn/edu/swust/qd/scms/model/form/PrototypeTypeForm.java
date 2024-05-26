package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "原型类型表单")
@Data
public class PrototypeTypeForm extends BaseQdDataForm {

    @Schema(description = "原型类型编码")
    @NotBlank(message = "原型类型编码不能为空")
    private String code;

    @Schema(description = "原型类型名称")
    @NotBlank(message = "原型类型名称不能为空")
    private String name;

    @Schema(description = "原型类型归属（原型p还是模型m）")
    private String belong;
    
}