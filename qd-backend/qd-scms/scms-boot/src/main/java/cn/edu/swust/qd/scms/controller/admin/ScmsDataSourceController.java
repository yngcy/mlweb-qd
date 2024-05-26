package cn.edu.swust.qd.scms.controller.admin;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */

import cn.edu.swust.qd.common.result.PageResult;
import cn.edu.swust.qd.common.result.Result;
import cn.edu.swust.qd.common.web.annotation.PreventDuplicateResubmit;
import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.form.*;
import cn.edu.swust.qd.scms.model.query.*;
import cn.edu.swust.qd.scms.model.vo.*;
import cn.edu.swust.qd.scms.service.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "试验数据来源接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/data_sources")
public class ScmsDataSourceController {
    private final ScmsBalanceService balanceService;

    private final ScmsWindTunnelService windTunnelService;

    private final ScmsCompanyService companyService;
    private final ScmsWtTypeService wtTypeService;
    private final ScmsBalanceTypeService balanceTypeService;
    private final ScmsSupportModeService supportModeService;
    private final ScmsTrajectoryService trajectoryService;
    private final ScmsFlightModeService flightModeService;
    private final ScmsSoftService softService;

    @Operation(summary = "天平分页列表")
    @GetMapping("/balances/page")
    public PageResult<BalancePageVO> getBalancePage(
            @ParameterObject BalancePageQuery queryParams
    ) {
        Page<BalancePageVO> result = balanceService.getBalancePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "天平下拉列表")
    @GetMapping("/balances/options")
    public Result<List<Option>> listBalanceOptions() {
        List<Option> list = balanceService.listBalanceOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增天平")
    @PostMapping("/balances")
    @PreAuthorize("@ss.hasPerm('scms:balance:add')")
    @PreventDuplicateResubmit
    public Result addBalance(@Valid @RequestBody BalanceForm balanceForm) {
        boolean result = balanceService.saveBalance(balanceForm);
        return Result.judge(result);
    }

    @Operation(summary = "天平表单数据")
    @GetMapping("/balances/{balanceId}/form")
    public Result<BalanceForm> getBalanceForm(
            @Parameter(description = "天平ID") @PathVariable Long balanceId
    ) {
        BalanceForm balanceForm = balanceService.getBalanceForm(balanceId);
        return Result.success(balanceForm);
    }

    @Operation(summary = "修改天平")
    @PutMapping("/balances/{balanceId}")
    @PreAuthorize("@ss.hasPerm('scms:balance:edit')")
    public Result updateBalance(@Valid @RequestBody BalanceForm balanceForm) {
        boolean result = balanceService.saveBalance(balanceForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除天平")
    @DeleteMapping("/balances/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:balance:delete')")
    public Result deleteBalances(
            @Parameter(description = "删除天平，多个以英文逗号(,)分隔") @PathVariable String ids
    ) {
        boolean result = balanceService.deleteBalances(ids);
        return Result.judge(result);
    }

    // todo 支持批量修改数据密级

    @Operation(summary = "风洞分页列表")
    @GetMapping("/wind_tunnels/page")
    public PageResult<WindTunnelPageVO> getWindTunnelPage(
            @ParameterObject WindTunnelPageQuery queryParams
    ) {
        Page<WindTunnelPageVO> result = windTunnelService.getWindTunnelPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "风洞下拉列表")
    @GetMapping("/wind_tunnels/options")
    public Result<List<Option>> listWindTunnelOptions() {
        List<Option> list = windTunnelService.listWindTunnelOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增风洞")
    @PostMapping("/wind_tunnels")
    @PreAuthorize("@ss.hasPerm('scms:wind_tunnel:add')")
    @PreventDuplicateResubmit
    public Result addWindTunnel(@Valid @RequestBody WindTunnelForm windTunnelForm) {
        boolean result = windTunnelService.saveWindTunnel(windTunnelForm);
        return Result.judge(result);
    }

    @Operation(summary = "风洞表单数据")
    @GetMapping("/wind_tunnels/{windTunnelId}/form")
    public Result<WindTunnelForm> getWindTunnelForm(
            @Parameter(description = "风洞ID") @PathVariable Long windTunnelId
    ) {
        WindTunnelForm windTunnelForm = windTunnelService.getWindTunnelForm(windTunnelId);
        return Result.success(windTunnelForm);
    }

    @Operation(summary = "修改风洞")
    @PutMapping("/wind_tunnels/{windTunnelId}")
    @PreAuthorize("@ss.hasPerm('scms:wind_tunnel:edit')")
    public Result updateWindTunnel(@Valid @RequestBody WindTunnelForm windTunnelForm) {
        boolean result = windTunnelService.saveWindTunnel(windTunnelForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除风洞")
    @DeleteMapping("/wind_tunnels/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:wind_tunnel:delete')")
    public Result deleteWindTunnels(
            @Parameter(description = "删除风洞，多个以英文逗号(,)分隔") @PathVariable String ids
    ) {
        boolean result = windTunnelService.deleteWindTunnels(ids);
        return Result.judge(result);
    }

    @Operation(summary = "研究机构分页列表")
    @GetMapping("/companies/page")
    public PageResult<CompanyPageVO> getCompanyPage(
            @ParameterObject CompanyPageQuery queryParams
    ) {
        Page<CompanyPageVO> result = companyService.getCompanyPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "研究机构下拉列表")
    @GetMapping("/companies/options")
    public Result<List<Option>> getCompanyOptions() {
        List<Option> list = companyService.getCompanyOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增研究机构")
    @PostMapping("/companies")
    @PreAuthorize("@ss.hasPerm('scms:company:add')")
    @PreventDuplicateResubmit
    public Result addCompany(@Valid @RequestBody CompanyForm companyForm) {
        boolean result = companyService.saveCompany(companyForm);
        return Result.judge(result);
    }

    @Operation(summary = "研究机构表单")
    @GetMapping("/companies/{companyId}/form")
    public Result<CompanyForm> getCompanyForm(
            @Parameter(description = "研究机构ID") @PathVariable Long companyId
    ) {
        CompanyForm companyForm = companyService.getCompanyForm(companyId);
        return Result.success(companyForm);
    }

    @Operation(summary = "修改研究机构")
    @PutMapping("/companies/{companyId}")
    @PreAuthorize("@ss.hasPerm('scms:company:edit')")
    public Result updateCompany(@Valid @RequestBody CompanyForm companyForm) {
        boolean result = companyService.saveCompany(companyForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除研究机构")
    @DeleteMapping("/companies/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:company:delete')")
    public Result deleteCompanies(
            @Parameter(description = "删除研究机构，多个以英文逗号(,)分隔") @PathVariable String ids
    ) {
        boolean result = companyService.deleteCompanies(ids);
        return Result.judge(result);
    }

    @Operation(summary = "风洞类型分页列表")
    @GetMapping("/wt_types/page")
    public PageResult<WtTypePageVO> getWtTypePage(
            @ParameterObject WtTypePageQuery queryParams
    ) {
        Page<WtTypePageVO> result = wtTypeService.getWtTypePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "风洞类型下拉列表")
    @GetMapping("/wt_types/options")
    public Result<List<Option>> getWtTypeOptions() {
        List<Option> list = wtTypeService.getWtTypeOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增风洞类型")
    @PostMapping("/wt_types")
    @PreAuthorize("@ss.hasPerm('scms:wt_types:add')")
    @PreventDuplicateResubmit
    public Result addWtType(@Valid @RequestBody WtTypeForm wtTypeForm) {
        boolean result = wtTypeService.saveWtType(wtTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "风洞类型表单")
    @GetMapping("/wt_types/{wtTypeId}/form")
    public Result<WtTypeForm> getWtTypeForm(
            @Parameter(description = "风洞类型ID") @PathVariable Long wtTypeId
    ) {
        WtTypeForm wtTypeForm = wtTypeService.getWtTypeForm(wtTypeId);
        return Result.success(wtTypeForm);
    }

    @Operation(summary = "修改风洞类型")
    @PutMapping("/wt_types/{wtTypeId}")
    @PreAuthorize("@ss.hasPerm('scms:wt_types:edit')")
    public Result updateWtType(@Valid @RequestBody WtTypeForm wtTypeForm) {
        boolean result = wtTypeService.saveWtType(wtTypeForm);
        return Result.success(result);
    }

    @Operation(summary = "删除风洞类型")
    @DeleteMapping("/wt_types/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:wt_types:delete')")
    public Result deleteWtTypes(
            @Parameter(description = "删除风洞类型，多个以英文逗号(,)分隔") @PathVariable String ids
    ) {
        boolean result = wtTypeService.deleteWtTypes(ids);
        return Result.judge(result);
    }

    @Operation(summary = "天平类型列表")
    @GetMapping("/balance_types/page")
    public PageResult<BalanceTypePageVO> getBalanceTypePage(
            @ParameterObject BalanceTypePageQuery queryParams
    ) {
        Page<BalanceTypePageVO> result = balanceTypeService.getBalanceTypePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "天平类型下拉列表")
    @GetMapping("/balance_types/options")
    public Result<List<Option>> getBalanceTypeOptions() {
        List<Option> list = balanceTypeService.getBalanceTypeOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增天平类型")
    @PostMapping("/balance_types")
    @PreAuthorize("@ss.hasPerm('scms:balance_type:add')")
    @PreventDuplicateResubmit
    public Result addBalanceType(@Valid @RequestBody BalanceTypeForm balanceTypeForm) {
        boolean result = balanceTypeService.saveBalanceType(balanceTypeForm);
        return Result.success(result);
    }

    @Operation(summary = "天平类型表单")
    @GetMapping("/balance_types/{balanceTypeId}/form")
    public Result<BalanceTypeForm> getBalanceTypeForm(
            @Parameter(description = "天平类型ID") @PathVariable Long balanceTypeId
    ) {
        BalanceTypeForm balanceTypeForm = balanceTypeService.getBalanceTypeForm(balanceTypeId);
        return Result.success(balanceTypeForm);
    }

    @Operation(summary = "修改天平类型")
    @PutMapping("/balance_types/{balanceTypeId}")
    @PreAuthorize("@ss.hasPerm('scms:balance_type:edit')")
    public Result updateBalanceType(@Valid @RequestBody BalanceTypeForm balanceTypeForm) {
        boolean result = balanceTypeService.saveBalanceType(balanceTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除天平类型")
    @DeleteMapping("/balance_types/{ids}")
    public Result deleteBalanceTypes(
            @Parameter(description = "删除天平类型，多个以英文逗号(,)分隔") @PathVariable String ids
    ) {
        boolean result = balanceTypeService.deleteBalanceTypes(ids);
        return Result.judge(result);
    }

    @Operation(summary = "模型支撑方式分页列表")
    @GetMapping("/support_modes/page")
    public PageResult<SupportModePageVO> getSupportModePage(
            @ParameterObject SupportModePageQuery queryParams
    ) {
        Page<SupportModePageVO> result = supportModeService.getSupportModePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "模型支撑方式下拉列表")
    @GetMapping("/support_modes/options")
    public Result<List<Option>> getSupportModeOptions() {
        List<Option> list = supportModeService.getSupportModeOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增模型支撑方式")
    @PostMapping("/support_modes")
    @PreAuthorize("@ss.hasPerm('scms:support_mode:add')")
    @PreventDuplicateResubmit
    public Result addSupportMode(@Valid @RequestBody SupportModeForm supportModeForm) {
        boolean result = supportModeService.saveSupportMode(supportModeForm);
        return Result.judge(result);
    }

    @Operation(summary = "模型支撑方式表单")
    @GetMapping("/support_modes/{supportModeId}/form")
    public Result<SupportModeForm> getSupportModeForm(
            @Parameter(description = "模型支撑方式ID") @PathVariable Long supportModeId
    ) {
        SupportModeForm supportModeForm = supportModeService.getSupportModeForm(supportModeId);
        return Result.success(supportModeForm);
    }

    @Operation(summary = "修改模型支撑方式")
    @PutMapping("/support_modes/{supportModeId}")
    @PreAuthorize("@ss.hasPerm('scms:support_mode:edit')")
    public Result updateSupportMode(@Valid @RequestBody SupportModeForm supportModeForm) {
        boolean result = supportModeService.saveSupportMode(supportModeForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除模型支撑方式")
    @DeleteMapping("/support_modes/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:support_mode:delete')")
    public Result deleteSupportModes(
            @Parameter(description = "删除模型支撑方式，多个以英文逗号(,)分隔") @PathVariable String ids
    ) {
        boolean result = supportModeService.deleteSupportModes(ids);
        return Result.judge(result);
    }

    @Operation(summary = "弹道分页列表")
    @GetMapping("/trajectories/page")
    public PageResult<TrajectoryPageVO> getTrajectoryPage(
            @ParameterObject TrajectoryPageQuery queryParams
    ) {
        Page<TrajectoryPageVO> result = trajectoryService.getTrajectoryPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "弹道下拉列表")
    @GetMapping("/trajectories/options")
    public Result<List<Option>> getTrajectoryOptions() {
        List<Option> list = trajectoryService.getTrajectoryOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增弹道")
    @PostMapping("/trajectories")
    @PreAuthorize("@ss.hasPerm('scms:trajectory:add')")
    @PreventDuplicateResubmit
    public Result addTrajectory(@Valid @RequestBody TrajectoryForm trajectoryForm) {
        boolean result = trajectoryService.saveTrajectory(trajectoryForm);
        return Result.judge(result);
    }

    @Operation(summary = "弹道表单")
    @GetMapping("/trajectories/{trajectoryId}/form")
    public Result<TrajectoryForm> getTrajectoryForm(
            @Parameter(description = "弹道ID") @PathVariable Long trajectoryId
    ) {
        TrajectoryForm trajectoryForm = trajectoryService.getTrajectoryForm(trajectoryId);
        return Result.success(trajectoryForm);
    }

    @Operation(summary = "修改弹道")
    @PutMapping("/trajectories/{trajectoryId}")
    @PreAuthorize("@ss.hasPerm('scms:trajectory:edit')")
    public Result updateTrajectory(@Valid @RequestBody TrajectoryForm trajectoryForm) {
        boolean result = trajectoryService.saveTrajectory(trajectoryForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除弹道")
    @DeleteMapping("/trajectories/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:trajectory:delete')")
    public Result deleteTrajectories(
            @Parameter(description = "删除弹道，多个以英文逗号(,)分隔") @PathVariable String ids
    ) {
        boolean result = trajectoryService.deleteTrajectories(ids);
        return Result.judge(result);
    }


    @Operation(summary = "飞行试验方式分页表单")
    @GetMapping("/flight_modes/page")
    public PageResult<FlightModePageVO> getFlightModePage(
            @ParameterObject FlightModePageQuery queryParams
    ) {
        Page<FlightModePageVO> result = flightModeService.getFlightModePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "飞行试验方式下拉列表")
    @GetMapping("/flight_modes/options")
    public Result<List<Option>> getFlightModeOptions() {
        List<Option> list = flightModeService.getFlightModeOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增飞行试验方式")
    @PostMapping("/flight_modes")
    @PreAuthorize("@ss.hasPerm('scms:flight_mode:add')")
    @PreventDuplicateResubmit
    public Result addFlightMode(@Valid @RequestBody FlightModeForm flightModeForm) {
        boolean result = flightModeService.saveFlightMode(flightModeForm);
        return Result.judge(result);
    }

    @Operation(summary = "飞行试验方式表单")
    @GetMapping("/flight_modes/{flightModeId}/form")
    public Result<FlightModeForm> getFlightModeForm(
            @Parameter(description = "飞行试验方式ID") @PathVariable Long flightModeId
    ) {
        FlightModeForm flightModeForm = flightModeService.getFlightModeForm(flightModeId);
        return Result.success(flightModeForm);
    }

    @Operation(summary = "修改飞行试验方式")
    @PutMapping("/flight_modes/{flightModeId}")
    @PreAuthorize("@ss.hasPerm('scms:flight_mode:edit')")
    public Result updateFlightMode(@Valid @RequestBody FlightModeForm flightModeForm) {
        boolean result = flightModeService.saveFlightMode(flightModeForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除飞行试验方式")
    @DeleteMapping("/flight_modes/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:flight_mode:delete')")
    public Result deleteFlightModes(
            @Parameter(description = "删除飞行试验方式，多个以英文逗号(,)分隔") @PathVariable String ids
    ) {
        boolean result = flightModeService.deleteFlightModes(ids);
        return Result.judge(result);
    }

    @Operation(summary = "软件分页列表")
    @GetMapping("/softs/page")
    public PageResult<SoftPageVO> getSoftPage(
            @ParameterObject SoftPageQuery queryParams
    ) {
        Page<SoftPageVO> result = softService.getSoftPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "软件下拉列表")
    @GetMapping("/softs/options")
    public Result<List<Option>> getSoftOptions() {
        List<Option> list = softService.getSoftOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增软件")
    @PostMapping("/softs")
    @PreAuthorize("@ss.hasPerm('scms:soft:add')")
    @PreventDuplicateResubmit
    public Result addSoft(@Valid @RequestBody SoftForm softForm) {
        boolean result = softService.saveSoft(softForm);
        return Result.judge(result);
    }

    @Operation(summary = "软件表单")
    @GetMapping("/softs/{softId}/form")
    public Result<SoftForm> getSoftForm(
            @Parameter(description = "软件ID") @PathVariable Long softId
    ) {
        SoftForm softForm = softService.getSoftForm(softId);
        return Result.success(softForm);
    }

    @Operation(summary = "修改软件")
    @PutMapping("/softs/{softId}")
    @PreAuthorize("@ss.hasPerm('scms:soft:edit')")
    public Result updateSoft(@Valid @RequestBody SoftForm softForm) {
        boolean result = softService.saveSoft(softForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除软件")
    @DeleteMapping("/softs/{ids}")
    @PreAuthorize("@ss.hasPerm('scms:soft:delete')")
    public Result deleteSofts(
            @Parameter(description = "删除软件，多个以英文逗号(,)分隔") @PathVariable String ids
    ) {
        boolean result = softService.deleteSofts(ids);
        return Result.judge(result);
    }

}
