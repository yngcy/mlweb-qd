package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "风洞表单")
@Data
public class WindTunnelForm extends BaseQdDataForm {

    @Schema(description = "风洞编码")
    @NotBlank(message = "风洞编码不能为空")
    private String code;

    @Schema(description = "风洞名称")
    @NotBlank(message = "风洞名称不能为空")
    private String name;

    @Schema(description = "风洞类型")
    private Long wtTypeId;

    @Schema(description = "所属单位")
    private Long companyId;

    @Schema(description = "风洞有效试验时间范围")
    private String rangeTestTime;

    @Schema(description = "风洞试验段长度（m）")
    private String lenTetssec;

    @Schema(description = "风洞试验段横截面尺寸（m）")
    private String sizeTestesc;

    @Schema(description = "风洞喷管出口尺寸（m）")
    private String sizeNozexit;

    @Schema(description = "风洞速度范围（m/s）")
    private String rangeV;

    @Schema(description = "风洞雷诺数范围")
    private String rangeRe;

    @Schema(description = "风洞努森数范围")
    private String rangeNusselt;

    @Schema(description = "风洞总压范围（MPa）")
    private String rangePt;

    @Schema(description = "风洞总温范围（℃）")
    private String rangeTt;

    @Schema(description = "模拟高度范围（m）")
    private String rangeAlt;

}