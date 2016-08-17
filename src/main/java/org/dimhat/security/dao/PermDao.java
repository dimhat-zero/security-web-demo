package org.dimhat.security.dao;

import org.dimhat.security.entity.Permission;

import java.util.List;

/**
 * 权限dao
 */
public interface PermDao {

    Permission save(Permission perm);

    int delete(Long id);

    int update(Permission perm);

    Permission findById(Long id);

    List<Permission> findAll();
}
