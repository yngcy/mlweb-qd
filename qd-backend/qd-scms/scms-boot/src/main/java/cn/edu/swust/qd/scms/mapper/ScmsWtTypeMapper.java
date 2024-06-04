package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.entity.ScmsWtType;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_wt_type(风洞类型表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsWtType
 */
@Mapper
public interface ScmsWtTypeMapper extends BaseMapper<ScmsWtType> {

    @DataPermission(clIdColumnName = "security")
    @Override
    List<ScmsWtType> selectList(@Param(Constants.WRAPPER) Wrapper<ScmsWtType> queryWrapper);
}




