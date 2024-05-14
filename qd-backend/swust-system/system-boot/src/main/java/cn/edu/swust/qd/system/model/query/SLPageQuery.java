package cn.edu.swust.qd.system.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Schema(description = "人员密级查询对象")
@Data
public class SLPageQuery extends BasePageQuery {

    @Schema(description = "关键字(密级名称/密级编码/描述)")
    private String keywords;
}
