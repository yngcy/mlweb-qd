package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.scms.converter.ScmsWingflapLayoutConverter;
import cn.edu.swust.qd.scms.mapper.ScmsWingflapLayoutMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsWingflapLayout;
import cn.edu.swust.qd.scms.model.form.WingflapLayoutForm;
import cn.edu.swust.qd.scms.model.query.WingflapLayoutPageQuery;
import cn.edu.swust.qd.scms.model.vo.WingflapLayoutPageVO;
import cn.edu.swust.qd.scms.service.ScmsWingflapLayoutService;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_wingflap_layout(翼舵布局表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsWingflapLayoutServiceImpl extends ServiceImpl<ScmsWingflapLayoutMapper, ScmsWingflapLayout>
        implements ScmsWingflapLayoutService {

    private final ScmsWingflapLayoutConverter scmsWingflapLayoutConverter;

    @Override
    public Page<WingflapLayoutPageVO> getWingflapLayoutPage(WingflapLayoutPageQuery queryParams) {
        Page<ScmsWingflapLayout> entity = this.page(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        return scmsWingflapLayoutConverter.entity2VO(entity);
    }


    @Override
    public boolean saveWingflapLayout(WingflapLayoutForm wingflapLayoutForm) {
        Long wingflapLayoutId = wingflapLayoutForm.getId();
        ScmsWingflapLayout oldWingflapLayout = null;
        if (wingflapLayoutId != null) {
            oldWingflapLayout = this.getById(wingflapLayoutId);
            Assert.isTrue(oldWingflapLayout != null, "翼舵布局不存在");
        }

        ScmsWingflapLayout entity = scmsWingflapLayoutConverter.form2Entity(wingflapLayoutForm);
        boolean result = this.save(entity);
        return result;
    }

    @Override
    public WingflapLayoutForm getWingflapLayoutForm(Long wingflapLayoutId) {
        ScmsWingflapLayout entity = this.getById(wingflapLayoutId);
        return scmsWingflapLayoutConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteWingflapLayouts(String ids) {
        List<Long> wingflapLayoutIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long wingflapLayoutId : wingflapLayoutIds) {
            ScmsWingflapLayout wingflapLayout = this.getById(wingflapLayoutId);
            Assert.isTrue(wingflapLayout != null, "翼舵布局不存在");

            this.removeById(wingflapLayout);
        }
        return true;
    }
}




