package org.dimhat.security.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.dimhat.security.authz.util.CollectionUtils;
import org.dimhat.security.dao.PermDao;
import org.dimhat.security.dao.RolePermDao;
import org.dimhat.security.entity.Perm;
import org.dimhat.security.model.PermUpdateForm;
import org.dimhat.security.util.IDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 权限服务实现类
 * 默认会创建一个id=1,匹配*的权限
 */
@Service
@Transactional
public class PermServiceImpl implements PermService{

    private static final Logger logger =Logger.getLogger(PermServiceImpl.class);

    @Autowired
    private PermDao permDao;

    //将form转换成perm实体
    private Perm trans(PermUpdateForm form){
        Perm perm = Perm.createPerm();
        BeanUtils.copyProperties(form,perm);
        return perm;
    }


    @Override
    public Perm add(PermUpdateForm form) {
        Perm perm = trans(form);
        return permDao.save(perm);
    }

    @Override
    public void delete(Long id) {
        //delete sons
        String sql="delete from sys_perm where parent_id = "+id;
        permDao.executeSQL(sql);
        //delete self
        permDao.delete(id);
    }

    @Override
    public void update(PermUpdateForm form) {
        Perm perm = permDao.findById(form.getId());
        BeanUtils.copyProperties(form,perm);
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

    @Override
    public List<Perm> findAll() {
        return permDao.findAll();
    }

    @Override
    public List<Perm> findSubPermsByParentId(Long parentId) {
        Perm perm = Perm.createEmpty();
        perm.setParentId(parentId);
        return query(perm);
    }

    @Override
    public List<Perm> query(Perm perm) {
        return permDao.query(perm);
    }

    @Override
    public List<Perm> findPathById(Long id) {
        List<Perm> result = new ArrayList<>();
        Perm curr = permDao.findById(id);
        if(curr.getMenu()){//olny add menu
            result.add(curr);
        }
        while(!curr.isRoot()){
            curr = permDao.findById(curr.getParentId());
            result.add(curr);
        }
        Collections.reverse(result);
        return result;
    }

    @Override
    public List<Perm> findMenus() {
        Perm perm = Perm.createEmpty();
        perm.setMenu(true);
        return query(perm);
    }

}
