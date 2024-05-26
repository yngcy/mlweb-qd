package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.scms.converter.ScmsAirfoilCoordinateConverter;
import cn.edu.swust.qd.scms.mapper.ScmsAirfoilCoordinateMapper;
import cn.edu.swust.qd.scms.model.bo.AirfoilCoordinateBO;
import cn.edu.swust.qd.scms.model.entity.ScmsAirfoilCoordinate;
import cn.edu.swust.qd.scms.model.form.AirfoilCoordinateForm;
import cn.edu.swust.qd.scms.model.query.AirfoilCoordinatePageQuery;
import cn.edu.swust.qd.scms.model.vo.AirfoilCoordinatePageVO;
import cn.edu.swust.qd.scms.service.ScmsAirfoilCoordinateService;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_airfoil_coordinate(翼型坐标表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsAirfoilCoordinateServiceImpl extends ServiceImpl<ScmsAirfoilCoordinateMapper, ScmsAirfoilCoordinate>
        implements ScmsAirfoilCoordinateService {

    private final ScmsAirfoilCoordinateConverter scmsAirfoilCoordinateConverter;

    @Override
    public Page<AirfoilCoordinatePageVO> getAirfoilCoordinatePage(AirfoilCoordinatePageQuery queryParams) {
        Page<AirfoilCoordinateBO> airfoilCoordinatePage = this.baseMapper.getAirfoilCoordinatePage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()));

        Page<AirfoilCoordinatePageVO> page = scmsAirfoilCoordinateConverter.bo2VO(airfoilCoordinatePage);

        return page;
    }


    @Override
    public boolean saveAirfoilCoordinate(AirfoilCoordinateForm airfoilCoordinateForm) {
        Long airfoilCoordinateId = airfoilCoordinateForm.getId();
        ScmsAirfoilCoordinate oldAirfoilCoordinate = null;
        if (airfoilCoordinateId != null) {
            oldAirfoilCoordinate = this.getById(airfoilCoordinateId);
            Assert.isTrue(oldAirfoilCoordinate != null, "翼型坐标不存在");
        }

        ScmsAirfoilCoordinate airfoilCoordinate = scmsAirfoilCoordinateConverter.form2Entity(airfoilCoordinateForm);
        boolean result = this.save(airfoilCoordinate);
        return result;
    }

    @Override
    public AirfoilCoordinateForm getAirfoilCoordinateForm(Long airfoilCoordinateId) {
        ScmsAirfoilCoordinate airfoilCoordinate = this.getById(airfoilCoordinateId);
        return scmsAirfoilCoordinateConverter.entity2Form(airfoilCoordinate);
    }

    @Override
    public boolean deleteAirfoilCoordinates(String ids) {
        List<Long> acIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long acId : acIds) {
            ScmsAirfoilCoordinate airfoilCoordinate = this.getById(acId);
            Assert.isTrue(airfoilCoordinate != null, "翼型坐标不存在");

            this.removeById(airfoilCoordinate);
        }
        return true;
    }

    @Override
    public boolean hasAirfoilCoordinate(Long airfoilId) {
        return this.count(new LambdaQueryWrapper<ScmsAirfoilCoordinate>().eq(ScmsAirfoilCoordinate::getAirfoilId, airfoilId)) > 0;
    }
}




