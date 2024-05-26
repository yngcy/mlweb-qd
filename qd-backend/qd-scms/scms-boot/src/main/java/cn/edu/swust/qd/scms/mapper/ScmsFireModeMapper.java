package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.entity.ScmsFireMode;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_fire_mode(点火方式表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsFireMode
 */
@Mapper
public interface ScmsFireModeMapper extends BaseMapper<ScmsFireMode> {

    @DataPermission(clIdColumnName = "security")
    @Override
    List<ScmsFireMode> selectList(Wrapper<ScmsFireMode> queryWrapper);
}




