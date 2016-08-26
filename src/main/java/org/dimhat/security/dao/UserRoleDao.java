package org.dimhat.security.dao;

import org.dimhat.security.entity.Role;
import org.dimhat.security.entity.UserRole;

import java.util.List;

/**
 * Created by think on 2016/8/17.
 */
public interface UserRoleDao {

    int addUserRole(Long userId,Long roleId);

    UserRole findById(Long id);

    int deleteUserRole(Long id);

    //find user's all role
    List<Role> findRolesByUserId(Long userId);

    int deleteUserRolesByUserId(Long userId);
}
