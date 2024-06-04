package cn.edu.swust.qd.system.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.system.model.entity.SysSecretLevel;
import cn.edu.swust.qd.system.model.form.SLForm;
import cn.edu.swust.qd.system.model.vo.SLPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface SLConverter {

    Page<SLPageVO> entity2VO(Page<SysSecretLevel> entity);

    SysSecretLevel form2Entity(SLForm slForm);

    SLForm entity2Form(SysSecretLevel entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(SysSecretLevel sl);

    List<Option> entities2Options(List<SysSecretLevel> sls);
}
