package com.iKMAK.model;

import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * Created by Leaf.Ye on 2017/3/15.
 */
public class User {

    private int userId;
    @NotBlank(message = "{user.username.NotBlank}")
    private String username;
    private String nickname;
    private String password;
    private boolean rememberMe;
    private List<Role> roleList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
