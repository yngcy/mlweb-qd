package cn.edu.swust.qd.scms.model.query;

import cn.edu.swust.qd.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "飞行器主信息分页查询对象")
@Data
public class AircraftMainPageQuery extends BasePageQuery {

//    @Schema(description = "气动布局类型ID")
//    private Long vehiTypeId;
//
//    @Schema(description = "燃油类型")
//    private Long fuelTypeId;
//
//    @Schema(description = "飞行器ID")
//    private Long vehiId;
//
//    @Schema(description = "飞行器名称")
//    private String vehiName;
//
//    @Schema(description = "设计单位")
//    private String designDepartment;
//
//    @Schema(description = "设计负责人")
//    private String designPerson;
//
//    @Schema(description = "设计日期")
//    private Date designDate;
//
//    @Schema(description = "发动机类型ID")
//    private Long engineTypeId;
//
//    @Schema(description = "发动机模块数")
//    private Long moduleNum;
//
//    @Schema(description = "飞行器发射平台")
//    private String launchPlatform;
//
//    @Schema(description = "飞行器总级数")
//    private Long stagesLv;
//
//    @Schema(description = "转级马赫数范围")
//    private String machTurn;
//
//    @Schema(description = "转级高度范围(km)")
//    private String altiTurn;
//
//    @Schema(description = "巡航马赫数范围")
//    private String machCruise;
//
//    @Schema(description = "巡航高度范围（km）")
//    private String altiCruise;
//
//    @Schema(description = "俯冲马赫数范围")
//    private String machDive;
//
//    @Schema(description = "俯冲高度范围（km）")
//    private String altiDive;
//
//    @Schema(description = "射程（km）")
//    private String range;

//    @Schema(description = "创建用户ID")
//    private Long createUser;

}