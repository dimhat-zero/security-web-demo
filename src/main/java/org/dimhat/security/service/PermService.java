package org.dimhat.security.service;


import org.dimhat.security.entity.Perm;
import org.dimhat.security.model.PermUpdateForm;

import java.util.List;

public interface PermService {

    /**
     * 增加一个权限
     * @param form
     * @return 权限
     */
    Perm add(PermUpdateForm form);

    /**
     * 伪删除一个权限
     * @param id
     */
    void fakeDelete(Long id);

    /**
     * 真删除一个权限
     * 检验是否被角色引用
     * 如果被引用则失败抛出异常
     * @param id
     */
    void delete(Long id);

    /**
     * 更新一个权限
     * @param form
     */
    void update(PermUpdateForm form);

    /**
     * 找到一个权限通过id
     * @param id
     * @return 权限
     */
    Perm findPermissionById(Long id);

    /**
     * 找到一堆权限通过id列表
     * @param ids
     * @return 权限列表
     */
    List<Perm> findPermissionsByIds(List<Long> ids);

    /**
     * 找到一堆权限通过id列表字符串
     * @param ids
     * @return 权限列表
     */
    List<Perm> findPermissionsByIds(String ids);

    List<Perm> findAll();

    void shiftup(Long id);

    void shiftdown(Long id);
}
