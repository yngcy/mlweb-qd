package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 飞行试验方式表
 *
 * @TableName scms_flight_mode
 */
@Schema(description = "飞行试验方式分页视图对象")
@Data
public class FlightModePageVO extends BaseQdDataVO {

    @Schema(description = "飞行方式名称")
    private String name;

    @Schema(description = "飞行试验方式描述")
    private String description;

}