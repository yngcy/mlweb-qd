package cn.edu.swust.qd.common.mybatis.handler;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

/**
 * 对象数组转json
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Slf4j
@MappedJdbcTypes(value = {JdbcType.OTHER}, includeNullJdbcType = true)
public class ArrayObjectJsonTypeHandler<E> extends BaseTypeHandler<E[]> {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String STRING_JSON_ARRAY_EMPTY = "[]";


    static {
        // 未知字段忽略
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 不使用科学计数
        MAPPER.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        // null 值不输出（节省内存）
        MAPPER.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    }

    private final Class<E[]> type;

    public ArrayObjectJsonTypeHandler(Class<E[]> type) {
        Objects.requireNonNull(type);
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toJson(parameter));
    }

    @Override
    public E[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toObject(rs.getString(columnName), type);
    }

    @Override
    public E[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toObject(rs.getString(columnIndex), type);
    }

    @Override
    public E[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toObject(cs.getString(columnIndex), type);
    }

    /**
     * 对象转josn
     *
     * @param obj
     * @return
     */
    private String toJson(E[] obj) {
        if (ArrayUtils.isEmpty(obj)) {
            return STRING_JSON_ARRAY_EMPTY;
        }

        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("mybatis column to json error, obj:" + Arrays.toString(obj), e);
        }
    }

    /**
     * json转对象
     *
     * @param json
     * @param clazz
     * @return
     */
    private E[] toObject(String json, Class<E[]> clazz) {
        if (json == null) {
            return null;
        }
        if (!StringUtils.hasText(json)) {
            return newArray(clazz);
        }

        try {
            return MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("mybatis column json to object error, json:{}", json, e);
            return newArray(clazz);
        }
    }

    /**
     * 生成空的对象数组
     *
     * @param clazz
     * @return
     */
    private E[] newArray(Class<E[]> clazz) {
        return (E[]) Array.newInstance(clazz.getComponentType(), 0);
    }
}
