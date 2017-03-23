package com.example.data.ctrl;

import com.example.data.dao.UserDao;
import com.example.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/23.
 */
@Controller
public class Ctrl {
    @Resource
    private UserDao userDao;

    @RequestMapping("/show")
    @ResponseBody
    public User save() {
        User u = new User();
        u.setRealName("success");
        u.setUserName("success");
        u.setPassWord("123456");
        u.seteMail("1@1");
        userDao.save(u);
        User list = userDao.findOne("402882e85af9833b015af9835a020000");
        List<Map<String, Object>> list1 = userDao.mapByWhere("select * from t_user", "");
        userDao.listAll();
        userDao.listByWhere("where id = ?", "402882e85af9833b015af9835a020000");
        userDao.count();
        Pageable pageable = new PageRequest(0, 5);
        Page<User> page = userDao.PageAll(pageable);
        page = userDao.PageByWhere(pageable, "where user_name = ? ", "success");
        return list;
    }
}
