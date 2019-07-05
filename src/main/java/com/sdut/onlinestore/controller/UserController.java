package com.sdut.onlinestore.controller;

import com.sdut.onlinestore.pojo.User;
import com.sdut.onlinestore.service.UserService;
import com.sdut.onlinestore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Service;

@RestController
@RequestMapping("/user")
public class UserController {
    // 登录, 注册, 激活, 修改
    @Autowired
    UserService service;

    @Autowired
    RedisTemplate redisTemplate;
    @RequestMapping("/hello")
    public User  hello(){

        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        User userzzz = operations.get("userzzz");
        return userzzz;
    }

    @RequestMapping("/update")
    public Result updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO: 获取用户信息, 暂时感觉没有什么用处
     * 前端:
     * 后端:
     * @Param [user]
     **/
//    @RequestMapping("/get")
//    public Result selectUser(@RequestBody User user) {
//        return ;
//    }

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO: 用户登录
     * 前端:
     * 后端:
     * @Param [user]
     **/
    @RequestMapping("/login")
    public Result loginUser(@RequestBody User user, HttpServletRequest request) {
        return service.loginUser(user,request);
    }

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO:
     * 前端: 前端需要实现表单校验,去空字符
     * 后端: 默认数据有效,非空,"",null去空格
     * @Param [user]
     **/
    @RequestMapping("/reg")
    public Result registerUser(@RequestBody User user) {
        return service.registerUser(user);
    }

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO:
     * 前端:
     * 后端:
     * @Param [user]
     **/
    @RequestMapping("/active")
    public Result activeUser(@RequestBody User user) {
        return null;
    }

    /**
     * @Author whywhathow
     * TODO:
     * 前端: 提供当前的登录用户,信息 不准给我少uid (登录成功后,我把uid 存进httpsession里去了,LoginUser方法中我也把登录的用户存进result.data里
     * 后端:  后台系统用户菜单的项的获取, {[{}]} 形式, 递归的性实现
     * @Param [user]
     * @return com.sdut.onlinestore.utils.Result
     **/
    @RequestMapping("/role")
    public Result getRole(@RequestBody User user) {
        return service.getMenu(user);
    }
}
