package org.dimhat.security.service;


import org.dimhat.security.entity.Role;
import org.dimhat.security.model.RoleModel;
import org.dimhat.security.model.RoleUpdateForm;

import java.util.List;

public interface RoleService {

    Role add(RoleUpdateForm role);

    void update(RoleUpdateForm role);

    void delete(Long id);

    Role findRoleById(Long id);

    RoleModel findDetailById(Long id);

    List<Role> findRoleByIds(List<Long> ids);

    List<Role> findRoleByIds(String ids);

    List<Role> findAll();

    void lock(Long id);

    void unlock(Long id);
}
