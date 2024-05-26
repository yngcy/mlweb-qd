package cn.edu.swust.qd.scms.converter;

import cn.edu.swust.qd.scms.model.bo.SampleBO;
import cn.edu.swust.qd.scms.model.entity.ScmsSample;
import cn.edu.swust.qd.scms.model.vo.SamplePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 样本信息转换器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Mapper(componentModel = "spring")
public interface ScmsSampleConverter {

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(entity.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    SamplePageVO entity2VO(ScmsSample entity);

    Page<SamplePageVO> entity2VO(Page<ScmsSample> entity);

    @Mappings({
            @Mapping(target = "security", expression = "java(cn.edu.swust.qd.common.base.IBaseEnum.getLabelByValue(bo.getSecurity(), cn.edu.swust.qd.common.enums.CLEnum.class))")
    })
    SamplePageVO bo2VO(SampleBO bo);

    Page<SamplePageVO> bo2VO(Page<SampleBO> entity);
}
