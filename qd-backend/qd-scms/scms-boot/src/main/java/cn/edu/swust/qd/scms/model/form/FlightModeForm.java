package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 飞行试验方式表
 *
 * @TableName scms_flight_mode
 */
@Schema(description = "飞行实验方式表单")
@Data
public class FlightModeForm extends BaseQdDataForm {

    @Schema(description = "飞行试验方式名称")
    @NotBlank(message = "飞行试验方式名称不能为空")
    private String name;

    @Schema(description = "飞行试验方式描述")
    private String description;

}