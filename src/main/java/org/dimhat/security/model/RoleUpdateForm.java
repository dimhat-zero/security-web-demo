package org.dimhat.security.model;

import java.util.List;

/**
 * Created by think on 2016/8/17.
 */
public class RoleUpdateForm {
    private Long id;

    private String roleName;

    private String description;

    private List<Long> permIds;//权限ids

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<Long> getPermIds() {
        return permIds;
    }

    public void setPermIds(List<Long> permIds) {
        this.permIds = permIds;
    }
}
