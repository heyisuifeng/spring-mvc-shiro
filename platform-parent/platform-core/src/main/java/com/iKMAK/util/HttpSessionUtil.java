package com.iKMAK.util;

import com.iKMAK.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Leaf.Ye on 2017/3/16.
 */
public class HttpSessionUtil {

    private static final String USER_KEY="user";

    /**
     * 设置用户到session
     *
     * @param user 用户
     * @param request
     */
    public static void saveUserToSession(User user, HttpServletRequest request){
        request.getSession().setAttribute(USER_KEY,user);
    }

    /**
     * 从Session获取当前用户信息
     *
     * @param request
     * @return user
     */
    public static User getUserFromHttpSession(HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(USER_KEY);
        return attribute == null ? null : (User) attribute;
    }
}
