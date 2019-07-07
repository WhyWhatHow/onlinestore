package com.sdut.onlinestore.service.impl;

import com.sdut.onlinestore.mapper.MenuMapper;
import com.sdut.onlinestore.mapper.UserMapper;
import com.sdut.onlinestore.pojo.Menu;
import com.sdut.onlinestore.pojo.Role;
import com.sdut.onlinestore.pojo.User;
import com.sdut.onlinestore.service.MailService;
import com.sdut.onlinestore.service.UserService;
import com.sdut.onlinestore.utils.MD5Util;
import com.sdut.onlinestore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    @Autowired
    MailService mailService;

    @Override
    @Transactional
    public Result registerUser(User user) {
        Result result = new Result();
        result.setSuccess(false);
        user.setUid(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        int res = 0;
        // 添加用户
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setState(true);
        user.setRid(3);//  默认角色

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
//    @Cacheable(value = "userzzz")
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
                request.getSession().setAttribute("user", user1);
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
            user.setUpdateTime(LocalDateTime.now());
            mapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server Problem,--update user");
            return result;
        }
//        result.setData(user);
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
            rid = user.getRid();
//            rid = mapper.selectByUserToRid(user);
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
        System.err.println("=---------------------------------------=");
        for (Menu menu : list) {
            System.err.println(menu);

        }
        System.err.println("=---------------------------------------=");
        result.setData(childrens);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("成功获取菜单数据");
        return result;

    }

    private static String subject = "账号激活码";

    @Override
    public Result sendACtiveCodeToUser(User user) {

        Result result = new Result();
        String code = "";
        user.setUid(MD5Util.setUUID());
        code = MD5Util.setUUID() + MD5Util.setUUID();

        String content = "<h3> 激活码: <a href ='#'>" + code + "</a> </h3>";
        result.setSuccess(false);
        int res = 0 ;
        try{
             res = mapper.insert(user);
        }catch(Exception e){
           result.setCode(500);
           result.setMessage("Server's problem,  --");
           return result;
        }
        int mail = 0 ;
        try {
            mail  = mailService.sendHtmlMail(user.getEmail(), subject, content);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- send code to user ");
            return result;
        }
        result.setCode(202);
        if(mail ==1 && res ==1 ){
            result.setSuccess(true);
            result.setData(user);
            result.setMessage("Success in sending user active code, please enter your email to check");
        }else{
            result.setMessage("bad in send email or generator active code !");
        }
        return result;

    }

    public ArrayList<Menu> getChildrens(Integer parentId, List<Menu> list) {
        ArrayList<Menu> arrayList = new ArrayList<>(100);
        for (Menu menu : list) {
            if (parentId == menu.getParentid()) {
                menu.setChildrenList(getChildrens(menu.getId(), list));
                arrayList.add(menu);
            }
        }
        return arrayList;
    }

}
