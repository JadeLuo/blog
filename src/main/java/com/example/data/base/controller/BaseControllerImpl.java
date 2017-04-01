package com.example.data.base.controller;

import com.example.data.base.baseservice.IBaseService;
import com.example.data.common.Constant;
import com.example.data.common.UtilFun;
import com.example.data.entity.user.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Administrator on 2017/3/3.
 * Controller 模板
 */
public abstract class BaseControllerImpl<M, PK extends Serializable> {

    protected IBaseService<M,PK> baseService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected void setBaseDao (IBaseService<M,PK> baseService) {

        this.baseService = baseService;
    }

    protected PageRequest getPage(int page) {
        return new PageRequest(page, Constant.DEFAULT_PAGE_SIZE);
    }

    protected User getSessionUser() {
        Subject subject = SecurityUtils.getSubject();
        return (User) subject.getSession().getAttribute("user");
    }

    protected Session getSession() {
        Subject subject = SecurityUtils.getSubject();
        return subject.getSession();
    }

    protected void setAddPara (Model model) {}

    protected void setAddAttr (M m) {}

    protected String setAddPage () {
        return this.getClass().getAnnotation(RequestMapping.class).value()[0] + "/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    protected String save(@RequestParam(defaultValue = "") PK id, Model model) {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<M> clazz = (Class<M>) pt.getActualTypeArguments()[0];
        try {
            M m = clazz.newInstance();

            model.addAttribute (id != null && baseService.findOne (id) != null ? baseService.findOne (id) :m);

            setAddPara (model);

            return setAddPage ();

        } catch (InstantiationException e) {
            e.printStackTrace();
            return "/error";
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "/error";
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    protected String save(@Valid M m, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                return result.getAllErrors().get(0).getDefaultMessage();
            }
            setAddAttr (m);
            baseService.save (m);
            return Constant.RECEIPT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constant.RECEIPT_ERROR;
        }
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    protected String delete(@RequestParam(defaultValue = "") PK id) {
        try {
            if (UtilFun.isEmptyString(id.toString())) {
                baseService.delete (id);
                return Constant.RECEIPT_SUCCESS;
            } else {
                return Constant.RECEIPT_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Constant.RECEIPT_ERROR;
        }
    }
}
