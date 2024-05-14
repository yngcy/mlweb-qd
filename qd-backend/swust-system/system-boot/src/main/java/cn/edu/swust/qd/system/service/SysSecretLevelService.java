package cn.edu.swust.qd.system.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.system.model.entity.SysSecretLevel;
import cn.edu.swust.qd.system.model.form.SLForm;
import cn.edu.swust.qd.system.model.query.SLPageQuery;
import cn.edu.swust.qd.system.model.vo.SLPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【sys_secret_level(人员密级表)】的数据库操作Service
 * @createDate 2024-05-04 14:41:47
 */
public interface SysSecretLevelService extends IService<SysSecretLevel> {

    /**
     * 分页获取人员密级数据
     *
     * @param queryParams
     * @return
     */
    Page<SLPageVO> getSLPage(SLPageQuery queryParams);

    /**
     * 保存人员密级
     *
     * @param slForm
     * @return
     */
    boolean saveSL(SLForm slForm);

    /**
     * 获取人员密级表单
     *
     * @param slId
     * @return
     */
    SLForm getSLForm(Long slId);

    /**
     * 删除人员密级
     *
     * @param ids
     * @return
     */
    boolean deleteSLs(String ids);

    /**
     * 更新人员密级状态
     *
     * @param slId
     * @param status
     * @return
     */
    boolean updateSLStatus(Long slId, Integer status);

    /**
     * 获取人员密级下拉列表
     *
     * @return
     */
    List<Option> listSLOptions();

    /**
     * 分配密级给用户
     *
     * @param slId
     * @param userIds
     * @return
     */
    boolean assignSLtoUser(Integer slId, List<Integer> userIds);
}