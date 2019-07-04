package com.sdut.onlinestore.service.impl;

import com.sdut.onlinestore.pojo.Menu;
import com.sdut.onlinestore.pojo.Role;
import com.sdut.onlinestore.pojo.User;
import com.sdut.onlinestore.service.CategoryService;
import com.sdut.onlinestore.service.RoleMenuService;
import com.sdut.onlinestore.service.UserService;
import com.sdut.onlinestore.utils.Result;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    CategoryService service;

    @Autowired
    UserService userService;

    @Autowired
    RoleMenuService roleMenuService;
    @Test
    void getAllMenu(){
        Result result=roleMenuService.getAllMenu();
        System.out.println("getAll"+result);
//
//        Menu menu = new Menu();
//        menu.setName("rye");
//        menu.setParentid(2);
//        menu.setUri("ryeurl");
//        menu.setIs_deleted(false);
//        Result result1=roleMenuService.addMenu(menu);
//        System.out.println("addMenu"+result1);


//        Role role = new Role();
//        role.setRname("ryerole");
//        role.setIs_deleted(false);
//        List<Menu> list=new ArrayList<Menu>();
//        list.add(new Menu(1));
//        role.setRid(7);
//        Result result2=roleMenuService.addRole(role, list);
//        System.out.println("addRole"+result2);


//        Menu menu = new Menu();
//        menu.setId(29);
//        Result result3=roleMenuService.delMenu(menu);
//        System.out.println(result3);
//
//        Role role=new Role();
//        role.setRid(1);
//        Result result4=roleMenuService.delRole(role);
//        System.out.println("delRole"+result4);
//        Map<Integer, Menu> menus = ((ArrayList<Menu>)result.getData()).stream().collect(
//                Collectors.toMap(Menu::getId, (p) -> p));
//        System.out.println("getAll"+result);
//        System.out.println("addMenu"+result1);
//        System.out.println("addRole"+result2);
//        System.out.println("delMenu"+result3);
//        System.out.println("delRole"+result4);

    }

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