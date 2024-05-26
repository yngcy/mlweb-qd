package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Schema(description = "天平表单")
@Data
public class BalanceForm extends BaseQdDataForm {

    @Schema(description = "天平设计单位")
    private Long companyDesignId;

    @Schema(description = "天平机构")
    private Long companyResearchId;

    @Schema(description = "天平类型")
    private Long balanceTypeId;

    @Schema(description = "天平编码")
    @NotBlank(message = "天平编码不能为空")
    private String code;

    @Schema(description = "天平名称")
    @NotBlank(message = "天平名称不能为空")
    private String name;

    @Schema(description = "天平设计载荷（kn）")
    private String load;

    @Schema(description = "天平外形尺寸（mm）")
    private String shapeSize;

    @Schema(description = "备注")
    private String remark;

}