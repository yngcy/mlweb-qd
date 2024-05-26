package cn.edu.swust.qd.scms.model.form;

import cn.edu.swust.qd.common.base.BaseQdDataForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 研究机构表
 *
 * @TableName scms_company
 */
@Schema(description = "研究机构表单")
@Data
public class CompanyForm extends BaseQdDataForm {

    @Schema(description = "单位编码")
    @NotBlank(message = "单位编码不能为空")
    private String code;

    @Schema(description = "单位名称")
    @NotBlank(message = "单位名称不能为空")
    private String name;

    @Schema(description = "单位地址")
    private String address;

    @Schema(description = "联系电话")
    private String telephone;

    @Schema(description = "父机构ID")
    private Long parentId;

}