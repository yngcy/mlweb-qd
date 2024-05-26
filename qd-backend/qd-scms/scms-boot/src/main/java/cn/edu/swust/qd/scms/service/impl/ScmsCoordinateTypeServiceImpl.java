package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsCoordinateTypeConverter;
import cn.edu.swust.qd.scms.mapper.ScmsCoordinateTypeMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsCoordinateType;
import cn.edu.swust.qd.scms.model.form.CoordinateTypeForm;
import cn.edu.swust.qd.scms.model.query.CoordinateTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.CoordinateTypePageVO;
import cn.edu.swust.qd.scms.service.ScmsCoordinateTypeService;
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
 * @description 针对表【scms_coordinate_type(坐标系类别表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsCoordinateTypeServiceImpl extends ServiceImpl<ScmsCoordinateTypeMapper, ScmsCoordinateType>
        implements ScmsCoordinateTypeService {

    private final ScmsCoordinateTypeConverter scmsCoordinateTypeConverter;

    @Override
    public Page<CoordinateTypePageVO> getCoordinateTypePage(CoordinateTypePageQuery queryParams) {
        Page<ScmsCoordinateType> scmsCoordinateTypePage = this.page(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));

        Page<CoordinateTypePageVO> page = scmsCoordinateTypeConverter.entity2VO(scmsCoordinateTypePage);
        return page;
    }


    @Override
    public List<Option> getCoordinateTypeOptions() {
        List<ScmsCoordinateType> entities = this.list(new LambdaQueryWrapper<ScmsCoordinateType>()
                .select(ScmsCoordinateType::getId, ScmsCoordinateType::getName));
        return scmsCoordinateTypeConverter.entities2Options(entities);
    }

    @Override
    public boolean saveCoordinateType(CoordinateTypeForm coordinateTypeForm) {
        Long coordinateTypeId = coordinateTypeForm.getId();
        ScmsCoordinateType oldCoordinateType = null;
        if (coordinateTypeId != null) {
            oldCoordinateType = this.getById(coordinateTypeId);
            Assert.isTrue(oldCoordinateType != null, "坐标系类别不存在");
        }

        ScmsCoordinateType coordinateType = scmsCoordinateTypeConverter.form2Entity(coordinateTypeForm);
        boolean result = this.save(coordinateType);
        return result;
    }

    @Override
    public CoordinateTypeForm getCoordinateTypeForm(Long coordinateTypeId) {
        ScmsCoordinateType entity = this.getById(coordinateTypeId);
        return scmsCoordinateTypeConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteCoordinateTypes(String ids) {
        List<Long> coordinateTypeIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long coordinateTypeId : coordinateTypeIds) {
            ScmsCoordinateType coordinateType = this.getById(coordinateTypeId);
            Assert.isTrue(coordinateType != null, "翼型坐标类型不存在");

            this.removeById(coordinateType);
        }
        return true;
    }
}




