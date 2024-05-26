package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "模型支撑方式分页视图对象")
@Data
public class SupportModePageVO extends BaseQdDataVO {

    @Schema(description = "模型支撑方式名称")
    private String name;

    @Schema(description = "模型支撑方式描述")
    private String description;

}