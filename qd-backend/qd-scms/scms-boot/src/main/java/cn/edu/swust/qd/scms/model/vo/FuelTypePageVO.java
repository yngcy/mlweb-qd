package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "燃油类型分页视图对象")
@Data
public class FuelTypePageVO extends BaseQdDataVO {

    @Schema(description = "燃油类型名称")
    private String name;

    @Schema(description = "燃油类型描述")
    private String description;

}