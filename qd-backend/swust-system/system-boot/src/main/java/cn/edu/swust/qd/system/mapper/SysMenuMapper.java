package cn.edu.swust.qd.system.mapper;

/**
 * 菜单持久接口层
 *
 * @author haoxr
 * @since 2022/1/24
 */

import cn.edu.swust.qd.system.model.dto.RouteDTO;
import cn.edu.swust.qd.system.model.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<RouteDTO> listRoutes();
}
