package cn.edu.swust.qd.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 用户分页视图对象
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Schema(description = "用户分页对象")
@Data
public class UserPageVO {

    @Schema(description = "用户ID")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "性别")
    private String genderLabel;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "人员密级")
    private String sl;

    @Schema(description = "联系方式")
    private String mobile;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "角色名称，多个以英文逗号分隔")
    private String roleNames;

    @Schema(description = "用户状态(1:启用;0:禁用)")
    private Integer status;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
