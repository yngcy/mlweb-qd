package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 风洞类型表
 *
 * @TableName scms_wt_type
 */
@Schema(description = "风洞类型分页查询对象")
@Data
public class WtTypePageQuery extends BasePageQuery {

//    /**
//     * 风洞类型名称
//     */
//    @Schema(description = "风洞类型名称")
//    private String name;
//
//    /**
//     * 风洞类型描述
//     */
//    @Schema(description = "风洞类型描述")
//    private String description;

}