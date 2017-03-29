package com.example.data.service.impl.user;

import com.example.data.base.baseservice.BaseServiceImpl;
import com.example.data.dao.UserDao;
import com.example.data.entity.user.User;
import com.example.data.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanghuiwen on 17-1-6.
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, String> implements IUserService {
    @Autowired
    private UserDao userDao;

    /**
     * 登陆成功返回true
     *
     * @param user
     * @return
     */
    public boolean login(User user) {
        User user1 = userDao.getByuserName(user.getUserName());
        if (user1 != null) {
            if (user1.getPassWord().equals(user.getSalting())) {
                return true;
            }
        }
        return false;
    }

    public User getByuserName(String name) {
        User user = userDao.getByuserName(name);
        return user;
    }

    @Override
    public User save(User user) {
        user.setPassWord(user.getSalting());
        return userDao.save(user);
    }
}
