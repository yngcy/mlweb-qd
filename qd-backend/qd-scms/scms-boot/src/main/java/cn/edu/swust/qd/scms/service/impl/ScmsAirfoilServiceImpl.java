package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsAirfoilConverter;
import cn.edu.swust.qd.scms.mapper.ScmsAirfoilMapper;
import cn.edu.swust.qd.scms.model.bo.AirfoilBO;
import cn.edu.swust.qd.scms.model.entity.ScmsAirfoil;
import cn.edu.swust.qd.scms.model.form.AirfoilCoordinateForm;
import cn.edu.swust.qd.scms.model.form.AirfoilForm;
import cn.edu.swust.qd.scms.model.query.AirfoilPageQuery;
import cn.edu.swust.qd.scms.model.vo.AirfoilPageVO;
import cn.edu.swust.qd.scms.service.ScmsAirfoilCoordinateService;
import cn.edu.swust.qd.scms.service.ScmsAirfoilService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_airfoil(翼型信息表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsAirfoilServiceImpl extends ServiceImpl<ScmsAirfoilMapper, ScmsAirfoil>
        implements ScmsAirfoilService {

    private final ScmsAirfoilConverter scmsAirfoilConverter;

    private final ScmsAirfoilCoordinateService scmsAirfoilCoordinateService;


    @Override
    public String getNameById(Long id) {
        ScmsAirfoil entity = this.getById(id);
        return entity.getName();
    }

    @Override
    public Page<AirfoilPageVO> getAirfoilPage(AirfoilPageQuery queryParams) {
        Page<AirfoilBO> airfoilPage = this.baseMapper.getAirfoilPage(
                new Page<AirfoilBO>(queryParams.getPageNum(), queryParams.getPageSize()), queryParams);
        return scmsAirfoilConverter.bo2VO(airfoilPage);
    }


    @Override
    @Transactional
    public boolean saveAirfoil(AirfoilForm airfoilForm) {
        Long airfoilId = airfoilForm.getId();
        ScmsAirfoil oldAirfoil = null;
        if (airfoilId != null) {
            oldAirfoil = this.getById(airfoilId);
            Assert.isTrue(oldAirfoil != null, "翼型信息不存在");
        }

        ScmsAirfoil airfoil = scmsAirfoilConverter.form2Entity(airfoilForm);
        boolean saveAirfoil = this.saveOrUpdate(airfoil);
        List<AirfoilCoordinateForm> coordinateFormList = airfoilForm.getCoordinateFormList();
        boolean saveCoord = CollUtil.isNotEmpty(coordinateFormList) ? scmsAirfoilCoordinateService.saveAirfoilCoordinates(coordinateFormList) : true;
        return saveAirfoil && saveCoord;
    }

    @Override
    public List<Option> getAirfoilOptions() {
        List<ScmsAirfoil> airfoils = this.list(new LambdaQueryWrapper<ScmsAirfoil>()
                .select(ScmsAirfoil::getAirfoilTypeId, ScmsAirfoil::getName));
        return scmsAirfoilConverter.entities2Options(airfoils);
    }

    @Override
    public AirfoilForm getAirfoilForm(Long airfoilId) {
        ScmsAirfoil airfoil = this.getById(airfoilId);

        AirfoilForm result = scmsAirfoilConverter.entity2Form(airfoil);
        // 获取翼型坐标
        List<AirfoilCoordinateForm> coordinateFormList = scmsAirfoilCoordinateService.listAirfoilCoordinatesByAirfoilId(airfoilId);

        result.setCoordinateFormList(coordinateFormList);
        return result;
    }

    @Override
    public boolean deleteAirfoils(String ids) {
        List<Long> airfoilIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long airfoilId : airfoilIds) {
            ScmsAirfoil airfoil = this.getById(airfoilId);
            Assert.isTrue(airfoil != null, "翼型信息不存在");

            // 无翼型坐标数据
            boolean hasAirfoilCoordinate = scmsAirfoilCoordinateService.hasAirfoilCoordinate(airfoilId);
            Assert.isTrue(!hasAirfoilCoordinate, "翼型信息下存在翼型坐标数据，请先删除翼型坐标数据");

            this.removeById(airfoilId);
        }
        return true;
    }

    @Override
    public boolean isTypeReferenced(Long airfoilTypeId) {
        return this.count(new QueryWrapper<ScmsAirfoil>().eq("airfoil_id", airfoilTypeId)) > 0;
    }
}




