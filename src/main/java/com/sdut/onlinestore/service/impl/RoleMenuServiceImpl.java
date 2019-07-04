package com.sdut.onlinestore.service.impl;

import com.sdut.onlinestore.mapper.MenuMapper;
import com.sdut.onlinestore.mapper.RoleMapper;
import com.sdut.onlinestore.mapper.UserMapper;
import com.sdut.onlinestore.pojo.Menu;
import com.sdut.onlinestore.pojo.Role;
import com.sdut.onlinestore.service.RoleMenuService;
import com.sdut.onlinestore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleMenuServiceImpl implements RoleMenuService{
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    UserMapper userMapper;



    @Override
    public Result addRole(Role role,List<Menu> menuList) {
        // TODO: 2019/7/3 添加role
        Result result = new Result();
        result.setSuccess(true);
        try{
            int i = roleMapper.insertRole(role);
        }catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setCode(500);
            result.setMessage("服务器异常添加角色失败");
        }
        // TODO: 2019/7/3 循环menuList 添加role menu关联
        try{
            for (Menu menu:menuList) {
                int i = roleMapper.insertRoleMenu(role, menu);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setCode(500);
            result.setMessage("服务器异常添加关联失败");
        }

        result.setCode(1);
        result.setMessage("添加用户成功");
        result.setData(1);
        return result;
    }

    @Override
    public Result addMenu(Menu menu) {
        Result result = new Result();
        result.setSuccess(true);
        try{
            int i = menuMapper.insertMenu(menu);
        }catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setCode(500);
            result.setMessage("服务器异常添加权限（分类项）失败");
        }

        result.setCode(1);
        result.setMessage("添加权限成功");
        result.setData(1);
        return result;
    }

    @Override
    public Result getAllMenu() {
        Result result = new Result();
        result.setSuccess(true);
        List<Menu> menuList = null;
        try{
             menuList = menuMapper.selectAllMenu();
        } catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setSuccess(false);
            result.setMessage("服务器异常");
            return result;
        }
        // TODO: 2019/7/3 判断是否有子集
        List<Menu> list = getChild(0,menuList);
        result.setCode(1);
        result.setData(list);
        result.setMessage("查询所有权限（分类项）成功");
        return result;
    }

    @Override
    public Result delMenu(Menu menu) {
        Result result = new Result();
        result.setSuccess(true);
        // TODO: 2019/7/4 删除menu
        try{
            int i = menuMapper.deleteMenu(menu);
        } catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setSuccess(false);
            result.setMessage("服务器异常");
            return result;
        }
        // TODO: 2019/7/4 删除role_menu
        try{
            int i = menuMapper.deleteRoleMenuByMenu(menu);
        } catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setSuccess(false);
            result.setMessage("服务器异常");
            return result;
        }
        result.setCode(1);
        result.setData(1);
        result.setMessage("删除menu成功");
        return result;
    }

    @Override
    public Result delRole(Role role) {
        Result result = new Result();
        result.setSuccess(true);
        // TODO: 2019/7/4 将role表的对应项的删除标记置为 1
        try{
            int i = roleMapper.delelteRole(role);
        } catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setSuccess(false);
            result.setMessage("服务器异常");
            return result;
        }
        // TODO: 2019/7/4 删除role_menu表的关联关系
        try{
            int i = roleMapper.deleteRoleMenuByRole(role);
        } catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setSuccess(false);
            result.setMessage("服务器异常");
            return result;
        }
        // TODO: 2019/7/4 将user表中该role的roleid置为null
        try{
            int i = userMapper.deleteUserRole(role);
        } catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setSuccess(false);
            result.setMessage("服务器异常");
            return result;
        }

        result.setCode(1);
        result.setData(1);
        result.setMessage("删除role成功");
        return null;
    }

    //获取parentId的所有子集
    public List<Menu> getChild(Integer parentId,List<Menu> menus){
        List<Menu> childList= new ArrayList<Menu>();
        for (Menu menu:menus) {
            if(menu.getParentid()==parentId){
                childList.add(menu);
            }
        }
        for (Menu menu:childList){
            menu.setChildrenList((ArrayList<Menu>) getChild(menu.getId(),menus));
        }
        return (ArrayList<Menu>) childList;
    }


}
