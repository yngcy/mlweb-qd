package cn.edu.swust.qd.system.service.impl;

import cn.edu.swust.qd.common.constant.GlobalConstants;
import cn.edu.swust.qd.common.constant.RedisConstants;
import cn.edu.swust.qd.common.constant.SystemConstants;
import cn.edu.swust.qd.common.security.service.PermissionService;
import cn.edu.swust.qd.common.security.utils.SecurityUtils;
import cn.edu.swust.qd.common.sms.property.AliyunSmsProperties;
import cn.edu.swust.qd.common.sms.service.SmsService;
import cn.edu.swust.qd.system.converter.UserConverter;
import cn.edu.swust.qd.system.dto.UserAuthInfo;
import cn.edu.swust.qd.system.mapper.SysUserMapper;
import cn.edu.swust.qd.system.model.bo.UserBO;
import cn.edu.swust.qd.system.model.bo.UserFormBO;
import cn.edu.swust.qd.system.model.bo.UserProfileBO;
import cn.edu.swust.qd.system.model.entity.SysUser;
import cn.edu.swust.qd.system.model.form.UserForm;
import cn.edu.swust.qd.system.model.form.UserRegisterForm;
import cn.edu.swust.qd.system.model.query.UserPageQuery;
import cn.edu.swust.qd.system.model.vo.UserExportVO;
import cn.edu.swust.qd.system.model.vo.UserInfoVO;
import cn.edu.swust.qd.system.model.vo.UserPageVO;
import cn.edu.swust.qd.system.model.vo.UserProfileVO;
import cn.edu.swust.qd.system.service.SysRoleService;
import cn.edu.swust.qd.system.service.SysUserRoleService;
import cn.edu.swust.qd.system.service.SysUserService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 25055
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2024-05-04 14:39:13
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    private final PasswordEncoder passwordEncoder;

    private final SysUserRoleService userRoleService;

    private final SysRoleService roleService;

    private final UserConverter userConverter;

    private final PermissionService permissionService;

    private final SmsService smsService;

    private final AliyunSmsProperties aliyunSmsProperties;

    private final StringRedisTemplate redisTemplate;


    @Override
    public Page<UserPageVO> getUserPage(UserPageQuery queryParams) {
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<UserBO> page = new Page<>(pageNum, pageSize);

        Page<UserBO> userPage = this.baseMapper.getUserPage(page, queryParams);

        return userConverter.bo2VO(userPage);
    }


    @Override
    public UserForm getUserForm(Long userId) {
        UserFormBO userFormBO = this.baseMapper.getUserDetail(userId);
        return userConverter.bo2Form(userFormBO);
    }


    @Override
    public boolean saveUser(UserForm userForm) {
        String username = userForm.getUsername();

        long count = this.count(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username));
        Assert.isTrue(count == 0, "用户名已存在");

        SysUser entity = userConverter.form2Entity(userForm);

        String defaultEncryptPassword = passwordEncoder.encode(SystemConstants.DEFAULT_PASSWORD);
        entity.setPassword(defaultEncryptPassword);

        boolean result = this.saveOrUpdate(entity);
        if (result) {
            userRoleService.saveUserRoles(entity.getId(), userForm.getRoleIds());
        }

        return result;
    }

    @Override
    public boolean updateUser(Long userId, UserForm userForm) {

        String username = userForm.getUsername();
        long count = this.count(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username)
                .ne(SysUser::getId, userId));
        Assert.isTrue(count == 0, "用户名已存在");

        SysUser entity = userConverter.form2Entity(userForm);

        boolean result = this.updateById(entity);
        if (result) {
            userRoleService.saveUserRoles(entity.getId(), userForm.getRoleIds());
        }
        return result;
    }

    @Override
    public boolean updatePassword(Long userId, String password) {
        return this.update(new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getId, userId)
                .set(SysUser::getPassword, passwordEncoder.encode(password)));
    }

    @Override
    public UserAuthInfo getUserAuthInfo(String username) {
        UserAuthInfo userAuthInfo = this.baseMapper.getUserAuthInfo(username);

        if (userAuthInfo != null) {
            Set<String> roles = userAuthInfo.getRoles();
            if (CollectionUtil.isNotEmpty(roles)) {
                // 获取最大范围的数据权限（DataScope越小，拥有的数据范围权限越大，所以获取得到角色列表中最小的DataScope）
                Integer dataScope = roleService.getMaxDataRangeScope(roles);
                userAuthInfo.setDataScope(dataScope);
            }

        }
        return userAuthInfo;
    }

    @Override
    public UserInfoVO getCurrentUserInfo() {
        SysUser user = this.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, SecurityUtils.getUsername())
                .select(
                        SysUser::getId,
                        SysUser::getNickname,
                        SysUser::getAvatar
                ));
        UserInfoVO userInfoVO = userConverter.entity2UserInfoVO(user);

        Set<String> roles = SecurityUtils.getRoles();
        userInfoVO.setRoles(roles);

        if (CollectionUtil.isNotEmpty(roles)) {
            Set<String> perms = permissionService.getRolePermsFormCache(roles);
            userInfoVO.setPerms(perms);
        }

        return userInfoVO;
    }

    @Override
    public boolean registerUser(UserRegisterForm userRegisterForm) {
        String mobile = userRegisterForm.getMobile();
        String code = userRegisterForm.getCode();
        // 校验验证码
        String cacheCode = redisTemplate.opsForValue().get(RedisConstants.REGISTER_SMS_CODE_PREFIX + mobile);
        if (!StrUtil.equals(code, cacheCode)) {
            log.warn("验证码不匹配或不存在：{}", mobile);
            return false;
        }
        // 校验通过，删除验证码
        redisTemplate.delete(RedisConstants.REGISTER_SMS_CODE_PREFIX + mobile);
        long count = this.count(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getMobile, mobile)
                .or()
                .eq(SysUser::getUsername, mobile));
        Assert.isTrue(count == 0, "手机号已注册");

        SysUser entity = new SysUser();
        BeanUtil.copyProperties(userRegisterForm, entity);
        entity.setStatus(GlobalConstants.STATUS_YES);

        // 设置默认加密密码
        String defaultEncryptPassword = passwordEncoder.encode(SystemConstants.DEFAULT_PASSWORD);
        entity.setPassword(defaultEncryptPassword);

        return this.save(entity);
    }

    @Override
    public boolean sendRegistrationSmsCode(String mobile) {
        // 获取短信验证码模板
        String templateCode = aliyunSmsProperties.getTemplateCodes().get("register");

        // 生成随机4位数验证码
        String code = RandomUtil.randomNumbers(4);

        String templateParams = JSONUtil.toJsonStr(Collections.singletonMap("code", code));

        boolean result = smsService.sendSms(mobile, templateCode, templateParams);
        if (result) {
            // 将验证码缓存，有效期 5 分钟
            redisTemplate.opsForValue().set(RedisConstants.REGISTER_SMS_CODE_PREFIX + mobile, code, 5, TimeUnit.MINUTES);
        }

        return result;
    }

    @Override
    public UserProfileVO getUserProfile() {
        Long userId = SecurityUtils.getUserId();
        UserProfileBO userProfileBO = this.baseMapper.getUserProfile(userId);

        return userConverter.userProfileBO2VO(userProfileBO);
    }

    @Override
    public List<UserExportVO> listExportUsers(UserPageQuery queryParams) {
        return listExportUsers(queryParams);
    }

    @Override
    public boolean deleteUsers(String ids) {
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return this.removeByIds(idList);
    }

    @Override
    public boolean logout() {
        String jti = SecurityUtils.getJti();
        // 使用 Optional 处理可能的空值
        Optional<Long> expireTimeOpt = Optional.ofNullable(SecurityUtils.getExp());

        long currentTimeInSeconds = System.currentTimeMillis() / 1000;

        expireTimeOpt.ifPresent(expireTime -> {
            if (expireTime > currentTimeInSeconds) {
                // token 未过期，添加至缓存作为黑名单，缓存时间为 token 剩余有效时间
                long remainingTimeInSeconds = expireTime - currentTimeInSeconds;
                redisTemplate.opsForValue().set(RedisConstants.TOKEN_BLACKLIST_PREFIX + jti, "", remainingTimeInSeconds, TimeUnit.SECONDS);
            }
        });

        if (expireTimeOpt.isEmpty()) {
            // token 用不国企，则永久加入黑名单
            redisTemplate.opsForValue().set(RedisConstants.TOKEN_BLACKLIST_PREFIX + jti, "");
        }

        return true;
    }

}




