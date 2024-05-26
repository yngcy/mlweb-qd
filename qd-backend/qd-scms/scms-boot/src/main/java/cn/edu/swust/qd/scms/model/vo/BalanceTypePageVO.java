package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "天平类型分页视图对象")
@Data
public class BalanceTypePageVO extends BaseQdDataVO {


    @Schema(description = "天平名称")
    private String name;

    @Schema(description = "天平描述")
    private String description;

}