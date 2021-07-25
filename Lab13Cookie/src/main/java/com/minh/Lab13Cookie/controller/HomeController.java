package com.minh.Lab13Cookie.controller;

import com.minh.Lab13Cookie.util.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome(Model model) {
        return "home";
    }

    @GetMapping("/blog")
    public String showBlog(Model model) {
        return "blog";
    }

    @GetMapping("/setting")
    public String showSetting(HttpServletRequest request, HttpServletResponse response, Model model,
                              @RequestParam(required = false) String bgColor,
                              @RequestParam(required = false) String fontColor,
                              @RequestParam(required = false) String fontSize) {

        if(bgColor != null) {
            Cookie bgColor1 = CookieUtil.createCookie(
                    "bgColor",
                    bgColor,
                    30,
                    true,
                    false,
                    "/",
                    request.getServerName()
            );
            response.addCookie(bgColor1);
        }

        if (fontColor != null) {
            Cookie fontColor1 = CookieUtil.createCookie(
                    "fontColor",
                    fontColor,
                    30,
                    true,
                    false,
                    "/",
                    request.getServerName()
            );
            response.addCookie(fontColor1);
        }

        if(fontSize != null) {
            Cookie fontSize1 = CookieUtil.createCookie(
                    "fontSize",
                    fontSize,
                    30,
                    true,
                    false,
                    "/",
                    request.getServerName()
            );
            response.addCookie(fontSize1);
        }

        return "setting";
    }

}
