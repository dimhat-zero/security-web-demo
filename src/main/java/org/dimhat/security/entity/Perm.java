package org.dimhat.security.entity;

import org.dimhat.security.Constant;

public class Perm {
    private Long id;

    private String permission;//权限字符串 a:b形式
    private String description;//描述
    private Boolean isDeleted;//是否删除

    private Boolean isMenu;//是否是菜单
    private Long parentId;//父节点id

    private Perm() {
    }

    public static Perm createPerm(){
        Perm perm = new Perm();
        perm.setDeleted(false);
        perm.setMenu(true);
        return perm;
    }

    private static Perm root = null;

    public synchronized static Perm getRoot(){
        if(root==null){
            root  = Perm.createPerm();
            root.setParentId(0L);
            root.setPermission(Constant.rootPerm);
            root.setDescription("权限管理");
        }
        return root;
    }
    public synchronized static void setRoot(Perm root2){
        root = root2;
    }

    public static Perm createEmpty(){
        return new Perm();
    }

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
