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

    /**
     * 根据id查询权限
     * 如果找不到或找到多个会抛出DataAccessException异常
     * @param id
     * @return
     */
    Perm findById(Long id);

    List<Perm> findAll();

    Perm findByPerm(String s);
}
