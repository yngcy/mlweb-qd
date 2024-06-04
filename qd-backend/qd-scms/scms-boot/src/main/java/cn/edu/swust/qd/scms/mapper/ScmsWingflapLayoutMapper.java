package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.entity.ScmsWingflapLayout;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_wingflap_layout(翼舵布局表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsWingflapLayout
 */
@Mapper
public interface ScmsWingflapLayoutMapper extends BaseMapper<ScmsWingflapLayout> {

    @DataPermission(clIdColumnName = "security")
    @Override
    List<ScmsWingflapLayout> selectList(@Param(Constants.WRAPPER) Wrapper<ScmsWingflapLayout> queryWrapper);
}




