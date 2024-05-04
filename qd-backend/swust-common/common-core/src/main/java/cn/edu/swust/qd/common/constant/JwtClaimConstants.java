package cn.edu.swust.qd.common.constant;

/**
 * JWT 声明常量
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
public interface JwtClaimConstants {

    /**
     * 用户ID
     */
    String USER_ID = "userId";

    /**
     * 用户名
     */
    String USERNAME = "username";

    /**
     * 人员密级ID
     */
    String SL_ID = "slId";

    /**
     * 数据权限
     */
    String DATA_SCOPE = "dataScope";

    /**
     * 权限（角色role）集合
     */
    String AUTHORITIES = "authorities";

    /**
     * 成员ID
     */
    String MEMBER_ID = "memberId";

}
