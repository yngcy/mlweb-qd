package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsBalanceType;
import cn.edu.swust.qd.scms.model.form.BalanceTypeForm;
import cn.edu.swust.qd.scms.model.vo.BalanceTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 天平类型转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsBalanceTypeConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    BalanceTypePageVO entity2VO(ScmsBalanceType entity);

    Page<BalanceTypePageVO> entity2VO(Page<ScmsBalanceType> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsBalanceType entity);

    List<Option> entities2Options(List<ScmsBalanceType> entities);

    ScmsBalanceType form2Entity(BalanceTypeForm form);

    BalanceTypeForm entity2Form(ScmsBalanceType entity);
}
