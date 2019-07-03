package com.sdut.onlinestore.service.impl;

import com.sdut.onlinestore.pojo.User;
import com.sdut.onlinestore.service.CategoryService;
import com.sdut.onlinestore.service.UserService;
import com.sdut.onlinestore.utils.Result;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    CategoryService service;

    @Autowired
    UserService userService;

    @Test
    void getMenu() {
        User u = new User("nash", "aa12321.");
//        userService.registerUser(u);
        u.setUid("ca29ad0705a44b4ebdad965afa69946a");
//        Result result = userService.loginUser(u, request);
//        System.err.println(result.getMessage());
//        Result menu = userService.getMenu(u);
//        for (Object obj :
//                (List) menu.getData()) {
//            System.err.println(obj);
//        }
    }

    @Test
    void getAll() {
        Result all = service.getAll();
        System.err.println(all);
        for (Object cat :
                (List) all.getData()) {
            System.out.println(cat);

        }
    }

    @Test
    void getChildrens() {
    }
}