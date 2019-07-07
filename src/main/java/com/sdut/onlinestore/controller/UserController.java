package com.sdut.onlinestore.controller;

import com.sdut.onlinestore.pojo.Menu;
import com.sdut.onlinestore.pojo.User;
import com.sdut.onlinestore.service.UserService;
import com.sdut.onlinestore.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;

@Api(value = "用户的接口,pass")
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    // 登录, 注册, 激活, 修改
    @Autowired
    UserService service;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/hello")
    public void  hello( @RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
//        if( 1 ){
//
//        }else {
//
//        }
//        return new User("123456", "123456");
        //
//        ValueOperations<String, User> operations = redisTemplate.opsForValue();
//        User userzzz = operations.get("userzzz");
//        return userzzz;
    }

    @ApiOperation(value = "修改用户信息", notes = "提交数据为表单,封装的user对象", response = User.class)

    @PostMapping("/update")
    public Result updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }



    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO: 用户登录
     * 前端:
     * 后端:
     * @Param [user]
     **/
    @ApiOperation(value = "用户登录", notes = "用户登录成功后,用户会被存在session中,result.data中我也有给你们返回 测试数据;{\"username\":\"nash\",\n" +
            "\"password\":\"aa12321.\"}")
    @PostMapping("/login")
    public Result loginUser(@RequestBody User user, HttpServletRequest request) {
        return service.loginUser(user, request);
    }

//    @ApiOperation("发送激活码给用户")
//    @PostMapping("/send")
//    public Result sendToActiveUser(@RequestBody User user) {
//        return service.sendACtiveCodeToUser(user);
//    }

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO:
     * 前端: 前端需要实现表单校验,去空字符
     * 后端: 默认数据有效,非空,"",null去空格
     * @Param [user]
     **/
    @ApiOperation(value = "用户注册(pass)")
    @PostMapping("/reg")
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
//    @RequestMapping("/active")
//    public Result activeUser(@RequestBody User user) {
//        return null;
//    }

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO:
     * 前端: 提供当前的登录用户,信息 不准给我少uid (登录成功后,我把uid 存进httpsession里去了,LoginUser方法中我也把登录的用户存进result.data里
     * 后端:  后台系统用户菜单的项的获取, {[{}]} 形式, 递归的性实现
     * @Param [user]
     **/
    @ApiOperation(value = "获取用户的权限- pass", notes = "{\"username\":\"nash\",\n" +
            "\"password\":\"aa12321.\",\n" +
            "\"rid\":1\n" +
            "}", response = Menu.class)
//    @ApiOperation("")
    @PostMapping("/role")
    public Result getRole(@RequestBody User user) {
        return service.getMenu(user);
    }
}
