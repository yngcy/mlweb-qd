package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.entity.ScmsSupportMode;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_support_mode(模型支撑方式)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsSupportMode
 */
@Mapper
public interface ScmsSupportModeMapper extends BaseMapper<ScmsSupportMode> {

    @DataPermission(clIdColumnName = "security")
    @Override
    List<ScmsSupportMode> selectList(Wrapper<ScmsSupportMode> queryWrapper);
}




