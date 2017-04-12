package com.example.data.contrlor;

import com.alibaba.fastjson.JSON;
import com.example.data.base.controller.BaseControllerImpl;
import com.example.data.base.ui.ListParam;
import com.example.data.common.Constant;
import com.example.data.entity.role.Role;
import com.example.data.entity.user.User;
import com.example.data.service.role.IRoleService;
import com.example.data.service.user.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by wanghuiwen on 17-1-18.
 * 用户操作
 */
@Controller
@RequestMapping("/base/user")
public class UserController extends BaseControllerImpl<User, String> {
    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/list")
    public String list(@RequestParam(defaultValue = "0") int pageNumber, Model model) {

        Page<User> users = userService.findAll (getPage (pageNumber));
        model.addAttribute (users);
        ListParam listParam = new ListParam();
        listParam.setTitle("用户管理");
        listParam.setBaseurl ("/base/user");
        model.addAttribute(listParam);
        return "/base/user/list";
    }

    @RequiresPermissions("user:add")
    @Override
    public String save(@RequestParam(defaultValue = "") String id, Model model) {
        super.save(id, model);
        return "/base/user/user_add";
    }


    /**
     * 为用户添加角色
     */
    @RequestMapping(value = "/addRole", method = RequestMethod.GET)
    public String addRoleForUser(@RequestParam() User id, Model model) {
        Iterable<Role> list = roleService.findAll();
        model.addAttribute("pageImpl", JSON.toJSON(list));
        model.addAttribute(id);
        return "/base/user/add_role";
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @ResponseBody
    public String doAddRoleForUser(@Valid User user) {
        userService.save(user);
        return Constant.SUCCESS;
    }
}
