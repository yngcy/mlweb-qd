package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "翼舵构型表单")
@Data
public class WingflapConfigForm extends BaseQdDataForm {

    @Schema(description = "翼舵构型名称")
    @NotBlank(message = "翼舵构型名称不能为空")
    private String name;

    @Schema(description = "翼舵面积（㎡）")
    @NotBlank(message = "翼舵面积不能为空")
    private Double area;

    @Schema(description = "暴露展长（m）")
    @NotBlank(message = "暴露展长不能为空")
    private Double exposedLength;

    @Schema(description = "翼根弦长（m）")
    @NotBlank(message = "翼根弦长不能为空")
    private Double chordLength;

    @Schema(description = "跟弦扭转角")
    @NotBlank(message = "跟弦扭转角不能为空")
    private Double twistChordAngle;

    @Schema(description = "前沿后掠角")
    @NotBlank(message = "前沿后掠角不能为空")
    private Double leSweepBackAngle;

    @Schema(description = "后沿后掠角")
    @NotBlank(message = "后沿后掠角不能为空")
    private Double teSweepBackAngle;

    @Schema(description = "上反角")
    @NotBlank(message = "上反角不能为空")
    private Double dihedralAngle;

}