package com.example.data.contrlor;

import com.example.data.base.controller.BaseControllerImpl;
import com.example.data.entity.ArticleType;
import com.example.data.service.IArticleTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by Administrator on 2017/3/27.
 */
@Controller
@RequestMapping(value = "/article/type")
public class ArticleTypeCtrl extends BaseControllerImpl<ArticleType, Long> {
    @Resource
    private IArticleTypeService articleTypeService;

    @RequestMapping(value = "/save")
    protected String save(@Valid ArticleType articleType, BindingResult result, Model model) {
//        articleType.setUserId(getSessionUser().getId());
        String message = super.save(articleType, result, model);

        return message;

    }

    protected String list(@RequestParam(defaultValue = "0") int pageNumber, Model model) {
        return "";
    }

    @Override
    protected String save(@RequestParam(defaultValue = "") Long id, Model model) {
        super.save(id, model);
//        model.addAttribute("type",articleTypeService.listByUser(getSessionUser().getId()));
        return "/blog/article_type/add";

    }

    @Override
    protected String delete(@RequestParam(defaultValue = "") Long id) {
        return super.delete(id);
    }
}
