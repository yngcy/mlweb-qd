package cn.edu.swust.qd.system.model.vo;

/**
 * 当前登录用户视图对象
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

@Schema(description = "当前登录用户视图对象")
@Data
public class UserInfoVO {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "头像地址")
    private String avatar;

    @Schema(description = "用户角色编码集合")
    private Set<String> roles;

    @Schema(description = "用户权限标识集合")
    private Set<String> perms;
}
