package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsAirfoil;
import cn.edu.swust.qd.scms.model.form.AirfoilForm;
import cn.edu.swust.qd.scms.model.vo.AirfoilPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsAirfoilConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    AirfoilPageVO entity2VO(ScmsAirfoil entity);

    Page<AirfoilPageVO> entity2VO(Page<ScmsAirfoil> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsAirfoil entity);

    List<Option> entities2Options(List<ScmsAirfoil> entities);

    AirfoilForm entity2Form(ScmsAirfoil entity);

    ScmsAirfoil form2Entity(AirfoilForm form);
}
