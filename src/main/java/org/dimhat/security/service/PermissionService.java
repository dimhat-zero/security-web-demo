package org.dimhat.security.service;


import org.dimhat.security.entity.Permission;

import java.util.List;

public interface PermissionService {

    Permission add(Permission permission);

    void delete(Long id);

    void update(Permission permission);

    List<Permission> findPermissionsByIds(List<Long> ids);
    List<Permission> findPermissionsByIds(String ids);
}
