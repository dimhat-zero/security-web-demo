package org.dimhat.security.model;

import org.dimhat.security.entity.Perm;
import org.springframework.beans.BeanUtils;

/**
 * 新增时id不必须，其他字段都是必须的
 */
public class PermUpdateForm {
    //表单
    private Long id;
    private String permission;//权限字符串
    private String description;//描述
    private Boolean isMenu=false;//是否是菜单
    private Long parentId;//父节点id

    //展示值
    private String parentDescription;

    public PermUpdateForm() {
    }

    public PermUpdateForm(Perm perm) {
        this.id = perm.getId();
        this.permission = perm.getPermission();
        this.description = perm.getDescription();
        this.isMenu = perm.getMenu();
        this.parentId  = perm.getParentId();
    }

    public Boolean isRoot(){
        return parentId==0;
    }

    public String getParentDescription() {
        return parentDescription;
    }

    public void setParentDescription(String parentDescription) {
        this.parentDescription = parentDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getMenu() {
        return isMenu;
    }

    public void setMenu(Boolean menu) {
        isMenu = menu;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


}
