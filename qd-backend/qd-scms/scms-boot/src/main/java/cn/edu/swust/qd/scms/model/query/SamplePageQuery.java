package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "样本分页查询对象")
@Data
public class SamplePageQuery extends BasePageQuery {

//    @Schema(description = "样本名称")
//    private String name;
//
//    @Schema(description = "数据类别")
//    private String dataType;
//
//    @Schema(description = "文件格式(0-Excel; 1-Dat)")
//    private String fileFormat;
//
//    @Schema(description = "单位ID")
//    private Long companyId;
//
//    @Schema(description = "样本描述")
//    private String description;
//
//    @Schema(description = "下载地址")
//    private String downloadPath;
//
//    @Schema(description = "解析文件所在位置")
//    private String locations;
//
//    @Schema(description = "文件解析时的ID")
//    private Long parseId;


}