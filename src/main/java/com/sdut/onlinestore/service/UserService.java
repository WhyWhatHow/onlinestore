package com.sdut.onlinestore.service;

import com.sdut.onlinestore.pojo.User;
import com.sdut.onlinestore.utils.Result;

public interface UserService {

    Result registerUser(User user);

    Result loginUser(User user);

    Result updateUser(User user);
}
