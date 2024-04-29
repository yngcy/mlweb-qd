package cn.edu.swust.qd.common.mybatis.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

/**
 * Integer数组类型转换json
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Slf4j
@Component
@MappedTypes(value = {Integer[].class})
@MappedJdbcTypes(value = {JdbcType.OTHER}, includeNullJdbcType = true)
public class IntegerArrayJsonTypeHandler extends ArrayObjectJsonTypeHandler<Integer> {
    public IntegerArrayJsonTypeHandler() {
        super(Integer[].class);
    }
}
