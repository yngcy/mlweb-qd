package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "翼型类型分页视图对象")
@Data
public class AirfoilTypePageVO extends BaseQdDataVO {

    @Schema(description = "翼型类型名称")
    private String name;

}