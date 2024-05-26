package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Schema(description = "坐标系统类别表单")
@Data
public class CoordSystemTypeForm extends BaseQdDataForm {

    @Schema(description = "坐标系统类别名称")
    @NotBlank(message = "坐标系类别名称不能为空")
    private String name;

    @Schema(description = "坐标系统类别描述")
    private String description;

}