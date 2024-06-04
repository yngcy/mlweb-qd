package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsEngineTypeConverter;
import cn.edu.swust.qd.scms.mapper.ScmsEngineTypeMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsEngineType;
import cn.edu.swust.qd.scms.model.form.EngineTypeForm;
import cn.edu.swust.qd.scms.model.query.EngineTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.EngineTypePageVO;
import cn.edu.swust.qd.scms.service.ScmsAircraftMainService;
import cn.edu.swust.qd.scms.service.ScmsEngineTypeService;
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
 * @description 针对表【scms_engine_type(发动机类型)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsEngineTypeServiceImpl extends ServiceImpl<ScmsEngineTypeMapper, ScmsEngineType>
        implements ScmsEngineTypeService {

    private final ScmsEngineTypeConverter scmsEngineTypeConverter;

    private final ScmsAircraftMainService scmsAircraftMainService;

    @Override
    public String getNameById(Long id) {
        return this.getById(id).getName();
    }

    @Override
    public Page<EngineTypePageVO> getEngineTypePage(EngineTypePageQuery queryParams) {
        Page<ScmsEngineType> engineTypePage = this.page(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        Page<EngineTypePageVO> page = scmsEngineTypeConverter.entity2VO(engineTypePage);
        return page;
    }

    @Override
    public List<Option> getEngineTypeOptions() {
        List<ScmsEngineType> entities = this.list(new LambdaQueryWrapper<ScmsEngineType>()
                .select(ScmsEngineType::getId, ScmsEngineType::getName));
        return scmsEngineTypeConverter.entities2Options(entities);
    }

    @Override
    public boolean saveEngineType(EngineTypeForm engineTypeForm) {
        Long engineTypeId = engineTypeForm.getId();
        ScmsEngineType oldEngineType = null;
        if (engineTypeId != null) {
            oldEngineType = this.getById(engineTypeId);
            Assert.isTrue(oldEngineType != null, "发动机类型不存在");
        }

        ScmsEngineType engineType = scmsEngineTypeConverter.form2Entity(engineTypeForm);
        boolean result = this.saveOrUpdate(engineType);
        return result;
    }

    @Override
    public EngineTypeForm getEngineTypeForm(Long engineTypeId) {
        ScmsEngineType entity = this.getById(engineTypeId);
        return scmsEngineTypeConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteEngineTypes(String ids) {
        List<Long> engineTypeIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long engineTypeId : engineTypeIds) {
            ScmsEngineType engineType = this.getById(engineTypeId);
            Assert.isTrue(engineType != null, "发动机类型不存在");

            // 无飞行器主信息引用
            boolean isReferenced = scmsAircraftMainService.isEngineTypeReferenced(engineTypeId);
            Assert.isTrue(!isReferenced, "发动机类型【{}】被飞行器主信息关联，请先删除所有关联的飞行器主信息", engineType.getName());

            this.removeById(engineType);
        }
        return true;
    }
}