package cn.edu.swust.qd.ums.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 成员认证信息
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberAuthDTO {

    /**
     * 成员ID
     */
    private Long id;

    /**
     * 成员名(openId、mobile)
     */
    private String username;

    /**
     * 状态(1:正常; 2:禁用)
     */
    private Integer status;
}
