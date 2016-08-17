package org.dimhat.security.dao;

import org.dimhat.security.entity.Role;

import java.util.List;

/**
 * 角色dao
 */
public interface RoleDao {

    Role save(Role role);

    int update(Role role);

    int delete(Long id);

    Role findById(Long id);

    List<Role> findAll();
}
