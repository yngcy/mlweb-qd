package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "飞行器次级信息分页查询对象")
@Data
public class AircraftSecPageQuery extends BasePageQuery {

    @Schema(description = "飞行器主信息ID")
    private Long aircraftMainId;
//
//    @Schema(description = "飞行器子级ID")
//    private Long aircraftSubId;
//
//    @Schema(description = "飞行器子级名称")
//    private String aircraftSubName;
//
//    @Schema(description = "飞行器子级长度（m）")
//    private Double length;
//
//    @Schema(description = "飞行器子级控制面数")
//    private Integer numFace;
//
//    @Schema(description = "飞行器最大宽度（m）")
//    private Double maxWidth;
//
//    @Schema(description = "飞行器子级最大横截面积（m²）")
//    private Double maxCrossArea;
//
//    @Schema(description = "飞行器子级底部面积（m²）")
//    private Double maxBottomArea;

}