package cn.edu.swust.qd.scms.model.vo;

import cn.edu.swust.qd.common.base.BaseQdDataVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Schema(description = "飞行器主信息分页视图对象")
@Data
public class AircraftMainPageVO extends BaseQdDataVO {

    @Schema(description = "气动布局类型")
    private String vehiType;

    @Schema(description = "燃油类型")
    private String fuelType;

    @Schema(description = "飞行器ID")
    private Long vehiId;

    @Schema(description = "飞行器名称")
    private String vehiName;

    @Schema(description = "设计单位")
    private String designDepartment;

    @Schema(description = "设计负责人")
    private String designPerson;

    @Schema(description = "设计日期")
    private Date designDate;

    @Schema(description = "发动机类型")
    private String engineType;

    @Schema(description = "发动机模块数")
    private Long moduleNum;

    @Schema(description = "飞行器发射平台")
    private String launchPlatform;

    @Schema(description = "飞行器总级数")
    private Long stagesLv;

    @Schema(description = "转级马赫数范围")
    private String machTurn;

    @Schema(description = "转级高度范围(km)")
    private String altiTurn;

    @Schema(description = "巡航马赫数范围")
    private String machCruise;

    @Schema(description = "巡航高度范围（km）")
    private String altiCruise;

    @Schema(description = "俯冲马赫数范围")
    private String machDive;

    @Schema(description = "俯冲高度范围（km）")
    private String altiDive;

    @Schema(description = "射程（km）")
    private String range;

}