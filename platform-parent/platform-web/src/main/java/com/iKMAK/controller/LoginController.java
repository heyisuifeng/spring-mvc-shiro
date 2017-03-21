package com.iKMAK.controller;

import com.iKMAK.model.User;
import com.iKMAK.service.MenuService;
import com.iKMAK.service.UserService;
import com.iKMAK.util.HttpSessionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Leaf.Ye on 2017/3/16.
 */
@Controller
public class LoginController {

    @Inject
    private UserService userService;
    @Inject
    private MenuService menuService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpSession session){
        //spring基于session的国际化
        session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,new Locale("en"));
        return "login";
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String loginPost(User user, HttpServletRequest request, Model model) throws IOException {
        String msg="";
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            if (currentUser.isAuthenticated()){
                sessionHandle(user,request);
                return "redirect:"+getSavedUrl(request);
            }
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
            model.addAttribute("message",msg);
        }catch (ExcessiveAttemptsException e){
            model.addAttribute("message","超过3次，请2小时后重试");
        }catch (LockedAccountException e){
            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
            model.addAttribute("message",msg);
        }catch (DisabledAccountException e){
            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
            model.addAttribute("message",msg);
        }catch (ExpiredCredentialsException e){
            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
            model.addAttribute("message",msg);
        }catch (UnknownAccountException e){
            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
            model.addAttribute("message",msg);
        }catch (UnauthorizedException e){
            msg = "您没有得到相应的授权！" + e.getMessage();
            model.addAttribute("message",msg);
        }
        return "login";
    }

    private String getSavedUrl(HttpServletRequest request) {
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
        if (savedRequest == null) {
            return "/";
        }
        String savedUrl = savedRequest.getRequestUrl().replace(request.getContextPath(), "");
        if (!savedUrl.contains("menuId")) {
            savedUrl = "/";
        }
        return savedUrl;
    }

    private void sessionHandle(User user, HttpServletRequest request) {
        User loginUser = userService.getUserByUsername(user.getUsername());
        HttpSessionUtil.saveUserToSession(loginUser, request);
        menuService.updateMenuInHttpSession(request);
    }
}
