package org.dimhat.security.service;

import org.dimhat.security.entity.User;

/**
 * TODO
 * @author dimhat
 * @date 2016年8月13日 下午5:16:37
 * @version 1.0
 */
public interface UserService {

    User register(User user);

    User login(User user);

}
