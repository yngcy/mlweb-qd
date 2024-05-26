package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "翼型类型分页查询对象")
@Data
public class AirfoilTypePageQuery extends BasePageQuery {

//    @Schema(description = "翼型类型名称")
//    private String name;

}