package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "研究机构分页视图对象")
@Data
public class CompanyPageVO extends BaseQdDataVO {

    @Schema(description = "单位编码")
    private String code;

    @Schema(description = "单位名称")
    private String name;

    @Schema(description = "单位地址")
    private String address;

    @Schema(description = "联系电话")
    private String telephone;

    @Schema(description = "所属机构")
    private String parent;

}