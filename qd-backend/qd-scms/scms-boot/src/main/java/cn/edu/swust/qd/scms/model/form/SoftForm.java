package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "软件表单")
@Data
public class SoftForm extends BaseQdDataForm {

    @Schema(description = "软件编码")
    @NotBlank(message = "软件编码不能为空")
    private String code;

    @Schema(description = "软件名称")
    @NotBlank(message = "软件名称不能为空")
    private String name;

    @Schema(description = "软件版本")
    @NotBlank(message = "软件版本不能为空")
    private String version;

    @Schema(description = "软件供应商")
    private String provider;

    @Schema(description = "软件功能简介")
    private String funcDescription;

}