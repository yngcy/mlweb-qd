package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 坐标系类别表
 *
 * @TableName scms_coordinate_type
 */
@Schema(description = "坐标系类别分页视图对象")
@Data
public class CoordinateTypePageVO extends BaseQdDataVO {

    /**
     * 坐标系类别名称
     */
    private String name;

    /**
     * 坐标系类别描述
     */
    private String description;

}