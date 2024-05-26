package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.entity.ScmsPrototypeType;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_prototype_type(原型类型表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsPrototypeType
 */
@Mapper
public interface ScmsPrototypeTypeMapper extends BaseMapper<ScmsPrototypeType> {

    @DataPermission(clIdColumnName = "security")
    @Override
    List<ScmsPrototypeType> selectList(Wrapper<ScmsPrototypeType> queryWrapper);
}




