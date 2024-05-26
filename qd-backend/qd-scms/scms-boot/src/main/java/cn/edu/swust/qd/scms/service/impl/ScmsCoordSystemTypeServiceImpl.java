package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsCoordSystemTypeConverter;
import cn.edu.swust.qd.scms.mapper.ScmsCoordSystemTypeMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsCoordSystemType;
import cn.edu.swust.qd.scms.model.form.CoordSystemTypeForm;
import cn.edu.swust.qd.scms.model.query.CoordSystemTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.CoordSystemTypePageVO;
import cn.edu.swust.qd.scms.service.ScmsCoordSystemTypeService;
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
 * @description 针对表【scms_coord_system_type(坐标系系统类别表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsCoordSystemTypeServiceImpl extends ServiceImpl<ScmsCoordSystemTypeMapper, ScmsCoordSystemType>
        implements ScmsCoordSystemTypeService {

    private final ScmsCoordSystemTypeConverter scmsCoordSystemTypeConverter;


    @Override
    public Page<CoordSystemTypePageVO> getCoordSystemTypePage(CoordSystemTypePageQuery queryParams) {
        Page<ScmsCoordSystemType> coordSystemTypePage = this.page(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        Page<CoordSystemTypePageVO> page = scmsCoordSystemTypeConverter.entity2VO(coordSystemTypePage);
        return page;
    }


    @Override
    public List<Option> getCoordSystemTypeOptions() {
        List<ScmsCoordSystemType> entities = this.list(new LambdaQueryWrapper<ScmsCoordSystemType>()
                .select(ScmsCoordSystemType::getId, ScmsCoordSystemType::getName));
        return scmsCoordSystemTypeConverter.entities2Options(entities);
    }

    @Override
    public boolean saveCoordSystemType(CoordSystemTypeForm coordSystemTypeForm) {
        Long coordSystemTypeId = coordSystemTypeForm.getId();
        ScmsCoordSystemType oldCoordSystemType = null;
        if (coordSystemTypeId != null) {
            oldCoordSystemType = this.getById(coordSystemTypeId);
            Assert.isTrue(oldCoordSystemType != null, "坐标系系统类别不存在");
        }

        ScmsCoordSystemType coordSystemType = scmsCoordSystemTypeConverter.form2Entity(coordSystemTypeForm);
        boolean result = this.save(coordSystemType);
        return result;
    }

    @Override
    public CoordSystemTypeForm getCoordSystemTypeForm(Long coordSystemTypeId) {
        ScmsCoordSystemType coordSystemType = this.getById(coordSystemTypeId);
        return scmsCoordSystemTypeConverter.entity2Form(coordSystemType);
    }

    @Override
    public boolean deleteCoordSystemTypes(String ids) {
        List<Long> coordSystemTypeIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long coordSystemTypeId : coordSystemTypeIds) {
            ScmsCoordSystemType coordSystemType = this.getById(coordSystemTypeId);
            Assert.isTrue(coordSystemType != null, "坐标系系统类别不存在");

            this.removeById(coordSystemType);
        }

        return true;
    }
}




