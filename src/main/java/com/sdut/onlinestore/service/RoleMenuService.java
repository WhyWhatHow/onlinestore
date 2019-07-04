package com.sdut.onlinestore.service;

import com.sdut.onlinestore.pojo.Menu;
import com.sdut.onlinestore.pojo.Role;
import com.sdut.onlinestore.utils.Result;

import java.util.List;

public interface RoleMenuService {
    Result addRole(Role role, List<Menu> menu);

    Result addMenu(Menu menu);

    Result getAllMenu();

    Result delMenu(Menu menu);

    Result delRole(Role role);

}
