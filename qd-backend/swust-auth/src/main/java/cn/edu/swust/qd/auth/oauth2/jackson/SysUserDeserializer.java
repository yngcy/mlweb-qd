package cn.edu.swust.qd.auth.oauth2.jackson;

import cn.edu.swust.qd.auth.model.SysUserDetails;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;
import java.util.Set;

/**
 * 系统用户反序列化器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
public class SysUserDeserializer extends JsonDeserializer<SysUserDetails> {

    private static final TypeReference<Set<SimpleGrantedAuthority>> SIMPLE_GRANTED_AUTHORITY_SET = new TypeReference<Set<SimpleGrantedAuthority>>() {
    };

    /**
     * 反序列化方法，用于将JSON格式的数据转换为SysUserDetails对象。
     * <br>
     * 这个方法将创建 {@link User} 对象。该方法确保成功创建对象，即使在序列化json中password key为空，因为凭据
     * 可以通过调用 {@link User#eraseCredentials()} 从 {@link User} 删除，在这种情况下，序列化json中不会有任何密钥。
     *
     * @param jp                     用于解析JSON的JsonParser对象。
     * @param deserializationContext 反序列化上下文环境。
     * @return SysUserDetails对象，包含了用户详细信息及授权信息。
     * @throws IOException 当读取JSON数据发生错误时抛出。
     */
    @Override
    public SysUserDetails deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        Set<? extends GrantedAuthority> authorities = mapper.convertValue(jsonNode.get("authorities"),
                SIMPLE_GRANTED_AUTHORITY_SET);
        JsonNode passwordNode = readJsonNode(jsonNode, "password");
        Long userId = readJsonNode(jsonNode, "userId").asLong();
        String username = readJsonNode(jsonNode, "username").asText();
        String password = passwordNode.asText("");
        Integer dataScope = readJsonNode(jsonNode, "dataScope").asInt();
        Long deptId = readJsonNode(jsonNode, "deptId").asLong();
        boolean enabled = readJsonNode(jsonNode, "enabled").asBoolean();
        boolean accountNonExpired = readJsonNode(jsonNode, "accountNonExpired").asBoolean();
        boolean credentialsNonExpired = readJsonNode(jsonNode, "credentialsNonExpired").asBoolean();
        boolean accountNonLocked = readJsonNode(jsonNode, "accountNonLocked").asBoolean();
        SysUserDetails result = new SysUserDetails(userId, username, password, dataScope, deptId, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                authorities);
        if (passwordNode.asText(null) == null) {
            result.eraseCredentials();
        }
        return result;
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }
}
