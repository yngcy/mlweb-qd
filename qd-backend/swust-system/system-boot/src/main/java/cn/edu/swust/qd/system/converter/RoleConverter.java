package cn.edu.swust.qd.system.converter;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.system.model.entity.SysRole;
import cn.edu.swust.qd.system.model.form.RoleForm;
import cn.edu.swust.qd.system.model.vo.RolePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 角色转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface RoleConverter {
    Page<RolePageVO> entity2VO(Page<SysRole> entity);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(SysRole role);


    List<Option> entities2Options(List<SysRole> roles);

    SysRole form2Entity(RoleForm roleForm);

    RoleForm entity2Form(SysRole entity);
}
