package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.bo.WingSectionBO;
import cn.edu.swust.qd.scms.model.entity.ScmsWingSection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 25055
 * @description 针对表【scms_wing_section(翼型截面信息)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsWingSection
 */
@Mapper
public interface ScmsWingSectionMapper extends BaseMapper<ScmsWingSection> {

    @DataPermission(clAlias = "w", clIdColumnName = "security", userAlias = "w")
    Page<WingSectionBO> getWingSectionPage(Page<WingSectionBO> wingSectionBOPage);
}




