package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.scms.model.entity.ScmsWingflapConfig;
import cn.edu.swust.qd.scms.model.form.WingflapConfigForm;
import cn.edu.swust.qd.scms.model.vo.WingflapConfigPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 翼舵构型转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsWingflapConfigConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    WingflapConfigPageVO entity2VO(ScmsWingflapConfig entity);

    Page<WingflapConfigPageVO> entity2VO(Page<ScmsWingflapConfig> entity);

    ScmsWingflapConfig form2Entity(WingflapConfigForm wingflapConfigForm);

    WingflapConfigForm entity2Form(ScmsWingflapConfig entity);
}
