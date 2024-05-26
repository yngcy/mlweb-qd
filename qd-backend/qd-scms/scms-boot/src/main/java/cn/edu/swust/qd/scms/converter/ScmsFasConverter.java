package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsFas;
import cn.edu.swust.qd.scms.model.form.FasForm;
import cn.edu.swust.qd.scms.model.vo.FasPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * FAS细目类别转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsFasConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    FasPageVO entity2VO(ScmsFas entity);

    Page<FasPageVO> entity2VO(Page<ScmsFas> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsFas entity);

    List<Option> entities2Options(List<ScmsFas> entities);

    ScmsFas form2Entity(FasForm form);

    FasForm entity2Form(ScmsFas entity);
}
