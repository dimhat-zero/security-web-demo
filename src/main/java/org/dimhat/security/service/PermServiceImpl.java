package org.dimhat.security.service;

import org.dimhat.security.dao.PermDao;
import org.dimhat.security.entity.Permission;
import org.dimhat.security.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限服务实现类
 */
@Service
@Transactional
public class PermServiceImpl implements PermService{

    @Autowired
    private PermDao permDao;

    @Override
    public Permission add(Permission permission) {
        return permDao.save(permission);
    }

    @Override
    public void delete(Long id) {
        permDao.delete(id);
    }

    @Override
    public void update(Permission permission) {
        permDao.update(permission);
    }

    @Override
    public Permission findPermissionById(Long id){
        return permDao.findById(id);
    }

    @Override
    public List<Permission> findPermissionsByIds(List<Long> ids) {
        List<Permission> result = new ArrayList<>(ids.size());
        for(Long id : ids){
            result.add(permDao.findById(id));
        }
        return result;
    }

    @Override
    public List<Permission> findPermissionsByIds(String ids) {
        List<Long> idList = IDUtil.parseIds(ids);
        return findPermissionsByIds(idList);
    }

}
