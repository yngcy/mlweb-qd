package cn.edu.swust.qd.scms.mapper;

import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.scms.model.bo.BalanceBO;
import cn.edu.swust.qd.scms.model.entity.ScmsBalance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 25055
 * @description 针对表【scms_balance(天平表)】的数据库操作Mapper
 * @createDate 2024-05-10 16:39:59
 * @Entity cn.edu.swust.qd.scms.model.entity.ScmsBalance
 */
@Mapper
public interface ScmsBalanceMapper extends BaseMapper<ScmsBalance> {


    @DataPermission(clAlias = "b", clIdColumnName = "security", userAlias = "b")
    Page<BalanceBO> getBalancePage(Page<BalanceBO> balanceBOPage);

}




