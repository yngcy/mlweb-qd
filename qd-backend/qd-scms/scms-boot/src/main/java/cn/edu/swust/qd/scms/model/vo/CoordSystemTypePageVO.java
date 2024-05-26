package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "坐标系系统类别分页视图对象")
@Data
public class CoordSystemTypePageVO extends BaseQdDataVO {

    /**
     * 坐标系统类别名称
     */
    private String name;

    /**
     * 坐标系统类别描述
     */
    private String description;

}