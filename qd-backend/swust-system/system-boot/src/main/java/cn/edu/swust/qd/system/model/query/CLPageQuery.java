package cn.edu.swust.qd.system.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Schema(description = "数据密级查询对象")
@Data
public class CLPageQuery extends BasePageQuery {

    @Schema(description = "关键字(密级名称/角色编码/描述)")
    private String keywords;

    @Schema(description = "状态")
    private Integer status;
}
