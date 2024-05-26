package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 气动布局类型表
 *
 * @TableName scms_vehi_type
 */
@Schema(description = "气动布局类型分页查询对象")
@Data
public class VehiTypePageQuery extends BasePageQuery {

//    @Schema(description = "气动布局类型名称")
//    private String name;
//
//    @Schema(description = "气动布局类型描述")
//    private String description;

}