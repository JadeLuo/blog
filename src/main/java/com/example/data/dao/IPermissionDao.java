package com.example.data.dao;

import com.example.data.base.BaseRepository;
import com.example.data.entity.menu.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wang on 17-2-14.
 * 权限
 */
@Repository("permissionDao")
public interface IPermissionDao extends BaseRepository<Permission, String> {
    List<Permission> findByName(String name);
}
