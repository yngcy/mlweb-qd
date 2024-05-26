package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsSoft;
import cn.edu.swust.qd.scms.model.form.SoftForm;
import cn.edu.swust.qd.scms.model.query.SoftPageQuery;
import cn.edu.swust.qd.scms.model.vo.SoftPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_soft(软件表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsSoftService extends IService<ScmsSoft> {

    /**
     * 获取软件分页列表
     *
     * @param queryParams
     * @return
     */
    Page<SoftPageVO> getSoftPage(SoftPageQuery queryParams);

    /**
     * 获取软件下拉列表
     *
     * @return
     */
    List<Option> getSoftOptions();

    /**
     * 保存软件
     *
     * @param softForm
     * @return
     */
    boolean saveSoft(SoftForm softForm);

    /**
     * 获取软件表单
     *
     * @param softId
     * @return
     */
    SoftForm getSoftForm(Long softId);

    /**
     * 删除软件
     *
     * @param ids
     * @return
     */
    boolean deleteSofts(String ids);
}
