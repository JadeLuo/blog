package com.example.data.contrlor;

import com.example.data.base.controller.BaseControllerImpl;
import com.example.data.common.Constant;
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
 *
 */
@Controller
public class LoginCtrl extends BaseControllerImpl<User, String> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private IUserService userService;

    @RequestMapping("/")
    public String toIndex (Model model) {

        return "/blog/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin(Model model) {
        User u = new User();
        model.addAttribute(u);
        return "/login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String toLogin(User user, Model model, HttpSession session, @RequestParam(defaultValue = "false") String rememberMe) {

        UsernamePasswordToken token = new UsernamePasswordToken (user.getUserName (),user.getPassWord (),rememberMe);
        Subject subject = SecurityUtils.getSubject();

        String errmsg = null;
        try {
            subject.login (token);
        } catch (UnknownAccountException e) {
            errmsg = "账号或密码错误";
            logger.info ("登录出错");
        } catch (IncorrectCredentialsException e) {
            errmsg = "账号或密码错误";
            logger.info ("登录出错");
        } catch (Exception e) {
            errmsg = "账号或密码错误";
            e.printStackTrace();
            logger.info ("登录出错");
        }
        if (subject.isAuthenticated()) {
            subject.getSession ().setAttribute ("user",userService.getByuserName (user.getUserName ()));
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
    public String register (@Valid User user,BindingResult result) {

        if (!ajax (user.getUserName ()).equals (Constant.AJAX_SUCCESS)) {
            result.rejectValue ("userName","1111","用户名已经被使用");
        }
        if (result.hasErrors()) {
            return "/register";
        }
        baseService.save (user);
        return "/login/login";
    }

    @RequestMapping(value = "/unique", method = RequestMethod.POST)
    @ResponseBody
    public String ajax(String name) {
        User user = userService.getByuserName(name);
        if (user != null) {
            return "用户名以存在";
        }
        return Constant.AJAX_SUCCESS;
    }

    @RequestMapping(value = "/loginOut")
    public String loginout(HttpSession session) {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        getSession().removeAttribute("user");
        SecurityUtils.getSubject().logout();
        return "/blog/index";
    }

    @RequestMapping(value = "/admin")
    public String admin(HttpSession session) {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        return "admin/admin";
    }

}
