package org.dimhat.security.authz;

import java.util.Collection;

/**
 * 授权信息接口类
 */
public interface AuthorizationInfo {

    Collection<String> getRoles();

    Collection<String> getPerms();
}
