package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "风洞类型表单")
@Data
public class WtTypeForm extends BaseQdDataForm {

    @Schema(description = "风洞类型名称")
    @NotBlank(message = "风洞类型名称不能为空")
    private String name;

    @Schema(description = "风洞类型描述")
    private String description;
    
}