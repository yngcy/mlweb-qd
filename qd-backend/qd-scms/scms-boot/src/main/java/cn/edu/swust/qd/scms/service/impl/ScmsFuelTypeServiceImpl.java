package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsFuelTypeConverter;
import cn.edu.swust.qd.scms.mapper.ScmsFuelTypeMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsFuelType;
import cn.edu.swust.qd.scms.model.form.FuelTypeForm;
import cn.edu.swust.qd.scms.model.query.FuelTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.FuelTypePageVO;
import cn.edu.swust.qd.scms.service.ScmsFuelTypeService;
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
 * @description 针对表【scms_fuel_type(燃油类型表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsFuelTypeServiceImpl extends ServiceImpl<ScmsFuelTypeMapper, ScmsFuelType>
        implements ScmsFuelTypeService {

    private final ScmsFuelTypeConverter scmsFuelTypeConverter;

    @Override
    public String getNameById(Long id) {
        return this.getById(id).getName();
    }

    @Override
    public Page<FuelTypePageVO> getFuelTypePage(FuelTypePageQuery queryParams) {
        Page<ScmsFuelType> entity = this.page(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        return scmsFuelTypeConverter.entity2VO(entity);
    }


    @Override
    public List<Option> getFuelTypeOptions() {
        List<ScmsFuelType> entities = this.list(new LambdaQueryWrapper<ScmsFuelType>()
                .select(ScmsFuelType::getId, ScmsFuelType::getName));
        return scmsFuelTypeConverter.entities2Options(entities);
    }

    @Override
    public boolean saveFuelType(FuelTypeForm fuelTypeForm) {
        Long fuelTypeId = fuelTypeForm.getId();
        ScmsFuelType oldFuelType = null;
        if (fuelTypeId != null) {
            oldFuelType = this.getById(fuelTypeId);
            Assert.isTrue(oldFuelType != null, "燃油类型不存在");
        }

        ScmsFuelType fuelType = scmsFuelTypeConverter.form2Entity(fuelTypeForm);
        boolean result = this.saveOrUpdate(fuelType);

        return result;
    }

    @Override
    public FuelTypeForm getFuelTypeForm(Long fuelTypeId) {
        ScmsFuelType entity = this.getById(fuelTypeId);
        return scmsFuelTypeConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteFuelTypes(String ids) {
        List<Long> fuelTypeIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long fuelTypeId : fuelTypeIds) {
            ScmsFuelType fuelType = this.getById(fuelTypeId);
            Assert.isTrue(fuelType != null, "燃油类型不存在");
        }
        return false;
    }
}




