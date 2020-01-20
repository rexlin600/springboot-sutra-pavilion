package xyz.rexlin600.config;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import xyz.rexlin600.common.enums.ResponseEnum;
import xyz.rexlin600.entity.*;
import xyz.rexlin600.exception.base.BaseException;
import xyz.rexlin600.service.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: rexlin600
 * @date: 2020-01-19
 */
@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {

    private final static Integer UN_ACTIVED = 0;

    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;
    private final RoleResourceService roleResourceService;
    private final ResourceService resourceService;

    @Autowired
    public ShiroRealm(UserService userService,
                      RoleService roleService,
                      UserRoleService userRoleService,
                      RoleResourceService roleResourceService,
                      ResourceService resourceService) {
        this.userService = userService;
        this.roleService = roleService;
        this.roleResourceService = roleResourceService;
        this.userRoleService = userRoleService;
        this.resourceService = resourceService;
    }


    /**
     * 密码匹配器
     *
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        // 设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        // sha256方式加密
        hashMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        // 散列3次
        hashMatcher.setHashIterations(3);
        super.setCredentialsMatcher(hashMatcher);
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // query user by principal
        String username = authenticationToken.getPrincipal().toString();
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(true, User::getUsername, username));
        if (user == null) {
            log.error("==>  没有找到这个用户 [{}]", username);
            throw new BaseException(ResponseEnum.UN_AUTHENTICATION.getCode(), ResponseEnum.UN_AUTHENTICATION.getMsg());
        }
        if (user.getActive().equals(UN_ACTIVED)) {
            log.error("==>  用户账户未激活 [{}]", username);
            throw new BaseException(ResponseEnum.UN_ACTIVED.getCode(), ResponseEnum.UN_ACTIVED.getMsg());
        }

        // 注入认证后的信息：角色、权限等；前置条件：存在用户必分配角色、资源，否者用户不算激活
        Set<String> roleIdSet = userRoleService.list(new LambdaQueryWrapper<UserRole>().eq(true, UserRole::getUserId, user.getId())).stream()
                .map(userRole -> String.valueOf(userRole.getRoleId()))
                .collect(Collectors.toSet());
        List<Role> roleList = roleService.list(new LambdaQueryWrapper<Role>().in(true, Role::getId, roleIdSet));
        Set<String> roleSet = roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
        // 注入 RoleResource
        Set<String> resourceIdSet = roleResourceService.list(new LambdaQueryWrapper<RoleResource>().in(true, RoleResource::getRoleId, roleIdSet)).stream()
                .map(roleResource -> String.valueOf(roleResource.getResourceId()))
                .collect(Collectors.toSet());
        List<Resource> resourceList = resourceService.list(new LambdaQueryWrapper<Resource>().in(true, Resource::getId, resourceIdSet));
        Set<String> resourceSet = resourceList.stream().map(Resource::getResourceName).collect(Collectors.toSet());

        user.setRoleList(roleSet);
        user.setResourceList(resourceSet);

        // 传入用户信息及密码盐值
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) getAvailablePrincipal(principalCollection);
        Set<String> roleSet = user.getRoleList();
        Set<String> resourceSet = user.getResourceList();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleSet);
        info.setStringPermissions(resourceSet);
        return info;
    }


}