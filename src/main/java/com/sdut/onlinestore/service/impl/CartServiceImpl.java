package com.sdut.onlinestore.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.sdut.onlinestore.mapper.CartItemMapper;
import com.sdut.onlinestore.mapper.ItemMapper;
import com.sdut.onlinestore.mapper.OrderMapper;
import com.sdut.onlinestore.pojo.*;
import com.sdut.onlinestore.service.CartService;
import com.sdut.onlinestore.utils.MD5Util;
import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.pojo.CartItem;
import com.sdut.onlinestore.vo.CartItemVo;
import com.sdut.onlinestore.vo.CartVo;
import com.sdut.onlinestore.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartItemMapper mapper;
    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO: 用户点击商品添加到购物车
     * 前端:
     * 后端:
     * @Param [vo]
     **/
    @Override
    @Transactional
    public Result addToCart(ProductVo vo) {
        // 另外一种做法直接写文件,或者是(未登录)cookie, 当用户登录后将cookie 中内容写进数据库)
        //TODO  逻辑存在问题,需要仔细进行校验
        Result result = new Result();
        result.setSuccess(false);
        int res = 0;
        CartItem cartItem = null;
        // 1. cart表查找是否存在改购物项,若存在,数量加1, 不存在直接添加到数据库
        try {
            cartItem = mapper.selectByPidAndUid(vo.getProduct().getPid(), vo.getUser().getUid());
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- default get cartItem ");
            return result;
        }
        // 购物车中有该商品
        if (cartItem != null) {
            cartItem.setUpdated(new Date());
            cartItem.setNum(cartItem.getNum() + 1);
            int ress = 0;
            try {
                ress = mapper.updateByPrimaryKeyToNum(cartItem);
            } catch (Exception e) {
                result.setCode(500);
                result.setMessage("Server's problem,  --");
                return result;
            }
            result.setCode(202);
            if (ress == 0) {
                result.setMessage("修改商品到购物车失败");
            } else {
                result.setSuccess(true);
                result.setMessage("success in add product 1 to more cartItem");
            }
            return result;
        }
        // 购物车中没有该商品 直接添加
        try {
            cartItem.setCreated(new Date());
            cartItem.setUid(vo.getUser().getUid());
            Product product = vo.getProduct();
            cartItem.setPid(product.getPid());
            cartItem.setPlocation(product.getLocation());
            cartItem.setPname(product.getPname());
//          cartItem.setNum();
            res = mapper.insert(cartItem);

        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- add product to cartItem");
            return result;
        }

        result.setSuccess(true);
        result.setCode(202);
        result.setData(1);
        result.setMessage("Success in add product to cartItem ");
        // redis 持久化处理
        redisTemplate.opsForValue().set("cart", null);
        return result;
    }

    // TODO maybe wrong !!!
    @Override
    public Result selectAll(HttpServletRequest req) {
        // 根据用户的uid 来查找用户尚未结算的订单
        // 判断是否存在缓存
        Result result = (Result) redisTemplate.opsForValue().get("cart");
        if (result != null && result.isSuccess()) {
            result.setMessage(result.getMessage() + " redis.........................");
            return result;
        }
        // 判断用户是否已经登录
        User user = (User) req.getSession().getAttribute("user");
        if (user == null || StringUtils.isEmpty(user.getUid())) {
            result.setMessage("error , no user login in , please login in ");
            result.setCode(202);
            return result;
        }
        // 获取购物车列表
        List<CartItem> cartItems = null;
        try {
            cartItems = mapper.selectByUid(user.getUid());
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- get cartItemList");
            return result;
        }
        result.setCode(202);
        if (cartItems == null || cartItems.size() == 0) {
            for (CartItem cai : cartItems) {
                cai.setSubTotal();
            }

            result.setMessage("你还没有添加任何商品呢, 请先添加商品然后再来查看购物车");
        }
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in get CartItem");
        result.setData(cartItems);
        redisTemplate.opsForValue().set("cart", result);
        return result;
    }

    @Override
    public Result delById(String pid) {
        Result result = new Result();
        result.setSuccess(false);
        int res = 0;
        try {
            res = mapper.deletByPid(pid);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- del cartItem item ");
            return result;
        }
        result.setData(1);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in delete cartItem item ");
        redisTemplate.opsForValue().set("cart", null);
        return result;
    }


}
