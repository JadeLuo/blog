package com.example.data.service.impl.role;

import com.example.data.base.baseservice.BaseServiceImpl;
import com.example.data.dao.IRoleDao;
import com.example.data.entity.role.Role;
import com.example.data.service.role.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wang on 17-2-14.
 */
@Service("roleService")
public class RoleService extends BaseServiceImpl<Role, String> implements IRoleService {
    @Resource
    private IRoleDao roleDao;

    public Role getByname(String name) {
        Role roles = roleDao.getFirstByName(name);
        if (roles != null) {
            return roles;
        }
        return null;
    }
}
