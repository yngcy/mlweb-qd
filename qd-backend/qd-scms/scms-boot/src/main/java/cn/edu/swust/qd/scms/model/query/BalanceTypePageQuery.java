package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "天平类型分页查询对象")
@Data
public class BalanceTypePageQuery extends BasePageQuery {


//    @Schema(description = "天平名称")
//    private String name;
//
//    @Schema(description = "天平描述")
//    private String description;

}