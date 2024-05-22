package cn.edu.swust.qd.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Schema(description = "文件对象")
@Data
public class FileInfoVO {

    @Schema(description = "文件名称")
    private String name;

    @Schema(description = "文件URL")
    private String url;
}
