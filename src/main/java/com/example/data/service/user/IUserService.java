package com.example.data.service.user;


import com.example.data.base.baseservice.IBaseService;
import com.example.data.entity.user.User;

/**
 * Created by wang on 17-2-8.
 * 接口类
 */
public interface IUserService extends IBaseService<User, String> {

    /**
     * 登陆方法
     *
     * @param user
     * @return
     */
    boolean login(User user);

    /**
     * 根据用户名查找
     *
     * @return
     */
    User getByuserName(String userName);

    /**
     * 保存
     */
    User save(User user);

}
