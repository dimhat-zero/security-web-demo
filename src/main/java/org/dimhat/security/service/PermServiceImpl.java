package org.dimhat.security.service;

import org.dimhat.security.dao.PermDao;
import org.dimhat.security.entity.Perm;
import org.dimhat.security.model.PermUpdateForm;
import org.dimhat.security.util.IDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限服务实现类
 * 默认会创建一个id=1,匹配*的权限
 */
@Service
@Transactional
public class PermServiceImpl implements PermService{

    @Autowired
    private PermDao permDao;

    //将form转换成perm实体
    private Perm trans(PermUpdateForm form){
        Perm perm = new Perm();
        BeanUtils.copyProperties(form,perm);
        return perm;
    }

    private Integer getRankForParent(long parentId){
        if(parentId==0) return 0;//root rank is zero
        Perm parentPerm = permDao.findById(parentId);
        //select for udpate or plus 1，防止并发出错
        return parentPerm.getSubRankSeq();
    }

    @Override
    public Perm add(PermUpdateForm form) {
        Perm perm = trans(form);
        perm.setRank(getRankForParent(perm.getParentId()));
        //get rank from parent s
        return permDao.save(perm);
    }

    @Override
    public void delete(Long id) {
        permDao.delete(id);
    }

    @Override
    public void update(PermUpdateForm form) {
        Perm perm = trans(form);
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
    public void fakeDelete(Long id) {
        Perm perm = permDao.findById(id);
        perm.setDeleted(true);
        permDao.update(perm);
    }

    @Override
    public void shiftup(Long id) {
        //find pre
        String sql="select * from sys_perm where id < ? order by id desc limit 1";
        
        //swap rank
    }

    @Override
    public void shiftdown(Long id) {
        //find next
        String sql="select * from sys_perm where id > ? order by id asc limit 1";
        //swap rank
    }

}
