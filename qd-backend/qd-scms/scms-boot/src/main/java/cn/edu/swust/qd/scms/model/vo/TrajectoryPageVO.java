package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "弹道信息分页视图对象")
@Data
public class TrajectoryPageVO extends BaseQdDataVO {

    @Schema(description = "弹道编码")
    private String code;

    @Schema(description = "弹道名称")
    private String name;

}