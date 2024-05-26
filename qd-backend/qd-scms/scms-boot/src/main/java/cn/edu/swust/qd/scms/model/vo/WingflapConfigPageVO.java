package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 翼舵构型表
 *
 * @TableName scms_wingflap_config
 */
@Schema(description = "翼舵构型分页视图对象")
@Data
public class WingflapConfigPageVO extends BaseQdDataVO {

    @Schema(description = "翼舵构型名称")
    private String name;

    @Schema(description = "翼舵面积（㎡）")
    private Double area;

    @Schema(description = "暴露展长（m）")
    private Double exposedLength;

    @Schema(description = "翼根弦长（m）")
    private Double chordLength;

    @Schema(description = "跟弦扭转角")
    private Double twistChordAngle;

    @Schema(description = "前沿后掠角")
    private Double leSweepBackAngle;

    @Schema(description = "后沿后掠角")
    private Double teSweepBackAngle;

    @Schema(description = "上反角")
    private Double dihedralAngle;

}