package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.entity.ScmsInjMode;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_inj_mode(注油方式表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsInjMode
 */
@Mapper
public interface ScmsInjModeMapper extends BaseMapper<ScmsInjMode> {

    @DataPermission(clIdColumnName = "security")
    @Override
    List<ScmsInjMode> selectList(Wrapper<ScmsInjMode> queryWrapper);
}




