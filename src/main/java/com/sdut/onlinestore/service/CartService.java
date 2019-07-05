package com.sdut.onlinestore.service;


import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.CartVo;
import com.sdut.onlinestore.vo.ProductVo;

import javax.servlet.http.HttpServletRequest;

public interface CartService {
    Result addToCart(ProductVo vo);

    Result delById(String pid);

    Result selectAll(HttpServletRequest req);
}
