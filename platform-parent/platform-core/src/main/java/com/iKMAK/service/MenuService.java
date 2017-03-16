package com.iKMAK.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Leaf.Ye on 2017/3/16.
 */
@Service
public class MenuService implements ServletContextAware{

    private ServletContext servletContext;
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void updateMenuInHttpSession(HttpServletRequest request){

    }
}
