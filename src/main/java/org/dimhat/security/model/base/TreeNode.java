package org.dimhat.security.model.base;

import java.io.Serializable;

/**
 * zNode 节点
 */
public class TreeNode implements Serializable{
    private Long id;//id

    private Long pId;//父id

    private String name;//展示名称

    private Boolean checked;//是否被选中

    private Boolean open;//是否打开

    private Boolean isParent;//是否是文件夹

    private String iconSkin;//css的className

    public TreeNode(Long id, Long parentId, String description) {
        this.id= id;
        this.pId = parentId;
        this.name = description;
    }

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

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean parent) {
        isParent = parent;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }
}
