package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsWindTunnel;
import cn.edu.swust.qd.scms.model.form.WindTunnelForm;
import cn.edu.swust.qd.scms.model.query.WindTunnelPageQuery;
import cn.edu.swust.qd.scms.model.vo.WindTunnelPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_wind_tunnel(风洞表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsWindTunnelService extends IService<ScmsWindTunnel> {

    /**
     * 获取风洞分页列表
     *
     * @param queryParams
     * @return
     */
    Page<WindTunnelPageVO> getWindTunnelPage(WindTunnelPageQuery queryParams);

    /**
     * 保存风洞
     *
     * @param windTunnelForm
     * @return
     */
    boolean saveWindTunnel(WindTunnelForm windTunnelForm);

    /**
     * 获取风洞表单
     *
     * @param windTunnelId
     * @return
     */
    WindTunnelForm getWindTunnelForm(Long windTunnelId);

    /**
     * 删除风洞
     *
     * @param ids
     * @return
     */
    boolean deleteWindTunnels(String ids);

    /**
     * 获取风洞下拉列表
     *
     * @return
     */
    List<Option> listWindTunnelOptions();

    /**
     * 判断研究机构是否被引用
     *
     * @param companyId 研究机构ID
     * @return
     */
    boolean isCompanyReferenced(Long companyId);

    /**
     * 判断风洞类型是否被关联
     *
     * @param wtTypeId 风洞类型ID
     * @return
     */
    boolean isWtTypeReferenced(Long wtTypeId);
}
