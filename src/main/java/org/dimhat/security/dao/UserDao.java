package org.dimhat.security.dao;

import org.dimhat.security.entity.User;

import java.util.List;

/**
 * 用户接口
 * @author dimhat
 * @date 2016年8月13日 下午2:49:21
 * @version 1.0
 */
public interface UserDao {

    User save(User user);

	int update(User user);

    void delete(Long id);

	/**
	 * 找到用户通过id，如果不存在则出现EmptyResultDataAccessException
	 * @param id
	 * @return 找到的对象
	 */
    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll();
}
