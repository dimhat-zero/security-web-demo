package org.dimhat.security.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.dimhat.security.dao.RoleDao;
import org.dimhat.security.dao.RolePermDao;
import org.dimhat.security.entity.Perm;
import org.dimhat.security.entity.Role;
import org.dimhat.security.model.RoleModel;
import org.dimhat.security.model.RoleUpdateForm;
import org.dimhat.security.util.IDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色服务
 * 默认会创建一个id=1,admin的角色
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private static  final Logger logger=  Logger.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RolePermDao rolePermDao;
    @Autowired
    private PermService permService;

    @Override
    public Role add(RoleUpdateForm form) {
        //add role
        Role role = new Role();
        BeanUtils.copyProperties(form,role);
        role = roleDao.save(role);
        logger.debug("add role success:"+ JSON.toJSONString(role));
        //add role permission
        if(form.getPermIds()!=null){
           addRolePerm(role.getId(),form.getPermIds());
        }
        return role;
    }

    private void addRolePerm(Long roleId,List<Long> permIds){
        int count=0;
        for(Long permId:permIds){
            count+=rolePermDao.addRolePerm(roleId,permId);
        }
        logger.debug("add role["+roleId+"] perm success: add count is ["+count+"]");
    }

    @Override
    public void update(RoleUpdateForm form) {
        //update role
        Role role = roleDao.findById(form.getId());
        BeanUtils.copyProperties(form,role);
        roleDao.update(role);
        logger.debug("update role success:"+JSON.toJSONString(role));
        //update role permission
        int deleteCount = rolePermDao.deleteRolePermByRoleId(role.getId());
        logger.debug("delete role["+role.getId()+"]'s all perm  count["+deleteCount+"]");
        addRolePerm(role.getId(),form.getPermIds());
    }

    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }

    @Override
    public Role findRoleById(Long id){
        return roleDao.findById(id);
    }

    @Override
    public RoleModel findDetailById(Long id) {
        Role role = findRoleById(id);
        RoleModel roleModel = new RoleModel();
        BeanUtils.copyProperties(role,roleModel);
        //find perms

        List<Long> ids = rolePermDao.findPermIdsByRoleId(role.getId());
        List<Perm> list = permService.findPermissionsByIds(ids);
        roleModel.setPermList(list);
        return roleModel;
    }

    @Override
    public List<Role> findRoleByIds(List<Long> ids) {
        //ids not null
        List<Role> roles = new ArrayList<>(ids.size());
        for(Long id:ids){
            roles.add(roleDao.findById(id));//not find ?
        }
        return roles;
    }

    @Override
    public List<Role> findRoleByIds(String ids) {
        List<Long> idList = IDUtil.parseIds(ids);
        return findRoleByIds(idList);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void lock(Long id) {
        Role role = roleDao.findById(id);
        role.setDeleted(true);
        roleDao.update(role);
    }

    @Override
    public void unlock(Long id) {
        Role role = roleDao.findById(id);
        role.setDeleted(false);
        roleDao.update(role);
    }
}
