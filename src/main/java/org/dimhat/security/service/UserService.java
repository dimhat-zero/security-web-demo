package org.dimhat.security.service;

import org.dimhat.security.entity.User;
import org.dimhat.security.model.UserUpdateForm;

/**
 * 用户服务接口
 * @author dimhat
 * @date 2016年8月13日 下午5:16:37
 * @version 1.0
 */
public interface UserService {

    User register(User user);

    User login(User user);

	User getUserById(Long id);

	void update(UserUpdateForm form);
}
