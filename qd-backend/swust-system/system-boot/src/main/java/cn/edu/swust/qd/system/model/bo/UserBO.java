package cn.edu.swust.qd.system.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Data
public class UserBO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别(1:男;2:女)
     */
    private Integer gender;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 人员密级
     */
    private String sl;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 状态(1:正常;0:禁用)
     */
    private Integer status;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 角色名称，多个以英文逗号分隔
     */
    private String roleNames;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
