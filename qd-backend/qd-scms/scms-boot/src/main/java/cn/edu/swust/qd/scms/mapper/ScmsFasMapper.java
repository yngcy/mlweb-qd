package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.entity.ScmsFas;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_fas(FAS细目类别信息表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsFas
 */
@Mapper
public interface ScmsFasMapper extends BaseMapper<ScmsFas> {

    @DataPermission(clIdColumnName = "security")
    @Override
    List<ScmsFas> selectList(Wrapper<ScmsFas> queryWrapper);
}




