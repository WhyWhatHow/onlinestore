package com.sdut.onlinestore.service;


import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.ProductVo;

import javax.servlet.http.HttpServletRequest;

public interface CartService {
    Result addToCart(ProductVo vo);

    Result delById(Integer id);

    Result selectAll(String uid, HttpServletRequest req);
}
