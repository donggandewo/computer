package com.zzh.controller;

import com.zzh.entity.User;
import com.zzh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("login")
    ModelAndView login(User user, HttpSession session) {
        User login = userService.login(user);
        if (login == null) {
            ModelAndView mv = new ModelAndView("forward:/login.jsp");
            return mv;
        } else {
            session.setAttribute("userId", login.getId());
            session.setAttribute("mail", login.getMail());
            session.setAttribute("username", login.getUsername());
            ModelAndView mv = new ModelAndView("redirect:/product/selectByCondition");
            return mv;
        }
    }

    @RequestMapping("regist")
    String regist(User user) {
        user.setAddTime(new Date());
        userService.regist(user);
        return "login";
    }
}
