package com.example.data.dao;

import com.example.data.base.BaseRepository;
import com.example.data.entity.role.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wang on 17-2-14.
 * 角色
 */
@Repository("roleDao")
public interface IRoleDao extends BaseRepository<Role, String> {
    List<Role> getByName(String name);
}
