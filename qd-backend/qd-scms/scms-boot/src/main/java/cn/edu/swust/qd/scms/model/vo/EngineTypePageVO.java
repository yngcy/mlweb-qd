package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 发动机类型
 *
 * @TableName scms_engine_type
 */
@Schema(description = "发动机类型")
@Data
public class EngineTypePageVO extends BaseQdDataVO {

    @Schema(description = "发动机类型名称")
    private String name;

    @Schema(description = "发动机类型描述")
    private String description;

}