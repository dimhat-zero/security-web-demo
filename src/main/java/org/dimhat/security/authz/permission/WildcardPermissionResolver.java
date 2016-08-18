package org.dimhat.security.authz.permission;

import org.dimhat.security.authz.Permission;

/**
 * Created by think on 2016/8/18.
 */
public class WildcardPermissionResolver implements  PermissionResolver {
    @Override
    public Permission resolvePermission(String permissionString) {
        return new WildcardPermission(permissionString);
    }
}
