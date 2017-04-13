package com.example.data.base.controller.role;

import com.alibaba.fastjson.JSON;
import com.example.data.base.controller.BaseControllerImpl;
import com.example.data.base.ui.ListParam;
import com.example.data.common.UtilFun;
import com.example.data.entity.menu.Permission;
import com.example.data.entity.role.Role;
import com.example.data.service.permission.IPermissionService;
import com.example.data.service.role.IRoleService;
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
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/4/10.
 */
@Controller
@RequestMapping("/base/role")
public class RoleCtrl extends BaseControllerImpl<Role,String> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private IRoleService roleService;

    @Resource
    private IPermissionService permissionService;

    @RequestMapping("/list")
    public String list (@RequestParam(defaultValue = "0") int pageNumber,Model model,@RequestParam(defaultValue = "") String name) {

        LinkedHashMap<String,Object> where = new LinkedHashMap<String,Object> ();
        if (UtilFun.isEmptyString (name)) where.put (" and name = ?",name);
        Page<Role> page = roleService.PageByWhere (getPage (pageNumber),where);
        model.addAttribute (page);
        ListParam listParam = new ListParam ();
        listParam.setTitle ("角色管理");
        listParam.setBaseurl ("/base/role");
        model.addAttribute (listParam);
        return "/base/role/list";
    }

    /**
     * 为角色添加权限跳转
     */
    @RequestMapping(value = "/addPermission", method = RequestMethod.POST)
    public String addPermission (HttpServletRequest request,Model model) {

        String role1 = request.getParameter ("role");
        Role role = roleService.findOne (role1);
        Iterable<Permission> list = permissionService.findAll ();
        model.addAttribute ("apermission",list);
        model.addAttribute ("permissionJSON",JSON.toJSONString (list));
        model.addAttribute ("arole",role);
        return "/base/role/add_per";
    }

    /**
     * 为角色添加权限
     */
    @RequestMapping(value = "/addPermissionForRole", method = RequestMethod.POST)
    @ResponseBody
    public void addPermission (Role role) {

        roleService.save (role);
    }

}
