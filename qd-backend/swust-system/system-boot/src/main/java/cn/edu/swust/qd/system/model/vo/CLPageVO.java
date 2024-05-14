package cn.edu.swust.qd.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Schema(description = "数据密级分页对象")
@Data
public class CLPageVO {

    @Schema(description = "数据密级ID")
    private Integer id;

    @Schema(description = "数据密级名称")
    private String name;

    @Schema(description = "数据密级编码")
    private String code;

    @Schema(description = "数据密级描述")
    private String description;

    @Schema(description = "人员密级状态")
    private Integer status;

    @Schema(description = "排序")
    private Integer sort;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
