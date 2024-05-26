package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 天平表
 *
 * @TableName scms_balance
 */
@Schema(description = "天平分页视图对象")
@Data
public class BalancePageVO extends BaseQdDataVO {

    @Schema(description = "天平设计单位")
    private String companyDesign;

    @Schema(description = "天平机构")
    private String companyResearch;

    @Schema(description = "天平类型")
    private String balanceType;

    @Schema(description = "天平编码")
    private String code;

    @Schema(description = "天平名称")
    private String name;

    @Schema(description = "天平设计载荷（kn）")
    private String load;

    @Schema(description = "天平外形尺寸（mm）")
    private String shapeSize;

    @Schema(description = "备注")
    private String remark;

}