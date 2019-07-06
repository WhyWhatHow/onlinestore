package com.sdut.onlinestore.controller;

import com.sdut.onlinestore.pojo.CartItem;
import com.sdut.onlinestore.service.CartService;
import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.ProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "购物车的相关接口 -- pass")
@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService service;

    @ApiOperation(value = "将商品添加到购物车   ", notes = " uid, product 的全部数据 ", response = Integer.class)
    @PostMapping("/add")
    public Result addToCart(@RequestBody(required = true) ProductVo vo) {
        return service.addToCart(vo);
    }

    @ApiOperation(value = "查询购物车中的所有信息,不分页", notes = "response 返回的对象是数组", response = CartItem.class)
    @GetMapping("/all")
    public Result selectAll(HttpServletRequest req) {
        return service.selectAll(req);
    }

    @ApiOperation(value = "删除购物车项")
    @DeleteMapping("/del/{pid}")
    public Result delById(@PathVariable("pid") String pid) {
        return service.delById(pid);
    }

}
