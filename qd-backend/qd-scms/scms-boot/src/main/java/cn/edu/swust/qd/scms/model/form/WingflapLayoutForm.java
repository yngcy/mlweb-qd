package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "翼舵布局表单")
@Data
public class WingflapLayoutForm extends BaseQdDataForm {

    @Schema(description = "翼舵布局名称")
    @NotBlank(message = "翼舵布局名称不能为空")
    private String name;

    @Schema(description = "翼舵布局类型")
    @NotBlank(message = "翼舵布局类型不能为空")
    private String type;

    @Schema(description = "翼舵数目")
    @NotNull(message = "翼舵数目不能为空")
    private Integer wingflapNum;

    @Schema(description = "固定气动面数")
    private Integer fixedPneNum;

    @Schema(description = "活动气动面数")
    private Integer activePneNum;

    @Schema(description = "俯仰翼舵数目")
    private Integer pitchWingflapNum;

    @Schema(description = "俯仰翼舵编号集")
    private String pitchIdSet;

    @Schema(description = "偏航翼舵数目")
    private Integer yawWingflapNum;

    @Schema(description = "偏航翼舵编号集")
    private String yawWingflapSet;

    @Schema(description = "滚转翼舵数目")
    private Integer rollWingflapNum;

    @Schema(description = "滚转翼舵编号集")
    private String rollWingflapSet;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "翼舵示意图")
    private String wingflapImg;

    @Schema(description = "升降舵公式")
    @NotBlank(message = "升降舵公式不能为空")
    private String deformula;

    @Schema(description = "方向舵公式")
    @NotBlank(message = "方向舵公式不能为空")
    private String drformula;

    @Schema(description = "副翼舵公式")
    @NotBlank(message = "副翼舵公式不能为空")
    private String daformula;

}