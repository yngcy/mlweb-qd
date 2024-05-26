package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsSoft;
import cn.edu.swust.qd.scms.model.form.SoftForm;
import cn.edu.swust.qd.scms.model.vo.SoftPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 软件转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsSoftConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    SoftPageVO entity2VO(ScmsSoft entity);

    Page<SoftPageVO> entity2VO(Page<ScmsSoft> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsSoft entity);

    List<Option> entities2Options(List<ScmsSoft> entities);

    ScmsSoft form2Entity(SoftForm form);

    SoftForm entity2Form(ScmsSoft entity);
}
