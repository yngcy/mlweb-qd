package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "翼型信息分页查询对象")
@Data
public class AirfoilPageQuery extends BasePageQuery {

//    @Schema(description = "翼型类型ID")
//    private Long airfoilTypeId;
//
//    @Schema(description = "翼型名称")
//    private String name;
//
//    @Schema(description = "备注")
//    private String remark;

}