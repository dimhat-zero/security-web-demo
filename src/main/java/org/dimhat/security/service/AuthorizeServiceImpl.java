package org.dimhat.security.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.dimhat.security.dao.PermDao;
import org.dimhat.security.dao.RoleDao;
import org.dimhat.security.dao.UserRoleDao;
import org.dimhat.security.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by think on 2016/8/17.
 */
@Service
@Transactional
public class AuthorizeServiceImpl implements AuthorizeService {

    private static final Logger logger = Logger.getLogger(AuthorizeServiceImpl.class);

    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermDao permDao;

    @Override
    public void addUserRole(Long userId, Long roleId) {
        userRoleDao.addUserRole(userId,roleId);
        logger.info("authorize user["+userId+"] add role["+roleId+"] success");
    }

    @Override
    public void deleteUserRole(Long id) {
        UserRole userRole = userRoleDao.findById(id);
        if(userRole!=null){
            userRoleDao.deleteUserRole(id);
            logger.info("authorize delete UserRole["+id+"]:"+ JSON.toJSONString(userRole));
        }
    }

    @Override
    public Set<String> findRoles(Long userId) {
        return null;
    }

    @Override
    public Set<String> findPerms(Long userId) {
        return null;
    }
}
