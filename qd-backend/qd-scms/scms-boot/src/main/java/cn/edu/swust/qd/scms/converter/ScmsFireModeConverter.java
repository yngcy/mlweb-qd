package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsFireMode;
import cn.edu.swust.qd.scms.model.form.FireModeForm;
import cn.edu.swust.qd.scms.model.vo.FireModePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 点火方式转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsFireModeConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    FireModePageVO entity2VO(ScmsFireMode entity);

    Page<FireModePageVO> entity2VO(Page<ScmsFireMode> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsFireMode entity);

    List<Option> entities2Options(List<ScmsFireMode> entities);

    ScmsFireMode form2Entity(FireModeForm fireModeForm);

    FireModeForm entity2Form(ScmsFireMode entity);
}
