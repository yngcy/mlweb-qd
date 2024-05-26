package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * FAS细目类别信息表
 *
 * @TableName scms_fas
 */
@Schema(description = "FAS细目类别分页查询对象")
@Data
public class FasPageQuery extends BasePageQuery {

//    @Schema(description = "FAS细目类别")
//    private String name;
//
//    @Schema(description = "FAS细目类别编码")
//    private String code;
//
//    @Schema(description = "FAS细目类别描述")
//    private String description;

}