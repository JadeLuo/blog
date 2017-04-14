package com.example.data.service.role;


import com.example.data.base.baseservice.IBaseService;
import com.example.data.entity.role.Role;

import java.util.List;

/**
 * Created by wanghuiwen on 17-2-13.
 * 角色服务类
 */
public interface IRoleService extends IBaseService<Role, String> {
    Role getByname(String name);
}
