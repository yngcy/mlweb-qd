package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "翼型坐标表单")
@Data
public class AirfoilCoordinateForm extends BaseQdDataForm {

    @Schema(description = "翼型表面标识")
    @NotBlank(message = "翼型表面标识不能为空")
    private Integer surfaceIdent;

    @Schema(description = "沿弦向x值")
    @NotBlank(message = "沿弦向x值不能为空")
    private Double alongStringX;

    @Schema(description = "翼型表面y值")
    @NotBlank(message = "翼型表面y值不能为空")
    private Double airfoilFaceY;

    @Schema(description = "翼型ID")
    @NotBlank(message = "翼型ID不能为空")
    private Long airfoilId;

}