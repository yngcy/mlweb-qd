package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import cn.edu.swust.qd.scms.model.entity.ScmsAirfoilCoordinate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "翼型信息分页视图对象")
@Data
public class AirfoilPageVO extends BaseQdDataVO {

    @Schema(description = "翼型类型")
    private String airfoilType;

    @Schema(description = "翼型名称")
    private String name;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "翼型坐标")
    private List<ScmsAirfoilCoordinate> coordinates;

}