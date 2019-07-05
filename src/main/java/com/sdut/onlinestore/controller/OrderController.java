package com.sdut.onlinestore.controller;

import com.sdut.onlinestore.service.OrderService;
import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService service;

    @RequestMapping("/add")
    public Result saveOrder(@RequestBody CartVo vo) {
        return service.saveOrder(vo);
    }

    @RequestMapping("/all")
    public Result selectByPage(HttpServletRequest req) {
        return service.selectByPage(req);
    }

    @RequestMapping("/detail")
    public Result getDetail(String oid) {
        return service.getDetail(oid);

    }

    // 0 生成订单,(未确定收货地址) 1. 确认订单(未付款)  2. 付款未发货,  3. 发货已收到

    @RequestMapping("/pay")
    public Result userPay(String oid) {
        return service.userPay(oid);
    }

}
