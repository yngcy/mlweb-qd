package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "研究机构分页查询对象")
@Data
public class CompanyPageQuery extends BasePageQuery {

//    @Schema(description = "单位编码")
//    private String code;
//
//    @Schema(description = "单位名称")
//    private String name;
//
//    @Schema(description = "单位地址")
//    private String address;
//
//    @Schema(description = "联系电话")
//    private String telephone;
//
//    @Schema(description = "所属机构ID")
//    private Long parentId;

}