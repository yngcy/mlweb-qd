package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "翼型截面表单")
@Data
public class WingSectionForm extends BaseQdDataForm {

    @Schema(description = "翼型ID")
    @NotNull(message = "翼型ID不能为空")
    private Long airfoilId;

    @Schema(description = "翼型截面编号")
    @NotNull(message = "翼型截面编号不能为空")
    private Long sectionNumber;

    @Schema(description = "翼型截面位置")
    @NotBlank(message = "翼型截面位置不能为空")
    private String sectionLocation;

    @Schema(description = "翼型比例")
    @NotNull(message = "翼型比例不能为空")
    private Double wingProportion;

    @Schema(description = "翼舵构型ID")
    @NotNull(message = "翼舵构型ID不能为空")
    private Long wingflapConfigId;

    @Schema(description = "描述")
    private String description;

}