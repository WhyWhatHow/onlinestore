package com.sdut.onlinestore.service;


import com.sdut.onlinestore.pojo.Menu;
import com.sdut.onlinestore.pojo.Role;

import java.util.List;

public interface  MenuService {
    // 根据rid 返回 permission 集合
    List<Menu> getMenuByRole(Role role);

    int insertMenu(Menu menu);
}
