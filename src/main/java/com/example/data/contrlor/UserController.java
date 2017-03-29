package com.example.data.contrlor;

import com.alibaba.fastjson.JSON;
import com.example.data.base.controller.BaseControllerImpl;
import com.example.data.common.Constant;
import com.example.data.entity.baseEntity.ListParam;
import com.example.data.entity.role.Role;
import com.example.data.entity.user.User;
import com.example.data.service.role.IRoleService;
import com.example.data.service.user.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import javax.validation.Valid;

/**
 * Created by wanghuiwen on 17-1-18.
 * 用户操作
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseControllerImpl<User, String> {
    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public String list(@RequestParam(defaultValue = "0") int pageNumber, Model model) {
        ListParam listParam = new ListParam();
        listParam.setTitle("用户管理");
        listParam.setBaseurl("/user");
        model.addAttribute(listParam);
        return "/user/list";
    }

    @RequiresPermissions("user:add")
    @Override
    public String save(@RequestParam(defaultValue = "") String id, Model model) {
        super.save(id, model);
        return "/user/user_add";
    }

    @Override
    public String save(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/user/user_add";
        }
        super.save(user, result, model);
        return "/user/user_add";
    }


    @Override
    @RequiresPermissions("userInfo:delete")
    public String delete(@RequestParam(defaultValue = "") String id) {
        return "";
    }

    /**
     * 为用户添加角色
     */
    @RequestMapping(value = "/addRole", method = RequestMethod.GET)
    public String addRoleForUser(@RequestParam() String id, Model model) {
        Iterable<Role> list = roleService.findAll();
        model.addAttribute("pageImpl", JSON.toJSON(list));
        model.addAttribute(userService.findOne(id));
        return "/user/add_role";
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @ResponseBody
    public String doAddRoleForUser(@Valid User user) {
        userService.save(user);
        return Constant.SUCCESS;
    }
}
