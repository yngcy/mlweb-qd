package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.bo.AircraftMainBO;
import cn.edu.swust.qd.scms.model.entity.ScmsAircraftMain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_aircraft_main(飞行器主信息表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsAircraftMain
 */
@Mapper
public interface ScmsAircraftMainMapper extends BaseMapper<ScmsAircraftMain> {

    /**
     * 获取飞行器主信息分页列表
     *
     * @param page 分页参数
     * @return {@link List <AircraftMainBO>}
     */
    @DataPermission(clAlias = "a", clIdColumnName = "security", userAlias = "a")
    Page<AircraftMainBO> getAircraftMainPage(Page<AircraftMainBO> page);
}




