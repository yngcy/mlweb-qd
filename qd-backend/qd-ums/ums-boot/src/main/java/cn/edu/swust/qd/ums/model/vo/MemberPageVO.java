package cn.edu.swust.qd.ums.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Schema(description = "成员分页视图对象")
@Data
public class MemberPageVO {

    @Schema(description = "成员ID")
    private Long memberId;

    @Schema(description = "性别")
    private String genderLabel;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "手机号码")
    private String mobile;

    @Schema(description = "头像")
    private String avatarUrl;

    @Schema(description = "OpenID")
    private String openid;

    @Schema(description = "session密钥")
    private String sessionKey;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;
}
