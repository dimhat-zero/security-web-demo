package org.dimhat.security.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.dimhat.security.dao.*;
import org.dimhat.security.entity.Perm;
import org.dimhat.security.entity.Role;
import org.dimhat.security.entity.User;
import org.dimhat.security.entity.UserRole;
import org.dimhat.security.model.PermUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
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
    @Autowired
    private RolePermDao rolePermDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;

    /**
     * 初始化数据库中的数据
     * admin用户拥有admin角色拥有*权限
     */
    @PostConstruct
    public void init(){
        //create perm
        Perm perm = null;
        try {
            perm = permDao.findByPerm("*");
        }catch (DataAccessException de){
            perm = new Perm();
            perm.setParentId(0L);
            perm.setRank(0);
            perm.setPermission("*");
            perm.setDescription("系统权限根节点");
            perm = permDao.save(perm);
        }

        //create role
        Role role=null;
        try{
            role = roleDao.findByRole("admin");
        }catch(DataAccessException e){
            role = new Role();
            role.setRoleName("admin");
            role.setDescription("系统管理员");
            role = roleDao.save(role);
            //add role perm
            rolePermDao.addRolePerm(role.getId(),perm.getId());
        }
        //create user
        User user=null;
        try{
            user = userDao.findByUsername("admin");
        }catch(DataAccessException e){
            user=new User();
            user.setUsername("admin");//系统管理员
            user.setPassword("123456");//默认密码
            user = userService.register(user);
            //add user role
            userRoleDao.addUserRole(user.getId(),role.getId());
        }

    }

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
