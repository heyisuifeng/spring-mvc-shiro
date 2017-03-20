package com.iKMAK.controller.development;

import com.iKMAK.model.Menu;
import com.iKMAK.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leaf.Ye on 2017/3/20.
 */
@Controller
public class MenuController {

    @Inject
    private MenuService menuService;

    @RequestMapping(value = "/development/menu/sidebar")
    public String showSidebar(){
        return "/development/sidebar";
    }

    @RequestMapping(value = "/development/menu/current")
    public String getCurrent(HttpServletRequest request, HttpServletResponse response){
        Map<String,Menu> menuMap = (HashMap) request.getServletContext().getAttribute("menuMap");
        Menu menu = menuMap.get(request.getParameter("menuId"));
        int level = Integer.parseInt(menu.getLevel());
        return null;
    }
}
