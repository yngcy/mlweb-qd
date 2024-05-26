package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsVehiTypeConverter;
import cn.edu.swust.qd.scms.mapper.ScmsVehiTypeMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsVehiType;
import cn.edu.swust.qd.scms.model.form.VehiTypeForm;
import cn.edu.swust.qd.scms.model.query.VehiTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.VehiTypePageVO;
import cn.edu.swust.qd.scms.service.ScmsAircraftMainService;
import cn.edu.swust.qd.scms.service.ScmsVehiTypeService;
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
 * @description 针对表【scms_vehi_type(气动布局类型表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsVehiTypeServiceImpl extends ServiceImpl<ScmsVehiTypeMapper, ScmsVehiType>
        implements ScmsVehiTypeService {

    private final ScmsVehiTypeConverter scmsVehiTypeConverter;

    private final ScmsAircraftMainService scmsAircraftMainService;

    @Override
    public String getNameById(Long id) {
        return this.getById(id).getName();
    }

    @Override
    public Page<VehiTypePageVO> getVehiTypePage(VehiTypePageQuery queryParams) {
        Page<ScmsVehiType> entity = this.page(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        return scmsVehiTypeConverter.entity2VO(entity);
    }


    @Override
    public List<Option> getVehiTypeOptions() {
        List<ScmsVehiType> entities = this.list(new LambdaQueryWrapper<ScmsVehiType>()
                .select(ScmsVehiType::getId, ScmsVehiType::getName));
        return scmsVehiTypeConverter.entities2Options(entities);
    }

    @Override
    public boolean saveVehiType(VehiTypeForm vehiTypeForm) {
        Long vehiTypeId = vehiTypeForm.getId();
        ScmsVehiType oldVehiType = null;
        if (vehiTypeId != null) {
            oldVehiType = this.getById(vehiTypeId);
            Assert.isTrue(oldVehiType != null, "气动布局类型不存在");
        }

        ScmsVehiType entity = scmsVehiTypeConverter.form2Entity(vehiTypeForm);
        boolean result = this.save(entity);
        return result;
    }

    @Override
    public VehiTypeForm getVehiTypeForm(Long vehiTypeId) {
        ScmsVehiType entity = this.getById(vehiTypeId);
        return scmsVehiTypeConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteVehiTypes(String ids) {
        List<Long> vehiTypeIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long vehiTypeId : vehiTypeIds) {
            ScmsVehiType vehiType = this.getById(vehiTypeId);
            Assert.isTrue(vehiType != null, "气动布局类型不存在");

            // 无飞行器主信息关联
            boolean isReferenced = scmsAircraftMainService.isVehiTypeReferenced(vehiTypeId);
            Assert.isTrue(!isReferenced, "该气动布局类型【{}】已被关联，请删除所有关联的飞行器主信息", vehiType.getName());

            this.removeById(vehiType);
        }
        return true;
    }
}




