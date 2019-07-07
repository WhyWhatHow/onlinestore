package com.sdut.onlinestore.service.impl;

import com.github.pagehelper.PageInfo;
import com.sdut.onlinestore.pojo.User;
import com.sdut.onlinestore.service.CategoryService;
import com.sdut.onlinestore.service.ProductService;
import com.sdut.onlinestore.service.UserService;
import com.sdut.onlinestore.utils.Result;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
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
        User u = new User("icon", "aa12321.");
        userService.registerUser(u);
//        u.setUid("ca29ad0705a44b4ebdad965afa69946a");
//        Result result = userService.loginUser(u, request);
//        System.err.println(result.getMessage());
//        Result menu = userService.getMenu(u);
//        for (Object obj :
//                (List) menu.getData()) {
//            System.err.println(obj);
//        }
    }

    @Autowired
    ProductService productService;

    @Test
    void getProduct() {
        Result result = productService.selectByDefault(1, 10);
        for (Object obj :
                ((PageInfo) result.getData()).getList()) {
            System.err.println(obj);

        }

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

        Date date = new Date();
        System.err.println(date.toString());
    }
}