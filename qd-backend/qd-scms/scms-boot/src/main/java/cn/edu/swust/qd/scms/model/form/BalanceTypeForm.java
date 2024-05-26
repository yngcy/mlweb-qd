package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "天平类型表单")
@Data
public class BalanceTypeForm extends BaseQdDataForm {

    @Schema(description = "天平名称")
    @NotBlank(message = "天平名称不能为空")
    private String name;

    @Schema(description = "天平描述")
    private String description;

}