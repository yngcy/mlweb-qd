package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "注油方式分页查询对象")
@Data
public class InjModePageQuery extends BasePageQuery {

//    @Schema(description = "注油方式名称")
//    private String name;
//
//    @Schema(description = "注油方式描述")
//    private String description;

}