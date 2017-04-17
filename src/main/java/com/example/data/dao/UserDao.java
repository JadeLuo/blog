package com.example.data.dao;

import com.example.data.base.BaseRepository;
import com.example.data.entity.user.User;
import org.springframework.stereotype.Repository;

/**
 * Created by wang on 17-2-7.
 */
@Repository("userDao")
public interface UserDao extends BaseRepository<User, String> {

    User findFirstByuserName (String userName);
    User findFirstByeMail(String eMail);
}
