package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * FAS细目类别信息表
 *
 * @TableName scms_fas
 */
@Schema(description = "FAS细目类别表单")
@Data
public class FasForm extends BaseQdDataForm {

    @Schema(description = "FAS细目类别名称")
    @NotBlank(message = "FAS细目类别名称不能为空")
    private String name;

    @Schema(description = "FAS细目类别编码")
    @NotBlank(message = "FAS细目类别编码不能为空")
    private String code;

    @Schema(description = "FAS细目类别描述")
    private String description;

}