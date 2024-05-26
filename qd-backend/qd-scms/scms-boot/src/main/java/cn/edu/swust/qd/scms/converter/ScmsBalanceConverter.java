package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.bo.BalanceBO;
import cn.edu.swust.qd.scms.model.entity.ScmsBalance;
import cn.edu.swust.qd.scms.model.form.BalanceForm;
import cn.edu.swust.qd.scms.model.vo.BalancePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 天平信息转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsBalanceConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    BalancePageVO entity2VO(ScmsBalance entity);

    Page<BalancePageVO> entity2VO(Page<ScmsBalance> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(ScmsBalance entity);

    List<Option> entities2Options(List<ScmsBalance> entities);

    ScmsBalance form2Entity(BalanceForm form);

    BalanceForm entity2Form(ScmsBalance entity);

    BalancePageVO bo2VO(BalanceBO balanceBO);

    Page<BalancePageVO> bo2VO(Page<BalanceBO> balancePage);
}
