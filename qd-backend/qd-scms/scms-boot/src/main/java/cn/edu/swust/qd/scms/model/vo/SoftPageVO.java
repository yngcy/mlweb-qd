package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "软件分页视图对象")
@Data
public class SoftPageVO extends BaseQdDataVO {

    @Schema(description = "软件编码")
    private String code;

    @Schema(description = "软件名称")
    private String name;

    @Schema(description = "软件版本")
    private String version;

    @Schema(description = "软件供应商")
    private String provider;

    @Schema(description = "软件功能简介")
    private String funcDescription;

}