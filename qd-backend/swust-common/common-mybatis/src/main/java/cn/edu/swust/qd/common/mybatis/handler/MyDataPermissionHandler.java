package cn.edu.swust.qd.common.mybatis.handler;

import cn.edu.swust.qd.common.base.IBaseEnum;
import cn.edu.swust.qd.common.mybatis.annotation.DataPermission;
import cn.edu.swust.qd.common.mybatis.enums.DataScopeEnum;
import cn.edu.swust.qd.common.security.utils.SecurityUtils;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;

import java.lang.reflect.Method;

/**
 * 数据权限控制
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
public class MyDataPermissionHandler implements DataPermissionHandler {


    /**
     * 根据给定的表达式和映射语句ID，获取经过数据权限过滤的SQL段。
     *
     * @param where             表达式，原始的SQL WHERE子句。
     * @param mappedStatementId 映射语句ID，用于定位具体的数据库操作方法。
     * @return Expression，经过数据权限过滤后的SQL WHERE子句表达式。
     * @throws Exception 可能抛出的异常，由Class.forName和method.invoke触发。
     */
    @Override
    @SneakyThrows
    public Expression getSqlSegment(Expression where, String mappedStatementId) {

        Class<?> clazz = Class.forName(mappedStatementId.substring(0, mappedStatementId.lastIndexOf(StringPool.DOT)));
        String methodName = mappedStatementId.substring(mappedStatementId.lastIndexOf(StringPool.DOT));
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            DataPermission annotation = method.getAnnotation(DataPermission.class);
            // 没有注解，不进行数据权限过滤
            if (annotation == null) {
                return where;
            }
            // 超级管理员不受数据权限限制
            if (SecurityUtils.isRoot()) {
                return where;
            }
            if (ObjectUtils.isNotEmpty(annotation)
                    && (method.getName().equals(methodName) || (method.getName() + "_COUNT").equals(methodName))) {
                return dataScopeFilter(annotation.clAlias(), annotation.clIdColumnName(), annotation.userAlias(), annotation.userIdColumnName(), where);
            }
        }
        return where;
    }

    /**
     * 根据用户的数据权限范围，对SQL查询进行过滤。
     *
     * @param clAlias          数据密级别名，如果查询中使用了别名
     * @param clIdColumName    数据密级ID的列名
     * @param userAlias        用户别名，如果查询中使用了别名
     * @param userIdColumnName 用户ID的列名
     * @param where            原始查询条件表达式
     * @return 过滤后的查询条件表达式，加入了数据权限控制
     */
    @SneakyThrows
    public static Expression dataScopeFilter(String clAlias, String clIdColumName, String userAlias, String userIdColumnName, Expression where) {
        String clColumnName = StrUtil.isNotBlank(clAlias) ? (clAlias + StringPool.DOT + clIdColumName) : clIdColumName;
        String userColumnName = StrUtil.isNotBlank(userAlias) ? (userAlias + StringPool.DOT + userIdColumnName) : userIdColumnName;

        // 获取当前用户的数据权限
        Integer dataScope = SecurityUtils.getDataScope();
        DataScopeEnum dataScopeEnum = IBaseEnum.getEnumByValue(dataScope, DataScopeEnum.class);

        // slId 和 clId 默认一一对应
        Long slId, userId;
        String appendSqlStr;
        switch (dataScopeEnum) {
            case ALL -> {
                return where;
            }
            case CL -> {
                slId = SecurityUtils.getSecretLevel();
                appendSqlStr = clColumnName + StringPool.EQUALS + slId;
            }
            case SELF -> {
                userId = SecurityUtils.getUserId();
                appendSqlStr = userColumnName + StringPool.EQUALS + userId;
            }
            // 默认密级及子密级数据权限（clId >= slId）
            default -> {
                slId = SecurityUtils.getSecretLevel();
                // todo 支持树形筛选
                appendSqlStr = clColumnName + StringPool.HTML_GT + slId;
            }
        }

        if (StrUtil.isBlank(appendSqlStr)) {
            return where;
        }

        Expression appendExpression = CCJSqlParserUtil.parseCondExpression(appendSqlStr);

        if (where == null) {
            return appendExpression;
        }

        return new AndExpression(where, appendExpression);
    }
}
