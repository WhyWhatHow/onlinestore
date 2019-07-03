package com.sdut.onlinestore.service;

import com.sdut.onlinestore.pojo.User;
import com.sdut.onlinestore.utils.Result;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    Result registerUser(User user);

    Result loginUser(User user, HttpServletRequest request);

    Result updateUser(User user);

    Result getMenu(User user);
}
