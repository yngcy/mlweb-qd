package cn.edu.swust.qd.system.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户分页查询对象
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Schema(description = "用户分页查询对象")
@Data
public class UserPageQuery extends BasePageQuery {

    @Schema(description = "关键字(用户名/昵称/手机号)")
    private String keywords;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "密级ID")
    private Integer slId;
}
