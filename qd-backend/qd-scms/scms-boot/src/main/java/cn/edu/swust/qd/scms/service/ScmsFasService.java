package cn.edu.swust.qd.scms.service;

import cn.edu.swust.qd.common.web.model.Option;
import cn.edu.swust.qd.scms.model.entity.ScmsFas;
import cn.edu.swust.qd.scms.model.form.FasForm;
import cn.edu.swust.qd.scms.model.query.FasPageQuery;
import cn.edu.swust.qd.scms.model.vo.FasPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 25055
 * @description 针对表【scms_fas(FAS细目类别信息表)】的数据库操作Service
 * @createDate 2024-05-10 16:39:59
 */
public interface ScmsFasService extends IService<ScmsFas> {

    /**
     * 获取FAS细目类别分页列表
     *
     * @param queryParams
     * @return
     */
    Page<FasPageVO> getFasPage(FasPageQuery queryParams);

    /**
     * 获取FAS细目类别下拉列表
     *
     * @return
     */
    List<Option> getFasOptions();

    /**
     * 保存FAS细目类别
     *
     * @param fasForm
     * @return
     */
    boolean saveFas(FasForm fasForm);

    /**
     * 获取FAS表单
     *
     * @param fasId
     * @return
     */
    FasForm getFasForm(Long fasId);

    /**
     * 删除FAS
     *
     * @param ids
     * @return
     */
    boolean deleteFases(String ids);
}
