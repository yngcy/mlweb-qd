package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "燃油类型分页查询对象")
@Data
public class FuelTypePageQuery extends BasePageQuery {

//    @Schema(description = "燃油类型名称")
//    private String name;
//
//    @Schema(description = "燃油类型描述")
//    private String description;

}