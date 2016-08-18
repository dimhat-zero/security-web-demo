package org.dimhat.security.service;


import org.dimhat.security.entity.Perm;

import java.util.List;

public interface PermService {

    Perm add(Perm perm);

    void delete(Long id);

    void update(Perm perm);

    Perm findPermissionById(Long id);

    List<Perm> findPermissionsByIds(List<Long> ids);
    List<Perm> findPermissionsByIds(String ids);
}
