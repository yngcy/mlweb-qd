package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.bo.WindTunnelBO;
import cn.edu.swust.qd.scms.model.entity.ScmsWindTunnel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 25055
 * @description 针对表【scms_wind_tunnel(风洞表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsWindTunnel
 */
@Mapper
public interface ScmsWindTunnelMapper extends BaseMapper<ScmsWindTunnel> {

    @DataPermission(clAlias = "w", clIdColumnName = "security", userAlias = "w")
    Page<WindTunnelBO> getWindTunnelPage(Page<WindTunnelBO> windTunnelBOPage);
}




