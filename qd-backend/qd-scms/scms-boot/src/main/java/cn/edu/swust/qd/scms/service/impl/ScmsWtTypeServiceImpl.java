package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsWtTypeConverter;
import cn.edu.swust.qd.scms.mapper.ScmsWtTypeMapper;
import cn.edu.swust.qd.scms.model.entity.ScmsWtType;
import cn.edu.swust.qd.scms.model.form.WtTypeForm;
import cn.edu.swust.qd.scms.model.query.WtTypePageQuery;
import cn.edu.swust.qd.scms.model.vo.WtTypePageVO;
import cn.edu.swust.qd.scms.service.ScmsWindTunnelService;
import cn.edu.swust.qd.scms.service.ScmsWtTypeService;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_wt_type(风洞类型表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsWtTypeServiceImpl extends ServiceImpl<ScmsWtTypeMapper, ScmsWtType>
        implements ScmsWtTypeService {

    private final ScmsWtTypeConverter scmsWtTypeConverter;

    private final ScmsWindTunnelService scmsWindTunnelService;

    @Override
    public String getNameById(Long id) {
        ScmsWtType entity = this.getById(id);
        return entity.getName();
    }

    @Override
    public Page<WtTypePageVO> getWtTypePage(WtTypePageQuery queryParams) {
        Page<ScmsWtType> entity = this.page(new Page<>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        return scmsWtTypeConverter.entity2VO(entity);
    }


    @Override
    public List<Option> getWtTypeOptions() {
        List<ScmsWtType> entities = this.list(new LambdaQueryWrapper<ScmsWtType>()
                .select(ScmsWtType::getId, ScmsWtType::getName));
        return scmsWtTypeConverter.entities2Options(entities);
    }

    @Override
    public boolean saveWtType(WtTypeForm wtTypeForm) {
        Long wtTypeId = wtTypeForm.getId();
        ScmsWtType oldWtType = null;
        if (wtTypeId != null) {
            oldWtType = this.getById(wtTypeId);
            Assert.isTrue(oldWtType != null, "风洞类型不存在");
        }

        ScmsWtType entity = scmsWtTypeConverter.form2Entity(wtTypeForm);
        boolean result = this.saveOrUpdate(entity);
        return result;
    }

    @Override
    public WtTypeForm getWtTypeForm(Long wtTypeId) {
        ScmsWtType entity = this.getById(wtTypeId);
        return scmsWtTypeConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteWtTypes(String ids) {
        List<Long> wtTypeIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long wtTypeId : wtTypeIds) {
            ScmsWtType wtType = this.getById(wtTypeId);
            Assert.isTrue(wtType != null, "风洞类型不存在");

            // 无风洞信息关联
            boolean isWtTypeReferenced = scmsWindTunnelService.isWtTypeReferenced(wtTypeId);
            Assert.isTrue(!isWtTypeReferenced, "风洞类型【{}】被风洞信息关联，请先删除所有关联的风洞信息", wtType.getName());

            this.removeById(wtType);
        }
        return true;
    }
}




