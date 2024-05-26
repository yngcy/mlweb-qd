package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 发动机类型
 *
 * @TableName scms_engine_type
 */
@Schema(description = "发动机类型")
@Data
public class EngineTypePageQuery extends BasePageQuery {

//    @Schema(description = "发动机类型名称")
//    private String name;
//
//    @Schema(description = "发动机类型描述")
//    private String description;

}