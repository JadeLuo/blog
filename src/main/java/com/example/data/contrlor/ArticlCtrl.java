package com.example.data.contrlor;

import com.example.data.base.controller.BaseControllerImpl;
import com.example.data.common.UtilFun;
import com.example.data.entity.Article;
import com.example.data.entity.ArticleType;
import com.example.data.entity.user.User;
import com.example.data.service.IArticleService;
import com.example.data.service.IArticleTypeService;
import com.example.data.service.ICommentService;
import com.example.data.service.user.IUserService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

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


    @Resource
    private IUserService userService;

    @Resource
    private CommentCtrl commentCtrl;

    @Override
    protected String setAddPage () {
        return "/blog/article/add";
    }

    @Override
    protected void setAddAttr (Article article,HttpServletRequest request) {
        article.setArticleDate (new Date ());
    }

    @Override
    protected void setAddPara (Model model) {

        List<ArticleType> list = articleTypeService.listByUser (getSessionUser ().getId ());
        model.addAttribute ("articleType",list);
    }


    @RequestMapping("/index")
    public String index() {
        return "/blog/index";
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list (@RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "") User user,Model model) {

        LinkedHashMap<String,Object> sql = new LinkedHashMap<String,Object> ();

        sql.put ("and user_id = ? ",user.getId ());

        Page<Article> page = articleService.PageByWhere (getPage (pageNumber),sql);

        List<ArticleType> list = articleTypeService.listByUser (user.getId ());

        model.addAttribute ("articleType",list);
        if (page != null) model.addAttribute (page);
        if (pageNumber > 0) {
            return "/blog/article/list_append";
        }
        return "/blog/article/list";
    }

    @RequestMapping(value = "/details")
    public String articleDetails (@RequestParam(defaultValue = "") Article id,Model model) {

        model.addAttribute (id);

        User user = userService.findOne (id.getUserId ());

        List<ArticleType> list = articleTypeService.listByUser (id.getUserId ());

        model.addAttribute ("articleType",list);

        commentCtrl.ListByArticleId (id.getId (),model);

        model.addAttribute (user);

        return "/blog/article/details";
    }

    @RequestMapping(value = "/serch", method = RequestMethod.GET)
    public String serch(@RequestParam(defaultValue = "0") int pageNumber, Model model,
                        @RequestParam(defaultValue = "") String content) {

        Page<Article> page = articleService.serchByKey (getPage (pageNumber),content);

        model.addAttribute(page);
        return "/blog/article/list_append";
    }


    @RequestMapping(value = "/articleByType")
    public String articleByType (@RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "") ArticleType typeId,@RequestParam(defaultValue = "") User user,Model model) {

        LinkedHashMap<String,Object> sql = new LinkedHashMap<String,Object> ();

        if (typeId == null) {
            sql.put (" and type = '' and user_id = ?",user.getId ());
        } else if (typeId.getId () != 0) {
            sql.put ("and type = ?",typeId.getId ());
        }

        Page<Article> page = articleService.PageByWhere (getPage (pageNumber),sql);

        List<ArticleType> list = articleTypeService.listByUser (user != null ? user.getId () :typeId.getUserId ());

        model.addAttribute ("articleType",list);

        model.addAttribute (page);

        return "/blog/article/list";
    }

    /**
     * 喜欢 不喜欢
     */
    @RequestMapping(value = "/trial")
    @ResponseBody
    public int trial (@RequestParam() Article article,@RequestParam() int trial) {

        if (trial == 1) {
            article.setEnjoy (article.getEnjoy () + 1);
            articleService.save (article);
            return article.getEnjoy ();
        } else {
            article.setHate (article.getHate () + 1);
            articleService.save (article);
            return article.getHate ();
        }

    }

}
