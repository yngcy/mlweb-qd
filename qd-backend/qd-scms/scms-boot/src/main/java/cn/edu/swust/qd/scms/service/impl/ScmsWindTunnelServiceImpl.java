package cn.edu.swust.qd.scms.service.impl;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.converter.ScmsWindTunnelConverter;
import cn.edu.swust.qd.scms.mapper.ScmsWindTunnelMapper;
import cn.edu.swust.qd.scms.model.bo.WindTunnelBO;
import cn.edu.swust.qd.scms.model.entity.ScmsWindTunnel;
import cn.edu.swust.qd.scms.model.form.WindTunnelForm;
import cn.edu.swust.qd.scms.model.query.WindTunnelPageQuery;
import cn.edu.swust.qd.scms.model.vo.WindTunnelPageVO;
import cn.edu.swust.qd.scms.service.ScmsWindTunnelService;
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
 * @description 针对表【scms_wind_tunnel(风洞表)】的数据库操作Service实现
 * @createDate 2024-05-10 16:39:59
 */
@Service
@RequiredArgsConstructor
public class ScmsWindTunnelServiceImpl extends ServiceImpl<ScmsWindTunnelMapper, ScmsWindTunnel>
        implements ScmsWindTunnelService {

    private final ScmsWindTunnelConverter scmsWindTunnelConverter;

    @Override
    public Page<WindTunnelPageVO> getWindTunnelPage(WindTunnelPageQuery queryParams) {
        Page<WindTunnelBO> windTunnelPage = this.baseMapper.getWindTunnelPage(new Page<WindTunnelBO>(
                queryParams.getPageNum(),
                queryParams.getPageSize()
        ));
        return scmsWindTunnelConverter.bo2VO(windTunnelPage);
    }


    @Override
    public boolean saveWindTunnel(WindTunnelForm windTunnelForm) {
        Long windTunnelId = windTunnelForm.getId();
        ScmsWindTunnel oldWindTunnel = null;
        if (windTunnelId != null) {
            oldWindTunnel = this.getById(windTunnelId);
            Assert.isTrue(oldWindTunnel != null, "风洞信息不存在");
        }

        ScmsWindTunnel entity = scmsWindTunnelConverter.form2Entity(windTunnelForm);
        boolean result = this.save(entity);
        return result;
    }

    @Override
    public WindTunnelForm getWindTunnelForm(Long windTunnelId) {
        ScmsWindTunnel entity = this.getById(windTunnelId);
        return scmsWindTunnelConverter.entity2Form(entity);
    }

    @Override
    public boolean deleteWindTunnels(String ids) {
        List<Long> windTunnelIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        for (Long windTunnelId : windTunnelIds) {
            ScmsWindTunnel windTunnel = this.getById(windTunnelId);
            Assert.isTrue(windTunnel != null, "风洞信息不存在");

            this.removeById(windTunnel);
        }
        return true;
    }

    @Override
    public List<Option> listWindTunnelOptions() {
        List<ScmsWindTunnel> entities = this.list(new LambdaQueryWrapper<ScmsWindTunnel>()
                .select(ScmsWindTunnel::getId, ScmsWindTunnel::getName));
        return scmsWindTunnelConverter.entities2Options(entities);
    }

    @Override
    public boolean isCompanyReferenced(Long companyId) {
        return this.count(new LambdaQueryWrapper<ScmsWindTunnel>().eq(ScmsWindTunnel::getCompanyId, companyId)) > 0;
    }

    @Override
    public boolean isWtTypeReferenced(Long wtTypeId) {
        return this.count(new LambdaQueryWrapper<ScmsWindTunnel>().eq(ScmsWindTunnel::getWtTypeId, wtTypeId)) > 0;
    }
}




