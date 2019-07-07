package com.sdut.onlinestore.service;

import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.CartVo;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {
    Result saveOrder(CartVo vo);

    Result selectByPage(HttpServletRequest req, String uid);

    Result getDetail(String oid);


//    Result changeState(String oid, Integer state);

    Result setState(String oid, Integer state);
}
