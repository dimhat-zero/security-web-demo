package org.dimhat.security.model.base;

import java.io.Serializable;

/**
 * zNode 节点
 */
public class ZNode implements Serializable{
    private Long id;

    private Long pId;

    private String name;

    private Boolean checked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
