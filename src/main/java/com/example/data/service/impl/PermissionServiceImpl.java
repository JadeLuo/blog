package com.example.data.service.impl;

import com.example.data.base.baseservice.BaseServiceImpl;
import com.example.data.common.UtilFun;
import com.example.data.dao.IPermissionDao;
import com.example.data.entity.menu.Permission;
import com.example.data.service.permission.IPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wang on 17-2-14.
 */
@Service("permissionService")
public class PermissionServiceImpl extends BaseServiceImpl<Permission, String> implements IPermissionService {
    @Resource
    private IPermissionDao permissionDao;

    public List<Permission> findByName(String name) {
        List<Permission> list = permissionDao.findByName(name);
        if (UtilFun.isEmptyList(list)) {
            return list;
        }
        return null;
    }
}
