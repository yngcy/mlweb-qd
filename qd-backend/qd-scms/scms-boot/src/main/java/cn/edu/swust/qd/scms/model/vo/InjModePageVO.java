package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "注油方式分页视图对象")
@Data
public class InjModePageVO extends BaseQdDataVO {

    @Schema(description = "注油方式名称")
    private String name;

    @Schema(description = "注油方式描述")
    private String description;

}