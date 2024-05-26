package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "点火方式分页视图对象")
@Data
public class FireModePageVO extends BaseQdDataVO {

    /**
     * 点火方式名称
     */
    @Schema(description = "点火方式名称")
    private String name;

    /**
     * 点火方式描述
     */
    @Schema(description = "点火方式描述")
    private String description;

}