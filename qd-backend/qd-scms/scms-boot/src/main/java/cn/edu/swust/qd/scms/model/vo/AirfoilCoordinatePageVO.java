package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 翼型坐标表
 *
 * @TableName scms_airfoil_coordinate
 */
@Schema(description = "翼型坐标分页视图对象")
@Data
public class AirfoilCoordinatePageVO extends BaseQdDataVO {

    @Schema(description = "翼型表面标识")
    private Integer surfaceIdent;

    @Schema(description = "沿弦向x值")
    private Double alongStringX;

    @Schema(description = "翼型表面y值")
    private Double airfoilFaceY;

    @Schema(description = "翼型")
    private String airfoil;
}