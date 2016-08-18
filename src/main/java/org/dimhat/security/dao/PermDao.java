package org.dimhat.security.dao;

import org.dimhat.security.entity.Perm;

import java.util.List;

/**
 * 权限dao
 */
public interface PermDao {

    Perm save(Perm perm);

    int delete(Long id);

    int update(Perm perm);

    Perm findById(Long id);

    List<Perm> findAll();
}
