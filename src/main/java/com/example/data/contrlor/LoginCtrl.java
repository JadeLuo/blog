package com.example.data.contrlor;

import com.example.data.base.controller.BaseControllerImpl;
import com.example.data.common.Constant;
import com.example.data.common.HttpUtil;
import com.example.data.common.UtilFun;
import com.example.data.entity.Code;
import com.example.data.entity.user.User;
import com.example.data.service.IArticleService;
import com.example.data.service.ICodeService;
import com.example.data.service.user.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wanghuiwen on 17-1-12.
 *
 */
@Controller
public class LoginCtrl extends BaseControllerImpl<User, String> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private IUserService userService;

    @Resource
    private IArticleService articleService;

    @Resource
    private ICodeService codeService;
    @Autowired
    private JavaMailSender mailSender;

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

        UsernamePasswordToken token = new UsernamePasswordToken (user.getUserName (),user.getSalting(),rememberMe);
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
            model.addAttribute("errmsg", errmsg);
            return "/login/login";
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute(new User());
        return "/login/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register (@Valid User user,BindingResult result,@RequestParam(defaultValue = "") String code,Model model) {

        if (!ajax (user.getUserName ()).equals (Constant.AJAX_SUCCESS)) {
            result.rejectValue ("userName","1111","用户名已经被使用");
        }
        if(userService.findByeMail (user.geteMail ())!=null){
            result.rejectValue ("eMail","1111","该邮箱已经被注册");
        }
        if (result.hasErrors()) {
            model.addAttribute("loginModel","register");
            return "/login/login";
        }
        Code acode = codeService.findOne (user.geteMail ());
        if (acode == null) {
            model.addAttribute ("loginModel","register");
            model.addAttribute ("code","请先发送验证码");
            return "/login/login";
        }
        if (!acode.getCode ().equals (code)) {
            model.addAttribute ("loginModel","register");
            model.addAttribute ("code","验证码不正确");
            return "/login/login";
        }

        user.setPassWord(user.getSalting());
        codeService.delete (acode);
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
        SecurityUtils.getSubject ().logout ();
        return "redirect:/login";
    }

    @RequestMapping(value = "/admin")
    public String admin (Model model) {
        return "admin/admin";
    }

    @RequestMapping(value = "sendSMS" ,method = RequestMethod.POST)
    @ResponseBody
    public String sendSMS(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        String Url="https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
        String para = "accountSid=d6f289db83a64e4799d48fe5e201145f" +
                "&smsContent=【大神科技】您的验证码为123456，请于2分钟内正确输入，如非本人操作，请忽略此短信。" +
                "&to=18235404640" +
                "&timestamp=" +timestamp+
                "&sig="+DigestUtils.md5Hex("d6f289db83a64e4799d48fe5e201145f"+"a91c4cfc1db145c1b74e52b4a90fd32d"+timestamp)+
                "&respDataType=JSON";
        String res= HttpUtil.sendPost(Url,para);
        return res;
    }
    @RequestMapping(value = "/sendSimpleEmail",method = RequestMethod.POST)
    @ResponseBody
    public String   sendSimpleEmail(@RequestParam(defaultValue = "") String email) {
        if(!UtilFun.isEmptyString (email)) return "邮箱不能为空";
        SimpleMailMessage message = new SimpleMailMessage ();
        int code= (int)((Math.random()*9+1)*100000);
        message.setFrom ("wanghuiwen312@sina.com");//发送者.
        message.setTo (email);//接收者.
        message.setSubject ("验证码");//邮件主题.
        message.setText ("您的验证码是"+code);//邮件内容.
        Code acode = new Code (email,Integer.toString (code));
        codeService.save (acode);

        try {
            mailSender.send (message);//发送邮件
            return Constant.AJAX_SUCCESS;
        } catch (MailException e) {
            return Constant.AJAX_FAIL;
        }
    }

    @RequestMapping(value = "/upPwd", method = RequestMethod.POST)
    public String forgetPassword (@Valid User user,BindingResult result,
                                  @RequestParam String password,
                                  @RequestParam String eMail,
                                  @RequestParam String code,Model model) {

        String forgot_Message = "";
        User auser = userService.findByeMail (eMail);

        if (user == null) {
            model.addAttribute ("loginModel","forgetPassword");
            forgot_Message = "该邮箱还未注册";
            model.addAttribute ("forgot_Message",forgot_Message);
            return "/login/login";
        }

        Code acode = codeService.findOne (eMail);
        if (acode == null) {
            model.addAttribute ("loginModel","forgetPassword");
            forgot_Message = "还未发送验证码";
            model.addAttribute ("forgot_Message",forgot_Message);

            return "/login/login";
        }
        if (!acode.getCode ().equals (code)) {
            model.addAttribute ("loginModel","forgetPassword");
            forgot_Message = "验证码错误";
            model.addAttribute ("forgot_Message",forgot_Message);

            return "/login/login";
        }
        auser.setPassWord (password);
        auser.setPassWord (auser.getSalting ());
        codeService.delete (acode);
        userService.save (auser);
        return "/login/login";
    }

}
