package org.dimhat.security.dao;

/**
 * Created by think on 2016/8/17.
 */
public interface RolePermDao {

    /**
     * delete role perm limit by role's id
     * @param roleId
     */
    int deleteRolePermByRoleId(Long roleId);

    int addRolePerm(Long roleId, Long permId);
}
