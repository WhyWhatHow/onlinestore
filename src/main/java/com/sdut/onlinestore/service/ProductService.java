package com.sdut.onlinestore.service;

import com.sdut.onlinestore.pojo.Product;
import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.CategoryVo;
import com.sdut.onlinestore.vo.ProductVo;

import java.util.List;

public interface ProductService {

    Result selectByDefault(Integer start, Integer rows);

    Result edit(Product product) ;
    Result updateProduct(Product product);

//    Result insertProduct(Product product, Category category);

    Result selectByCategory(CategoryVo category);

    Result deleteByPid(String pid);

    Result insertProduct(Product product);

    Result selectByLike(String pname);

    Result changeListByPidAndState(List<String> list, Boolean state);

    Result selectTolist(ProductVo product);

    Result selectToSideShow();

    Result detailByPid(String pid);
}
