package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.bo.WindTunnelBO;
import cn.edu.swust.qd.scms.model.entity.ScmsWindTunnel;
import cn.edu.swust.qd.scms.model.form.WindTunnelForm;
import cn.edu.swust.qd.scms.model.vo.WindTunnelPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 风洞转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsWindTunnelConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")

    })
    WindTunnelPageVO entity2VO(ScmsWindTunnel entity);

    Page<WindTunnelPageVO> entity2VO(Page<ScmsWindTunnel> entity);

    ScmsWindTunnel form2Entity(WindTunnelForm windTunnelForm);

    WindTunnelForm entity2Form(ScmsWindTunnel entity);

    List<Option> entities2Options(List<ScmsWindTunnel> entities);

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(bo.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    WindTunnelPageVO bo2VO(WindTunnelBO bo);

    Page<WindTunnelPageVO> bo2VO(Page<WindTunnelBO> windTunnelPage);
}
