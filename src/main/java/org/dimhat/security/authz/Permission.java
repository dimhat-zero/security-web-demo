package org.dimhat.security.authz;

/**
 * 权限接口
 */
public interface Permission {

    /**
     * 是否当前实例包含
     * @param p
     * @return
     */
    boolean implies(Permission p);
}
