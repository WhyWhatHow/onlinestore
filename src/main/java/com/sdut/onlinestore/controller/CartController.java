package com.sdut.onlinestore.controller;

import com.sdut.onlinestore.service.CartService;
import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.CartVo;
import com.sdut.onlinestore.vo.ProductVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService service;

    @ApiOperation(value = "将商品添加到购物车   ", notes = " 根据商品id 添加到购物车,ProductVo 必须包括product的全部数据,以及该商品的数量")
    @ApiImplicitParam(dataType = "ProductVo", name = "ProductVo", value = "将商品添加到购物车")
    @RequestMapping("/add")
    public Result addToCart(@RequestBody ProductVo vo) {
        return service.addToCart(vo);
    }

    @RequestMapping("/all")
    public Result selectAll( HttpServletRequest req) {
        return service.selectAll( req);
    }

    @RequestMapping("/del")
    public Result delById(String pid) {
        return service.delById(pid);
    }

}
