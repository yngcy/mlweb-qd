package cn.edu.swust.qd.ums.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Schema(description = "成员分页查询对象")
@Data
public class MemberPageQuery extends BasePageQuery {

    @Schema(description = "昵称")
    private String nickName;
}
