package cn.edu.swust.qd.common.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础气动数据视图
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Data
public class BaseQdDataVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "自定密级")
    private String security;

    @Schema(description = "创建用户")
    private Long createUser;

    @Schema(description = "更新用户")
    private Long updateUser;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;
}
