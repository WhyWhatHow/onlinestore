package com.sdut.onlinestore.service.impl;

import com.sdut.onlinestore.mapper.MenuMapper;
import com.sdut.onlinestore.mapper.UserMapper;
import com.sdut.onlinestore.pojo.Menu;
import com.sdut.onlinestore.pojo.Role;
import com.sdut.onlinestore.pojo.User;
import com.sdut.onlinestore.service.UserService;
import com.sdut.onlinestore.utils.MD5Util;
import com.sdut.onlinestore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    @Override
    @Transactional
    public Result registerUser(User user) {
        Result result = new Result();
        result.setSuccess(false);
        user.setUid(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        int res = 0;
        // 添加用户
        user.setCreateTime(new Date());
        // TODO  密码加密
        String password = null;
        try {
            password = MD5Util.getEncryption(user.getPassword());

        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
            result.setCode(500);
            result.setMessage("Server Problem!! -- reigister user encryption ");
            return result;
        }

        user.setPassword(password);

        try {
            res = mapper.insert(user);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server Problem!! -- reigister user ");
            return result;
        }
        // TODO 账户激活 ,或者放到另外一个请求中

        result.setSuccess(true);
        result.setMessage("用户注册成功....");
        result.setCode(202);
        result.setData(1);
        return result;
    }

    @Override
    public Result loginUser(User user, HttpServletRequest request) {
//        return null;
        Result result = new Result();
        result.setSuccess(false);
        //  加密登录密码, 加密 md5
        String encryption = "";
        try {
            encryption = MD5Util.getEncryption(user.getPassword());
        } catch (Exception e) {
//            e.printStackTrace();
            result.setCode(500);
            result.setMessage("Server Problem!! -- Login user ,encryption");
            return result;
        }

        // 根据用户名获取用户
        User user1 = null;
        try {
            user1 = mapper.selectByUsername(user.getUsername());
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server Problem!! -- Login user ");
            return result;
        }
        result.setCode(202);
        if (user1 != null) {
            if (encryption.equals(user1.getPassword())) {
                result.setSuccess(true);
                result.setData(user1);
                request.getSession().setAttribute("uid", user1.getUid());
                result.setMessage("Login In Success");

            } else {
                result.setMessage("password or username is wrong!! ");

            }
        } else {
            result.setMessage("userName is wrong");
        }
        return result;
    }

    @Override
    public Result updateUser(User user) {
        Result result = new Result();
        result.setSuccess(false);
        // 根据用户uid 修改信息, if 动态sql 所以好一点
        try {
            user.setUpdateTime(new Date());
            mapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server Problem,--update user");
            return result;
        }
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("update user success");
        return result;

    }

    @Autowired
    MenuMapper menuMapper;

    @Transactional
    @Override
    public Result getMenu(User user) {
        Result result = new Result();
        result.setSuccess(false);
        Integer rid = null;
        // !.获取用户对应的角色
        try {
            rid = mapper.selectByUserToRid(user);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- get rid false in getMenuMethod");
            return result;
        }
        Role role = new Role(rid);
        // 获取 该角色对应的权限(所谓的菜单)
        List<Menu> list = null;
        try {
            list = menuMapper.selectByRole(role);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- get menu list false  in getMenu menthod");
            return result;
        }

        // 处理菜单 , 注意 角色必须对应一个 根菜单
        ArrayList<Menu> childrens = new ArrayList<>(50);
        try {
            childrens = getChildrens(0, list);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
        result.setData(childrens);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("成功获取菜单数据");
        return result;

    }

    public ArrayList<Menu> getChildrens(Integer parentId, List<Menu> list) {
        ArrayList<Menu> arrayList = new ArrayList<>(100);
        for (Menu cat : list) {
            if (parentId == cat.getParentid()) {
                cat.setChildrenList(getChildrens(cat.getId(), list));
                arrayList.add(cat);
            }
        }
        return arrayList;
    }

}
