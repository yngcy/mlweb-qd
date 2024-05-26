package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.scms.model.bo.WingSectionBO;
import cn.edu.swust.qd.scms.model.entity.ScmsWingSection;
import cn.edu.swust.qd.scms.model.form.WingSectionForm;
import cn.edu.swust.qd.scms.model.vo.WingSectionPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 翼舵截面信息转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsWingSectionConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    WingSectionPageVO entity2VO(ScmsWingSection entity);

    Page<WingSectionPageVO> entity2VO(Page<ScmsWingSection> entity);

    ScmsWingSection form2Entity(WingSectionForm wingSectionForm);

    WingSectionForm entity2Form(ScmsWingSection entity);

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(bo.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    WingSectionPageVO bo2VO(WingSectionBO bo);

    Page<WingSectionPageVO> bo2VO(Page<WingSectionBO> wingSectionPage);
}
