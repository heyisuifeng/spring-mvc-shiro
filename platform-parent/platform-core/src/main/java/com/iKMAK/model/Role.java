package com.iKMAK.model;

import java.util.List;

/**
 * Created by Leaf.Ye on 2017/3/15.
 */
public class Role {
    private int roleId;
    private String roleName;
    private String description;
    private List<User> userList;
    private List<String> permTokenList;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<String> getPermTokenList() {
        return permTokenList;
    }

    public void setPermTokenList(List<String> permTokenList) {
        this.permTokenList = permTokenList;
    }
}
