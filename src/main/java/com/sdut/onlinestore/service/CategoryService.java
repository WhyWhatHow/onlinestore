package com.sdut.onlinestore.service;

import com.sdut.onlinestore.pojo.Category;
import com.sdut.onlinestore.utils.Result;

public interface CategoryService {

    Result getAll();

    Result insertCategory(Category category);

    Result updateCategory(Category category);
}
