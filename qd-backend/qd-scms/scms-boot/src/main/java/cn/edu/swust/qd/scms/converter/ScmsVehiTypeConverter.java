package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsVehiType;
import cn.edu.swust.qd.scms.model.form.VehiTypeForm;
import cn.edu.swust.qd.scms.model.vo.VehiTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 气动布局类型转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsVehiTypeConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    VehiTypePageVO entity2VO(ScmsVehiType entity);

    Page<VehiTypePageVO> entity2VO(Page<ScmsVehiType> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsVehiType entity);

    List<Option> entities2Options(List<ScmsVehiType> entities);

    ScmsVehiType form2Entity(VehiTypeForm vehiTypeForm);

    VehiTypeForm entity2Form(ScmsVehiType entity);
}
