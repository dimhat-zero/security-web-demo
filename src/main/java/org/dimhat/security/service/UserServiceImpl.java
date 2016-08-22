package org.dimhat.security.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.dimhat.security.dao.UserDao;
import org.dimhat.security.entity.Role;
import org.dimhat.security.entity.User;
import org.dimhat.security.exception.AccountNotFindException;
import org.dimhat.security.exception.PasswordIncorrectException;
import org.dimhat.security.exception.UsernameExistException;
import org.dimhat.security.model.UserUpdateForm;
import org.dimhat.security.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * 用户服务类
 * @author dimhat
 * @date 2016年8月13日 下午5:44:30
 * @version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger= Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    /**
     * @see org.dimhat.security.service.UserService#register(org.dimhat.security.entity.User)
     */
    @Override
    public User register(User user) {
		//validate username
		User dbUser = userDao.findByUsername(user.getUsername());
		if (dbUser != null) {
			throw new UsernameExistException("用户名[" + dbUser.getUsername() + "]已存在");
		}
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

        User tuser = userDao.findByUsername(user.getUsername());
        if (tuser == null) {
			throw new AccountNotFindException("找不到用户名");
        }
        String encryptPassword = encryptPassword(user.getPassword(), tuser.getSalt());
		if (!tuser.getPassword().equals(encryptPassword)) {
			throw new PasswordIncorrectException("密码错误");
        }
        tuser.setLastLogin(new Timestamp(new Date().getTime()));
		userDao.update(tuser);
        return tuser;
    }

	@Override
	public User getUserById(Long id) {
		return userDao.findById(id);
	}

	@Override
	public void update(UserUpdateForm form) {
		User dbUser = userDao.findById(form.getId());
		BeanUtils.copyProperties(form, dbUser);
		userDao.update(dbUser);
	}

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
