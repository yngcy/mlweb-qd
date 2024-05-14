package cn.edu.swust.qd.system.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.system.model.entity.SysConfidentialityLevel;
import cn.edu.swust.qd.system.model.form.CLForm;
import cn.edu.swust.qd.system.model.query.CLPageQuery;
import cn.edu.swust.qd.system.model.vo.CLPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【sys_confidentiality_level(数据密级表)】的数据库操作Service
 * @createDate 2024-05-04 14:41:11
 */
public interface SysConfidentialityLevelService extends IService<SysConfidentialityLevel> {

    /**
     * 获取数据密级分页
     *
     * @param queryParams
     * @return
     */
    Page<CLPageVO> getCLPage(CLPageQuery queryParams);

    /**
     * 保存数据密级
     *
     * @param clForm
     * @return
     */
    boolean saveCL(CLForm clForm);

    /**
     * 获取数据密级表单
     *
     * @param clId
     * @return
     */
    CLForm getCLForm(Long clId);

    /**
     * 删除数据密级
     *
     * @param ids
     * @return
     */
    boolean deleteCLs(String ids);

    /**
     * 更新数据密级状态
     *
     * @param clId
     * @param status
     * @return
     */
    boolean updateCLStatus(Long clId, Integer status);

    /**
     * 获取数据密级下拉列表
     *
     * @return
     */
    List<Option> listCLOptions();

    /**
     * 分配数据密级给数据
     *
     * @param clId
     * @param userIds
     * @return
     */
    boolean assignClToData(Integer clId, List<Integer> userIds);
}
