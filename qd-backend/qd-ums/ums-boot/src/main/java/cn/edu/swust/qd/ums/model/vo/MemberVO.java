package cn.edu.swust.qd.ums.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Schema(description = "成员视图层对象")
@Data
public class MemberVO {
    @Schema(description = "成员ID")
    private Long id;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "手机号码")
    private String mobile;

    @Schema(description = "头像")
    private String avatarUrl;

}
