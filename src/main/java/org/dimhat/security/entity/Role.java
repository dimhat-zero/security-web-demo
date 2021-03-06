package org.dimhat.security.entity;

public class Role {
    private Long id;
    private String roleName;//角色名称
    private String description;//角色描述
    private Boolean isDeleted=false;//是否删除

    private static Role admin =  null;
    public static Role getAdmin(){
        if(admin==null){
            admin = new Role();
            admin.setRoleName("admin");
            admin.setDescription("系统管理员");
        }
        return admin;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
