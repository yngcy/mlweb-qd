package cn.edu.swust.qd.ums.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Schema(description = "成员表单对象")
@Data
public class MemberForm {

    @Schema(description = "成员ID")
    private Long id;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "手机号码")
    @Pattern(regexp = "^$|^1(3\\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$", message = "手机号码格式不正确")
    private String mobile;

    @Schema(description = "头像")
    private String avatarUrl;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "OpenID")
    private String openid;

    @Schema(description = "session密钥")
    private String sessionKey;

}
