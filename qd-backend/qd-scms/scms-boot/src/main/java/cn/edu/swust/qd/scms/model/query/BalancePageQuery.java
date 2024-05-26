package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 天平表
 *
 * @TableName scms_balance
 */
@Schema(description = "天平分页查询对象")
@Data
public class BalancePageQuery extends BasePageQuery {

//    @Schema(description = "天平设计单位ID")
//    private Long companyDesignId;
//
//    @Schema(description = "天平机构ID")
//    private Long companyResearchId;
//
//    @Schema(description = "天平类型ID")
//    private Long balanceTypeId;
//
//    @Schema(description = "天平编码")
//    private String code;
//
//    @Schema(description = "天平名称")
//    private String name;
//
//    @Schema(description = "天平设计载荷（kn）")
//    private String load;
//
//    @Schema(description = "天平外形尺寸（mm）")
//    private String shapeSize;
//
//    @Schema(description = "备注")
//    private String remark;

}