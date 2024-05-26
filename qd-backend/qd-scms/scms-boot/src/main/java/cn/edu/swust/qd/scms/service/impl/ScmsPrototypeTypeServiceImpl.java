package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsPrototypeTypeConverter;
import cn.edu.swust.qd.scms.mapper.ScmsPrototypeTypeMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsPrototypeType;
import cn.edu.swust.qd.scms.model.form.PrototypeTypeForm;
import cn.edu.swust.qd.scms.model.query.PrototypeTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.PrototypeTypePageVO;
import cn.edu.swust.qd.scms.service.ScmsPrototypeTypeService;
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
 * @description 针对表【scms_prototype_type(原型类型表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsPrototypeTypeServiceImpl extends ServiceImpl<ScmsPrototypeTypeMapper, ScmsPrototypeType>
        implements ScmsPrototypeTypeService {

    private final ScmsPrototypeTypeConverter scmsPrototypeTypeConverter;


    @Override
    public Page<PrototypeTypePageVO> getPrototypeTypePage(PrototypeTypePageQuery queryParams) {
        Page<ScmsPrototypeType> entity = this.page(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        return scmsPrototypeTypeConverter.entity2VO(entity);
    }


    @Override
    public List<Option> getPrototypeTypeOptions() {
        List<ScmsPrototypeType> entities = this.list(new LambdaQueryWrapper<ScmsPrototypeType>()
                .select(ScmsPrototypeType::getId, ScmsPrototypeType::getName));
        return scmsPrototypeTypeConverter.entities2Options(entities);
    }

    @Override
    public boolean savePrototypeType(PrototypeTypeForm prototypeTypeForm) {
        Long prototypeTypeId = prototypeTypeForm.getId();
        ScmsPrototypeType oldPrototypeType = null;
        if (prototypeTypeId != null) {
            oldPrototypeType = this.getById(prototypeTypeId);
            Assert.isTrue(oldPrototypeType != null, "原型类型不存在");
        }

        ScmsPrototypeType entity = scmsPrototypeTypeConverter.form2Entity(prototypeTypeForm);
        boolean result = this.save(entity);

        return result;
    }

    @Override
    public PrototypeTypeForm getPrototypeTypeForm(Long prototypeTypeId) {
        ScmsPrototypeType entity = this.getById(prototypeTypeId);
        return scmsPrototypeTypeConverter.entity2Form(entity);
    }

    @Override
    public boolean deletePrototypeTypes(String ids) {
        List<Long> prototypeTypeIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long prototypeTypeId : prototypeTypeIds) {
            ScmsPrototypeType prototypeType = this.getById(prototypeTypeId);
            Assert.isTrue(prototypeType != null, "原型类型不存在");

            this.removeById(prototypeType);
        }
        return true;
    }
}




