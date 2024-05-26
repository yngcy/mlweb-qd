package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 点火方式表
 *
 * @TableName scms_fire_mode
 */
@Schema(description = "点火方式表单")
@Data
public class FireModeForm extends BaseQdDataForm {

    @Schema(description = "点火方式名称")
    @NotBlank(message = "点火方式名称不能为空")
    private String name;

    @Schema(description = "点火方式描述")
    private String description;

}