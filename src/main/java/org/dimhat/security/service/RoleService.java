package org.dimhat.security.service;


import org.dimhat.security.entity.Role;

import java.util.List;

public interface RoleService {

    Role add();

    void update();

    void delete(Long id);

    List<Role> findRoleByIds(List<Long> ids);

    List<Role> findRoleByIds(String ids);

}
