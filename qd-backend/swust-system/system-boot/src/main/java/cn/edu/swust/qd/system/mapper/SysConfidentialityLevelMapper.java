package cn.edu.swust.qd.system.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.system.model.entity.SysConfidentialityLevel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【sys_confidentiality_level(数据密级表)】的数据库操作Mapper
 * @createDate 2024-05-04 14:41:11
 * @Entity cn.edu.swust.qd.system.model.entity.SysConfidentialityLevel
 */
@Mapper
public interface SysConfidentialityLevelMapper extends BaseMapper<SysConfidentialityLevel> {

    @DataPermission(clIdColumnName = "sort")
    @Override
    List<SysConfidentialityLevel> selectList(@Param(Constants.WRAPPER) Wrapper<SysConfidentialityLevel> queryWrapper);
}




