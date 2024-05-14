package cn.edu.swust.qd.ums.model.entity;

import cn.edu.swust.qd.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName ums_member
 */
@TableName(value = "ums_member")
@Data
public class UmsMember extends BaseEntity implements Serializable {
    /**
     * 成员ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * OpenID
     */
    private String openid;

    /**
     * session密钥
     */
    private String sessionKey;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否删除
     */
    @TableLogic(delval = "1", value = "0")
    private Integer deleted;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}