package com.sdut.onlinestore.service.impl;

import com.sdut.onlinestore.mapper.MenuMapper;
import com.sdut.onlinestore.pojo.Menu;
import com.sdut.onlinestore.pojo.Role;
import com.sdut.onlinestore.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper mapper ;
    @Override
    public List<Menu> getMenuByRole(Role role) {
        return mapper.selectByRole(role);
    }

    @Override
    public int insertMenu(Menu menu) {
        return 0;
    }
}
