package cn.edu.swust.qd.ums.converter;

import cn.edu.swust.qd.ums.dto.MemberAuthDTO;
import cn.edu.swust.qd.ums.dto.MemberInfoDTO;
import cn.edu.swust.qd.ums.dto.MemberRegisterDTO;
import cn.edu.swust.qd.ums.model.entity.UmsMember;
import cn.edu.swust.qd.ums.model.form.MemberForm;
import cn.edu.swust.qd.ums.model.vo.MemberPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 成员转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface MemberConverter {

    @Mappings({
            @Mapping(target = "username", source = "openid")
    })
    MemberAuthDTO entity2OpenidAuthDTO(UmsMember entity);

    @Mappings({
            @Mapping(target = "username", source = "mobile")
    })
    MemberAuthDTO entity2MobileAuthDTO(UmsMember entity);

    MemberInfoDTO entity2MemberInfoDTO(UmsMember entity);

    UmsMember dto2Entity(MemberRegisterDTO memberRegisterDTO);

    @Mappings({
            @Mapping(target = "memberId", source = "id"),
            @Mapping(target = "genderLabel", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getGender(), cn.edu.swust.qd.common.enums.GenderEnum.class))")
    })
    MemberPageVO entity2VO(UmsMember entity);

    Page<MemberPageVO> entity2VO(Page<UmsMember> entity);

    UmsMember form2Entity(MemberForm memberForm);
}
