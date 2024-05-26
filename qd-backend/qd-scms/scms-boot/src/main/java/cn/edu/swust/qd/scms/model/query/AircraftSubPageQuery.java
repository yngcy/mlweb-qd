package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "飞行器子级信息分页查询对象")
@Data
public class AircraftSubPageQuery extends BasePageQuery {

//    @Schema(description = "飞行器子级名称")
//    private String name;
//
//    @Schema(description = "飞行器子级描述")
//    private String description;
//
//    @Schema(description = "翼型ID")
//    private Long airfoilId;
}