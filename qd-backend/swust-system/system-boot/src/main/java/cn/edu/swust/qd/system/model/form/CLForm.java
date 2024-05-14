package cn.edu.swust.qd.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Schema(description = "数据密级表单对象")
@Data
public class CLForm {
    @Schema(description = "数据密级ID")
    private Long id;

    @Schema(description = "数据密级名称")
    @NotBlank(message = "数据密级名称不能为空")
    private String name;

    @Schema(description = "数据密级编码")
    @NotBlank(message = "数据密级编码不能为空")
    private String code;

    @Schema(description = "数据密级描述")
    private String description;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "数据密级状态(1-正常；0-停用)")
    private Integer status;

}
