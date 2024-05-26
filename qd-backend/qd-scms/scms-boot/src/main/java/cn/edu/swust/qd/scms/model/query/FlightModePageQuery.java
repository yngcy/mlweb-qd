package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 飞行试验方式表
 *
 * @TableName scms_flight_mode
 */
@Schema(description = "飞行试验方式分页查询对象")
@Data
public class FlightModePageQuery extends BasePageQuery {

//    @Schema(description = "飞行方式名称")
//    private String name;
//
//    @Schema(description = "飞行试验方式描述")
//    private String description;

}