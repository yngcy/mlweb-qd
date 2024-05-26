package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "点火方式分页查询对象")
@Data
public class FireModePageQuery extends BasePageQuery {

//    /**
//     * 点火方式名称
//     */
//    @Schema(description = "点火方式名称")
//    private String name;
//
//    /**
//     * 点火方式描述
//     */
//    @Schema(description = "点火方式描述")
//    private String description;

}