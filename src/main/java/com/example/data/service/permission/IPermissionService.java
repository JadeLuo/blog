package com.example.data.service.permission;


import com.example.data.base.baseservice.IBaseService;
import com.example.data.entity.menu.Permission;

import java.util.List;

/**
 * Created by wanghuiwen on 17-2-13.
 */
public interface IPermissionService extends IBaseService<Permission, String> {
    List<Permission> findByName(String name);
}
