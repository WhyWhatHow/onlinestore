package com.sdut.onlinestore.controller.impl;

import com.sdut.onlinestore.mapper.UserMapper;
import com.sdut.onlinestore.pojo.User;
import com.sdut.onlinestore.service.UserService;
import com.sdut.onlinestore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        try {
            res = mapper.insert(user);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server Problem!! -- reigister user ");
            return result;
        }
        // TODO 账户激活

        result.setSuccess(true);
        result.setMessage("用户注册成功....");
        result.setCode(202);
        result.setData(1);
        return result;
    }

    @Override
    public Result loginUser(User user) {
//        return null;
        Result result = new Result() ;
        result.setSuccess(false);
        User user1 = null;
        try{
             user1 = mapper.selectByPrimaryKey(user.getUid());
        }catch (Exception e){
            result.setCode(500);
            result.setMessage("Server Problem!! -- Login user ");
            return result;
        }
        // 加密 hashcode

        return result;
    }

    @Override
    public Result updateUser(User user) {
        return null;
    }
}
