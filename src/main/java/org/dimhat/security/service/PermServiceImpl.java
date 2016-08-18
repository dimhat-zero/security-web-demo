package org.dimhat.security.service;

import org.dimhat.security.dao.PermDao;
import org.dimhat.security.entity.Perm;
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
    public Perm add(Perm perm) {
        return permDao.save(perm);
    }

    @Override
    public void delete(Long id) {
        permDao.delete(id);
    }

    @Override
    public void update(Perm perm) {
        permDao.update(perm);
    }

    @Override
    public Perm findPermissionById(Long id){
        return permDao.findById(id);
    }

    @Override
    public List<Perm> findPermissionsByIds(List<Long> ids) {
        List<Perm> result = new ArrayList<>(ids.size());
        for(Long id : ids){
            result.add(permDao.findById(id));
        }
        return result;
    }

    @Override
    public List<Perm> findPermissionsByIds(String ids) {
        List<Long> idList = IDUtil.parseIds(ids);
        return findPermissionsByIds(idList);
    }

}
