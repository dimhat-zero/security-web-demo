package org.dimhat.security.authz;

import org.dimhat.security.authz.permission.PermissionResolver;
import org.dimhat.security.authz.permission.WildcardPermissionResolver;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by think on 2016/8/18.
 */
public class AuthorizingRealm {

    private PermissionResolver permissionResolver;
    public PermissionResolver getPermissionResolver() {
        return permissionResolver;
    }

    public AuthorizingRealm() {
        this.permissionResolver = new WildcardPermissionResolver();
    }

    /**
     * 是否被许可
     * @param permission 需要有的权限
     * @param info 授权信息
     * @return 存在包含返回true
     */
    private boolean isPermitted(Permission permission,AuthorizationInfo info){
        Collection<Permission> perms = getPermissions(info);
        if (perms != null && !perms.isEmpty()) {
            for (Permission perm : perms) {
                if (perm.implies(permission)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 是否存在该权限（通配）
     */
    public boolean isPermitted(String permissionStr,AuthorizationInfo info){
        Permission permission = getPermissionResolver().resolvePermission(permissionStr);
        return isPermitted(permission,info);
    }

    //获得权限对象集合
    private Collection<Permission> getPermissions(AuthorizationInfo info) {
        Set<Permission> permissions = new HashSet<Permission>();

        if (info != null) {
            Collection<Permission> perms = resolvePermissions(info.getPerms());
            if (!CollectionUtils.isEmpty(perms)) {
                permissions.addAll(perms);
            }
        }

        if (permissions.isEmpty()) {
            return Collections.emptySet();
        } else {
            return Collections.unmodifiableSet(permissions);
        }
    }

    //将权限字符串解析成权限对象
    private Collection<Permission> resolvePermissions(Collection<String> stringPerms) {
        Collection<Permission> perms = Collections.emptySet();
        PermissionResolver resolver = getPermissionResolver();
        if (resolver != null && !CollectionUtils.isEmpty(stringPerms)) {
            perms = new LinkedHashSet<Permission>(stringPerms.size());
            for (String strPermission : stringPerms) {
                Permission permission = getPermissionResolver().resolvePermission(strPermission);
                perms.add(permission);
            }
        }
        return perms;
    }

}
