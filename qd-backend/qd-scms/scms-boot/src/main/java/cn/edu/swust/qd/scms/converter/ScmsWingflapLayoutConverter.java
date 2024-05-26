package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.scms.model.entity.ScmsWingflapLayout;
import cn.edu.swust.qd.scms.model.form.WingflapLayoutForm;
import cn.edu.swust.qd.scms.model.vo.WingflapLayoutPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 翼舵布局转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsWingflapLayoutConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    WingflapLayoutPageVO entity2VO(ScmsWingflapLayout entity);

    Page<WingflapLayoutPageVO> entity2VO(Page<ScmsWingflapLayout> entity);

    ScmsWingflapLayout form2Entity(WingflapLayoutForm form);

    WingflapLayoutForm entity2Form(ScmsWingflapLayout entity);
}
