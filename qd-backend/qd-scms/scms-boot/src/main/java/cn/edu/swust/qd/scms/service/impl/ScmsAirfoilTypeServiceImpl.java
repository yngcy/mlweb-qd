package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsAirfoilTypeConverter;
import cn.edu.swust.qd.scms.mapper.ScmsAirfoilTypeMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsAirfoilType;
import cn.edu.swust.qd.scms.model.form.AirfoilTypeForm;
import cn.edu.swust.qd.scms.model.query.AirfoilTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.AirfoilTypePageVO;
import cn.edu.swust.qd.scms.service.ScmsAirfoilService;
import cn.edu.swust.qd.scms.service.ScmsAirfoilTypeService;
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
 * @description 针对表【scms_airfoil_type(翼型类型表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsAirfoilTypeServiceImpl extends ServiceImpl<ScmsAirfoilTypeMapper, ScmsAirfoilType>
        implements ScmsAirfoilTypeService {

    private final ScmsAirfoilTypeConverter scmsAirfoilTypeConverter;

    private final ScmsAirfoilService scmsAirfoilService;

    @Override
    public String getNameById(Long id) {
        return this.getById(id).getName();
    }

    @Override
    public Page<AirfoilTypePageVO> getAirfoilTypePage(AirfoilTypePageQuery queryParams) {
        Page<ScmsAirfoilType> airfoilTypePage = this.page(new Page<>(queryParams.getPageNum(), queryParams.getPageSize()));

        Page<AirfoilTypePageVO> page = scmsAirfoilTypeConverter.entity2VO(airfoilTypePage);
        return page;
    }


    @Override
    public List<Option> getAirfoilTypeOptions() {
        List<ScmsAirfoilType> entities = this.list(new LambdaQueryWrapper<ScmsAirfoilType>()
                .select(ScmsAirfoilType::getId, ScmsAirfoilType::getName));
        return scmsAirfoilTypeConverter.entities2Options(entities);
    }

    @Override
    public boolean saveAirfoilType(AirfoilTypeForm airfoilTypeForm) {
        Long airfoilTypeId = airfoilTypeForm.getId();
        ScmsAirfoilType oldAirfoilType = null;
        if (airfoilTypeId != null) {
            oldAirfoilType = this.getById(airfoilTypeId);
            Assert.isTrue(oldAirfoilType != null, "翼型类型不存在");
        }

        ScmsAirfoilType airfoilType = scmsAirfoilTypeConverter.form2Entity(airfoilTypeForm);
        boolean result = this.saveOrUpdate(airfoilType);
        return result;
    }

    @Override
    public AirfoilTypeForm getAirfoilTypeForm(Long airfoilTypeId) {
        ScmsAirfoilType airfoilType = this.getById(airfoilTypeId);
        return scmsAirfoilTypeConverter.entity2Form(airfoilType);
    }

    @Override
    public boolean deleteAirfoilTypes(String ids) {
        List<Long> airfoilTypeIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long airfoilTypeId : airfoilTypeIds) {
            ScmsAirfoilType airfoilType = this.getById(airfoilTypeId);
            Assert.isTrue(airfoilType != null, "翼型类型不存在");

            // 无翼型信息关联
            boolean isReferenced = scmsAirfoilService.isTypeReferenced(airfoilTypeId);
            Assert.isTrue(!isReferenced, "翼型类型被引用，不可删除");

            this.removeById(airfoilType);
        }
        return true;
    }
}




