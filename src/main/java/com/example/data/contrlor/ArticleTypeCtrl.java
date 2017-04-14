package com.example.data.contrlor;

import com.example.data.base.controller.BaseControllerImpl;
import com.example.data.entity.ArticleType;
import com.example.data.service.IArticleTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */
@Controller
@RequestMapping(value = "/article/type")
public class ArticleTypeCtrl extends BaseControllerImpl<ArticleType, Long> {
    @Resource
    private IArticleTypeService articleTypeService;

    @Override
    protected String setAddPage () {
        return "/blog/article_type/add";
    }

    @Override
    public void setAddPara (Model model) {

        List<ArticleType> list = articleTypeService.listByUser (getSessionUser ().getId ());
        model.addAttribute ("type",list);
        model.addAttribute ("userId",getSessionUser ().getId ());
    }

    @RequestMapping(value = "/list")
    public String list (Model model) {

        List<ArticleType> list = articleTypeService.listByUser (getSessionUser ().getId ());
        model.addAttribute ("type",list);
        return "/blog/article_type/list";

    }

    @RequestMapping(value = "/select")
    public String select (Model model) {
        List<ArticleType> list = articleTypeService.listByUser (getSessionUser ().getId ());
        model.addAttribute ("articleType",list);
        return "/blog/article_type/select";

    }


}
