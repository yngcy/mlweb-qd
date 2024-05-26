package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "弹道信息分页查询对象")
@Data
public class TrajectoryPageQuery extends BasePageQuery {

//    @Schema(description = "弹道编码")
//    private String code;
//
//    @Schema(description = "弹道名称")
//    private String name;

}