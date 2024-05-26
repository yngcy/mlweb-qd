package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 坐标系类别表
 *
 * @TableName scms_coordinate_type
 */
@Schema(description = "坐标系类别分页查询对象")
@Data
public class CoordinateTypePageQuery extends BasePageQuery {

//    /**
//     * 坐标系类别名称
//     */
//    private String name;
//
//    /**
//     * 坐标系类别描述
//     */
//    private String description;

}