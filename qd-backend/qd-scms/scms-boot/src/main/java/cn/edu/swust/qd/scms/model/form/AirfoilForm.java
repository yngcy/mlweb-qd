package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 翼型信息表
 *
 * @TableName scms_airfoil
 */
@Schema(description = "翼型信息表单")
@Data
public class AirfoilForm extends BaseQdDataForm {

    @Schema(description = "翼型类型ID")
    @NotNull(message = "翼型类型不能为空")
    private Long airfoilTypeId;

    @Schema(description = "翼型名称")
    @NotBlank(message = "翼型名称不能为空")
    private String name;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "翼型坐标")
    private List<AirfoilCoordinateForm> coordinateFormList;

}