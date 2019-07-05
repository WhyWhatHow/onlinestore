package com.sdut.onlinestore.controller;

import com.sdut.onlinestore.pojo.CartItem;
import com.sdut.onlinestore.service.CartService;
import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.CartVo;
import com.sdut.onlinestore.vo.ProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "购物车的相关接口")
@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService service;

    @ApiOperation(value = "将商品添加到购物车   ", notes = " 根据商品id 添加到购物车,ProductVo 必须包括product的全部数据,以及该商品的数量", response = Integer.class)
    @ApiImplicitParam(dataType = "ProductVo", name = "ProductVo", value = "将商品添加到购物车")
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
    @DeleteMapping("/del")
    public Result delById(String pid) {
        return service.delById(pid);
    }

}
