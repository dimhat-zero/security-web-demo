package org.dimhat.security.service;

import java.util.Set;

/**
 * 授权服务
 */
public interface AuthorizeService {

    void addUserRole(Long userId,Long roleId);

    void deleteUserRole(Long id);

    //find user's all roles str
    Set<String> findRoles(Long userId);

    //find user's all perms str
    Set<String> findPerms(Long userId);
}
