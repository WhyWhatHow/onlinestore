package com.sdut.onlinestore.controller;

import com.sdut.onlinestore.pojo.Menu;
import com.sdut.onlinestore.pojo.Role;
import com.sdut.onlinestore.service.RoleMenuService;
import com.sdut.onlinestore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/roleMenu")
@CrossOrigin
@RestController
public class RoleMenuController {
    @Autowired
    RoleMenuService service;

    /**
     * 前端不需要传参
     * 获取所有的权限（分类项）
     * @return
     */
    @RequestMapping("/getAllMenu")
    public Result getAllMenu(){
        return service.getAllMenu();
    }

    /**
     * 前端传入角色和权限（分类项）对象列表
     * 返回是否插入成功
     * @param role
     * @param
     * @return
     */
    @RequestMapping("/addRole")
    public Result addRole(@RequestBody Role role, @RequestBody List<Menu> menuList){
        return service.addRole(role,menuList);
    }

    /**
     * 前端传入要删除的role对象
     * 返回是否删除成功
     * @param role
     * @return
     */
    @RequestMapping("/delRole")
    public Result delRole(@RequestBody Role role){
        return service.delRole(role);
    }

    /**
     * 前端传入要铲除的menu对象
     * 放回是否删除成功
     * @param menu
     * @return
     */
    @RequestMapping("/delMenu")
    public Result delMenu(@RequestBody Menu menu){
        return service.delMenu(menu);
    }




    /***
     * 前端传入menu对象
     * 返回是否添加成功
     * @param menu
     * @return
     */
    @RequestMapping("/addMenu")
    public Result addMenu(@RequestBody Menu menu){
        return service.addMenu(menu);
    }



}
