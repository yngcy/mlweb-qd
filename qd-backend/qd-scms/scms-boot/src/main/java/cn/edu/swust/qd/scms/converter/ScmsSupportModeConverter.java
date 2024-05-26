package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsSupportMode;
import cn.edu.swust.qd.scms.model.form.SupportModeForm;
import cn.edu.swust.qd.scms.model.vo.SupportModePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 模型支撑方式转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsSupportModeConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    SupportModePageVO entity2VO(ScmsSupportMode entity);

    Page<SupportModePageVO> entity2VO(Page<ScmsSupportMode> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsSupportMode entity);

    List<Option> entities2Options(List<ScmsSupportMode> entities);

    ScmsSupportMode form2Entity(SupportModeForm supportModeForm);

    SupportModeForm entity2Form(ScmsSupportMode entity);
}
