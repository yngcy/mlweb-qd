package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.bo.SampleBO;
import cn.edu.swust.qd.scms.model.entity.ScmsSample;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 25055
 * @description 针对表【scms_sample(样本表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsSample
 */
@Mapper
public interface ScmsSampleMapper extends BaseMapper<ScmsSample> {

    @DataPermission(clAlias = "s", clIdColumnName = "security", userAlias = "s")
    Page<SampleBO> getSamplePage(Page<SampleBO> objectPage);
}




