package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "燃油类型表单")
@Data
public class FuelTypeForm extends BaseQdDataForm {

    @Schema(description = "燃油类型名称")
    @NotBlank(message = "燃油类型名称不能为空")
    private String name;

    @Schema(description = "燃油类型描述")
    private String description;

}