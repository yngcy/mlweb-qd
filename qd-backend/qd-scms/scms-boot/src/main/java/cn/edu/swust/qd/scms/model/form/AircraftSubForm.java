package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 飞行器子级信息表
 *
 * @TableName scms_aircraft_sub
 */
@Schema(description = "飞行器子级信息表单")
@Data
public class AircraftSubForm extends BaseQdDataForm {

    @Schema(description = "飞行器子级名称")
    @NotBlank(message = "飞行子子级名称不能为空")
    private String name;

    @Schema(description = "飞行器子级描述")
    private String description;

    @Schema(description = "翼型ID")
    private Long airfoilId;

}