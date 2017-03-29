package com.example.data.contrlor;

import com.example.data.base.controller.BaseControllerImpl;
import com.example.data.entity.user.User;
import com.example.data.service.user.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by wanghuiwen on 17-1-12.
 */
@Controller
public class LoginCtrl extends BaseControllerImpl<User, String> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private IUserService userService;

    @RequestMapping("/")
    public String toIndex() {
        return "/index/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin(Model model) {
        User u = new User();
        model.addAttribute(u);
        return "/login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String toLogin(User user, Model model, HttpSession session, @RequestParam(defaultValue = "false") String rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getSalting(), rememberMe);
        Subject subject = SecurityUtils.getSubject();

        String errmsg = null;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            errmsg = "账号或密码错误";
            logger.info("认证出错---用户名不存在");
        } catch (IncorrectCredentialsException e) {
            errmsg = "账号或密码错误";
            logger.info("认证出错---密码处理问题");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (subject.isAuthenticated()) {
//            subject.getSession().setAttribute("user", userService.getByuserName(user.getUserName()));
            return "/blog/index";
        } else {
            model.addAttribute(user);
            model.addAttribute("message", errmsg);
            return "/login/login";
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute(new User());
        return "/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid User user, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "/register";
        }
        userService.save(user);
        model.addAttribute(user);
        return toLogin(user, model, session, "");
    }

    @RequestMapping(value = "/unique", method = RequestMethod.POST)
    @ResponseBody
    public String ajax(String name) {
        User user = userService.getByuserName(name);
        if (user != null) {
            return "用户名以存在";
        }
        return "ajaxSuccess";
    }

    @RequestMapping(value = "/loginOut")
    public String loginout(HttpSession session) {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        getSession().removeAttribute("user");
        SecurityUtils.getSubject().logout();
        return "/index/index";
    }

    @RequestMapping(value = "/admin")
    public String admin(HttpSession session) {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        return "/admin";
    }

}
