package cn.edu.swust.qd.system.converter;


import cn.edu.swust.qd.system.model.bo.UserBO;
import cn.edu.swust.qd.system.model.entity.SysUser;
import cn.edu.swust.qd.system.model.form.UserForm;
import cn.edu.swust.qd.system.model.vo.UserImportVO;
import cn.edu.swust.qd.system.model.vo.UserInfoVO;
import cn.edu.swust.qd.system.model.vo.UserPageVO;
import cn.edu.swust.qd.system.model.vo.UserProfileVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 用户对象转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mappings({
            @Mapping(target = "genderLabel", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(bo.getGender(), cn.edu.swust.qd.common.enums.GenderEnum.class))")
    })
    UserPageVO bo2VO(UserBO bo);

    Page<UserPageVO> bo2VO(Page<UserBO> bo);

    UserForm bo2Form(cn.edu.swust.qd.system.model.bo.UserFormBO bo);

    UserForm entity2Form(SysUser entity);

    @InheritInverseConfiguration(name = "entity2Form")
    SysUser form2Entity(UserForm form);

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    UserInfoVO entity2UserInfoVO(SysUser entity);

    @Mappings({
            @Mapping(target = "slId", expression = "java((Integer) cn.edu.swust.qd.common.base.IBaseEnum.getValueByLabel(vo.getSl(), cn.edu.swust.qd.common.enums.SLEnum.class))")
    })
    SysUser importVO2Entity(UserImportVO vo);

    @Mappings({
            @Mapping(target = "genderLabel", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(bo.getGender(), cn.edu.swust.qd.common.enums.GenderEnum.class))")
    })
    UserProfileVO userProfileBO2VO(cn.edu.swust.qd.system.model.bo.UserProfileBO bo);

}
