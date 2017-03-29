package com.example.data.contrlor;

import com.example.data.base.controller.BaseControllerImpl;
import com.example.data.common.Constant;
import com.example.data.entity.Article;
import com.example.data.service.IArticleService;
import com.example.data.service.IArticleTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by wanghuiwen on 17-3-11.
 */
@Controller
@RequestMapping(value = "/article")
public class ArticlCtrl extends BaseControllerImpl<Article, String> {
    @Resource
    private IArticleService articleService;
    @Resource
    private IArticleTypeService articleTypeService;

    @Override
    protected String getAddPage() {
        return "/blog/article/add";
    }

    @RequestMapping("/index")
    public String index() {
        return "/blog/index";
    }

    public String list(@RequestParam(defaultValue = "0") int pageNumber, Model model) {
        Page<Article> page = articleService.findAll(getPage(pageNumber));
        model.addAttribute(page);
        return "/blog/article/list";
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public String page(@RequestParam(defaultValue = "0") int pageNumber, Model model) {
        Pageable pageable = new PageRequest(pageNumber, Constant.DEFAULT_PAGE_SIZE);
        Page<Article> page = articleService.findAll(pageable);
        model.addAttribute(page);
        return "/blog/article/list_append";
    }

    @RequestMapping(value = "/details")
    public String articleDetails(@RequestParam(defaultValue = "") String id, Model model) {
        model.addAttribute(articleService.findOne(id));
        return "/blog/article/details";
    }

    @RequestMapping(value = "/serch", method = RequestMethod.POST)
    public String serch(@RequestParam(defaultValue = "0") int pageNumber, Model model,
                        @RequestParam(defaultValue = "") String content) {
        Page<Article> page = articleService.serchByKey(getPage(pageNumber),
                content);
        model.addAttribute(page);
        return "/blog/article/list_append";
    }
}
