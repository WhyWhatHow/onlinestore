package com.sdut.onlinestore.service;

import com.sdut.onlinestore.pojo.Category;
import com.sdut.onlinestore.pojo.Product;
import com.sdut.onlinestore.utils.Result;

public interface ProductService {

    Result selectByDefault(Integer start, Integer rows);

    Result updateProduct(Product product);

    Result insertProduct(Product product, Category category);
}
