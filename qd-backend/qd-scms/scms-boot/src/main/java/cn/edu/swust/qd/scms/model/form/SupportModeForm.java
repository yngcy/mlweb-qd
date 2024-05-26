package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "模型支撑方式")
@Data
public class SupportModeForm extends BaseQdDataForm {

    @Schema(description = "模型支撑方式名称")
    @NotBlank(message = "模型支撑方式名称不能为空")
    private String name;

    @Schema(description = "模型支撑方式描述")
    private String description;

}