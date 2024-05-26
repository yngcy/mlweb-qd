package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsInjMode;
import cn.edu.swust.qd.scms.model.form.InjModeForm;
import cn.edu.swust.qd.scms.model.vo.InjModePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 注油方式转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsInjModeConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    InjModePageVO entity2VO(ScmsInjMode entity);

    Page<InjModePageVO> entity2VO(Page<ScmsInjMode> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsInjMode entity);

    List<Option> entities2Options(List<ScmsInjMode> entities);

    ScmsInjMode form2Entity(InjModeForm form);

    InjModeForm entity2Form(ScmsInjMode entity);
}
