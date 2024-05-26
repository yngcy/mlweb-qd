package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "翼型截面信息分页视图对象")
@Data
public class WingSectionPageVO extends BaseQdDataVO {

    @Schema(description = "翼型")
    private String airfoil;

    @Schema(description = "翼型截面编号")
    private Long sectionNumber;

    @Schema(description = "翼型截面位置")
    private String sectionLocation;

    @Schema(description = "翼型比例")
    private Double wingProportion;

    @Schema(description = "翼舵构型")
    private String wingflapConfig;

    @Schema(description = "描述")
    private String description;

}