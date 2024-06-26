package cn.edu.swust.qd.system.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Schema(description = "角色分页查询对象")
@Data
public class RolePageQuery extends BasePageQuery {

    @Schema(description = "关键字(角色名称/角色编码)")
    private String keywords;
}
