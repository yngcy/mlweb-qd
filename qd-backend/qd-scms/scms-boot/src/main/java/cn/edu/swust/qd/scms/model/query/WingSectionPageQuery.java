package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "翼型截面信息分页查询对象")
@Data
public class WingSectionPageQuery extends BasePageQuery {

    @Schema(description = "翼型ID")
    private Long airfoilId;

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