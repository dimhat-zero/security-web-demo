package org.dimhat.security.dao;

import org.dimhat.security.entity.User;

/**
 * 用户接口
 * @author dimhat
 * @date 2016年8月13日 下午2:49:21
 * @version 1.0
 */
public interface UserDao {

    User save(User user);

    User update(User user);

    void delete(Long id);

    User findUserById(Long id);

    User findUserByUsername(String username);

}
