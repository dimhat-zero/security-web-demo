package org.dimhat.security.model;

import org.dimhat.security.entity.Perm;

import java.util.List;

/**
 * Created by think on 2016/8/24.
 */
public class RoleModel {

    private Long id;

    private String roleName;

    private String description;

    private Boolean isDeleted;

    //展示值
    private List<Perm> permList;//权限列表

    public String permNames(){
        if(permList==null || permList.size()==0) return "";
        StringBuilder sb= new StringBuilder();
        for(Perm perm : permList){
            sb.append(perm.getDescription()).append(',');
        }
        sb.setLength(sb.length()-1);
        return  sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Perm> getPermList() {
        return permList;
    }

    public void setPermList(List<Perm> permList) {
        this.permList = permList;
    }
}
