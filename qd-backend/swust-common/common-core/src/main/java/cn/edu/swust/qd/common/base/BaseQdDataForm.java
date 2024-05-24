package cn.edu.swust.qd.common.base;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 基础气动数据表单
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Data
public class BaseQdDataForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "自定密级")
    @NotBlank(message = "自定密级不能为空")
    private String security;

}
