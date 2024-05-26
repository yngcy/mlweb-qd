package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "飞行器子级信息分页视图对象")
@Data
public class AircraftSubPageVO extends BaseQdDataVO {

    @Schema(description = "飞行器子级名称")
    private String name;

    @Schema(description = "飞行器子级描述")
    private String description;

    @Schema(description = "翼型")
    private String airfoil;
}