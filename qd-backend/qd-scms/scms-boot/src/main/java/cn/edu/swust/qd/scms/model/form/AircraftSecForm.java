package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 飞行器次级信息
 *
 * @TableName scms_aircraft_sec
 */
@Schema(description = "飞行器次级信息表单")
@Data
public class AircraftSecForm extends BaseQdDataForm {

    @Schema(description = "飞行器主信息ID")
    @NotBlank(message = "飞行器主信息ID不能为空")
    private Long aircraftMainId;

    @Schema(description = "飞行器子级信息ID")
    @NotBlank(message = "飞行器子级信息ID不能为空")
    private Long aircraftSubId;

    @Schema(description = "飞行器子级长度（m）")
    private Double length;

    @Schema(description = "飞行器子级控制面数")
    private Integer numFace;

    @Schema(description = "飞行器最大宽度（m）")
    private Double maxWidth;

    @Schema(description = "飞行器子级最大横截面积（m²）")
    private Double maxCrossArea;

    @Schema(description = "飞行器子级底部面积（m²）")
    private Double maxBottomArea;

}