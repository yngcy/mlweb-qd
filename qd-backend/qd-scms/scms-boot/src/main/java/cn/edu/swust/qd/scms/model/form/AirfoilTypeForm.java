package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 翼型类型表
 *
 * @TableName scms_airfoil_type
 */
@Schema(description = "翼型类型表单")
@Data
public class AirfoilTypeForm extends BaseQdDataForm {

    @Schema(description = "翼型类型名称")
    @NotBlank(message = "翼型类型不能为空")
    private String name;

}