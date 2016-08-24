package org.dimhat.security.entity;

public class Perm {
    private Long id;

    private String permission;//权限字符串 a:b形式
    private String description;//描述
    private Boolean isDeleted=false;//是否删除

    private Boolean isMenu=true;//是否是菜单
    private Long parentId;//父节点id

    public Boolean isRoot(){
        return parentId==0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
