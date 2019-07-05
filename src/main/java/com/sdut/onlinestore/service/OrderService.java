package com.sdut.onlinestore.service;

import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.CartVo;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {
    Result saveOrder(CartVo vo);

    Result selectByPage(HttpServletRequest req);

    Result getDetail(String oid);

    Result userPay(String oid);
}
