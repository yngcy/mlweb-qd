package cn.edu.swust.qd.ums.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * 成员传输对象
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Data
public class MemberRegisterDTO {

    private Integer gender;

    private String nickName;

    private String mobile;

    private LocalDate birthday;

    private String avatarUrl;

    private String openid;

    private String sessionKey;

    private String language;

}
