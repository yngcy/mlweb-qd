package cn.edu.swust.qd.system.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.system.model.entity.SysConfidentialityLevel;
import cn.edu.swust.qd.system.model.form.CLForm;
import cn.edu.swust.qd.system.model.vo.CLPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface CLConverter {

    Page<CLPageVO> entity2VO(Page<SysConfidentialityLevel> entity);

    SysConfidentialityLevel form2Entity(CLForm clForm);

    CLForm entity2Form(SysConfidentialityLevel entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(SysConfidentialityLevel cl);


    List<Option> entities2Options(List<SysConfidentialityLevel> cls);
}
