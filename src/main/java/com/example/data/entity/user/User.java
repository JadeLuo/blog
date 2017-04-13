package com.example.data.entity.user;


import com.example.data.entity.baseEntity.BaseModel;
import com.example.data.entity.role.Role;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by wanghuiwen on 17-1-5.
 * 用户实体
 */
@Entity
@Table(name = "t_user")
public class User extends BaseModel {
    public static final int USER_LOCK = 2;
    public static final int USER_NORMAL = 2;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @NotBlank(message = "用户名不能为空")
    private String userName;
    @Length(min = 6, message = "密码长度不能小于6位")
    private String passWord;
    /**
     * 账户状态
     */
    private int state;
    private String realName;

    @NotBlank(message = "邮箱不能为空")
    private String eMail;
    @Length(max = 11, min = 11, message = "请输入真确的格式")
    private String phone;
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE})//立即从数据库中进行加载数据;
    @JoinTable(name = "UserRole", joinColumns = {@JoinColumn(name = "userId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private Set<Role> roleList;// 一个用户具有多个角色

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Role> getRoleList () {

        return roleList;
    }

    public void setRoleList (Set<Role> roleList) {

        this.roleList = roleList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 密码加盐
     *
     * @return 加盐后的密码
     */
    public String getSalting() {
        return new Md5Hash(this.getPassWord() + getUserName()).toString();
    }
}
