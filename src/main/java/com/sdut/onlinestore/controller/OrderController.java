package com.sdut.onlinestore.controller;

import com.github.pagehelper.PageInfo;
import com.sdut.onlinestore.pojo.Order;
import com.sdut.onlinestore.service.OrderService;
import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.CartVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "订单的相关接口")
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService service;

    @ApiOperation(value = "添加订单", notes = " 这个方法可能有错误,需要测试的仔细点 ", response = Integer.class)
    @PostMapping("/add")
    public Result saveOrder(@RequestBody CartVo vo) {
        return service.saveOrder(vo);
    }

    @ApiOperation(value = "分页生成订单页,订单内容包括订单项内容" ,notes ="PageInfo<Order>.class " ,response = PageInfo.class)
    @GetMapping("/all")
    public Result selectByPage(HttpServletRequest req) {
        return service.selectByPage(req);
    }

    @ApiOperation(value = "根据订单编号获取订单详情",response = Order.class)
    @GetMapping("/detail")
    public Result getDetail(String oid) {
        return service.getDetail(oid);
    }
    // 0 生成订单,(未确定收货地址) 1. 确认订单(未付款)  2. 付款未发货,  3. 发货已收到
    @ApiOperation(value = "用户付款修改订单状态, 后期可能修改该方法" )
    @GetMapping("/pay")
    public Result userPay(String oid) {
        return service.userPay(oid);
    }
}
