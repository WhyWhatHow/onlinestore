package com.sdut.onlinestore.controller.impl;

import com.sdut.onlinestore.mapper.UserMapper;
import com.sdut.onlinestore.pojo.User;
import com.sdut.onlinestore.service.UserService;
import com.sdut.onlinestore.utils.MD5Util;
import com.sdut.onlinestore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;
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
    public Result loginUser(User user) {
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
}
