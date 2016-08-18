package org.dimhat.security.authz.permission;

import org.dimhat.security.authz.Permission;

/**
 * 权限字符串转换成权限对象接口
 */
public interface PermissionResolver {

    Permission resolvePermission(String permissionString);

}
