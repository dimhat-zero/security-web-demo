package org.dimhat.security.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.dimhat.security.dao.UserDao;
import org.dimhat.security.entity.User;
import org.dimhat.security.exception.ServiceException;
import org.dimhat.security.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务类
 * @author dimhat
 * @date 2016年8月13日 下午5:44:30
 * @version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /** 
     * @see org.dimhat.security.service.UserService#register(org.dimhat.security.entity.User)
     */
    @Override
    public User register(User user) {
        //generate salt
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        //encrypt password
        String encryptPassword = encryptPassword(user.getPassword(), salt);
        user.setPassword(encryptPassword);

        return userDao.save(user);
    }

    private String encryptPassword(String password, String salt) {
        return MD5Util.MD5(password + salt);
    }

    /** 
     * @see org.dimhat.security.service.UserService#login(org.dimhat.security.entity.User)
     */
    @Override
    public User login(User user) {

        User tuser = userDao.findUserByUsername(user.getUsername());
        if (tuser == null) {
            throw new ServiceException("找不到用户名");
        }
        String encryptPassword = encryptPassword(user.getPassword(), tuser.getSalt());
        if (tuser.getPassword().equals(encryptPassword)) {
            throw new ServiceException("密码错误");
        }
        tuser.setLastLogin(new Timestamp(new Date().getTime()));
        userDao.save(tuser);
        return tuser;
    }

}
