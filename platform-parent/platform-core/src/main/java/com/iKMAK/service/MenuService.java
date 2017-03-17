package com.iKMAK.service;

import com.iKMAK.dao.MenuRepository;
import com.iKMAK.model.Menu;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Leaf.Ye on 2017/3/16.
 */
@Service
public class MenuService implements ServletContextAware{

    private ServletContext servletContext;

    @Inject
    private MenuRepository menuRepository;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void updateMenuInHttpSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        List<Menu> level0List = new ArrayList<>();
        Subject currentUser = SecurityUtils.getSubject();
        for (Menu menu:menuRepository.getMenuListByLevel("0")) {
            if (StringUtils.isBlank(menu.getPermToken())
                    || currentUser.isPermitted(menu.getPermToken())){
                level0List.add(menuUrlHandle(menu));
            }
        }

        List<Menu> level1List = menuRepository.getMenuListByLevel("1");
        Map<String,List<Menu>> level1Map = groupMenuList(level1List,currentUser);

        List<Menu> level2List = menuRepository.getMenuListByLevel("2");
        Map<String,List<Menu>> level2Map = groupMenuList(level2List,currentUser);

        session.setAttribute("level0List",level0List);
        session.setAttribute("level1Map",level1Map);
        session.setAttribute("level2Map",level2Map);
    }

    private Map<String, List<Menu>> groupMenuList(List<Menu> menuList,Subject currentUser){
        Map<String,List<Menu>> map = new HashMap<>();
        for (Menu menu : menuList) {
            if (!map.containsKey(menu.getParentId())){
                map.put(menu.getParentId(),new ArrayList<Menu>());
            }
            if ("".equals(menu.getPermToken()) || currentUser.isPermitted(menu.getPermToken())){
                map.get(menu.getParentId()).add(menuUrlHandle(menu));
            }
        }
        return map;
    }

    public Menu menuUrlHandle(Menu menu){
        String url = menu.getUrl();
        menu.setUrl(url+(url.contains("?")?"&":"?")+"menuId="+menu.getMenuId());
        return menu;
    }
}
