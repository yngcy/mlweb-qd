package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "弹道表单")
@Data
public class TrajectoryForm extends BaseQdDataForm {

    @Schema(description = "弹道编码")
    @NotBlank(message = "弹道编码不能为空")
    private String code;

    @Schema(description = "弹道名称")
    @NotBlank(message = "弹道名称不能为空")
    private String name;

}