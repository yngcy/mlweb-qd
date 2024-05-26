package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsFuelType;
import cn.edu.swust.qd.scms.model.form.FuelTypeForm;
import cn.edu.swust.qd.scms.model.vo.FuelTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 燃油类型转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsFuelTypeConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    FuelTypePageVO entity2VO(ScmsFuelType entity);

    Page<FuelTypePageVO> entity2VO(Page<ScmsFuelType> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsFuelType entity);

    List<Option> entities2Options(List<ScmsFuelType> entities);

    ScmsFuelType form2Entity(FuelTypeForm form);

    FuelTypeForm entity2Form(ScmsFuelType entity);
}
