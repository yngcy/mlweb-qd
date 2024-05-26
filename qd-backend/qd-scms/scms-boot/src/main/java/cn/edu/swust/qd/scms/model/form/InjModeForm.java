package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "注油方式表单")
@Data
public class InjModeForm extends BaseQdDataForm {

    @Schema(description = "注油方式名称")
    @NotBlank(message = "注油方式名称不能为空")
    private String name;

    @Schema(description = "注油方式描述")
    private String description;

}