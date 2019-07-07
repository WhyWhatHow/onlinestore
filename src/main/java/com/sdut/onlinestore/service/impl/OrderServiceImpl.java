package com.sdut.onlinestore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdut.onlinestore.mapper.CartItemMapper;
import com.sdut.onlinestore.mapper.ItemMapper;
import com.sdut.onlinestore.mapper.OrderMapper;
import com.sdut.onlinestore.mapper.UserMapper;
import com.sdut.onlinestore.pojo.*;
import com.sdut.onlinestore.service.OrderService;
import com.sdut.onlinestore.utils.MD5Util;
import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper mapper;
    @Autowired
    UserMapper userMapper;

    private Order dealOrder(CartVo vo) {
        Order order = new Order();
        order.setOrdertime(new Date());
        order.setOid(MD5Util.setUUID());
//        order.setUser(vo.getUser());
        User user = null;

        try {
            user = userMapper.selectByPrimaryKey(vo.getUser().getUid());
        } catch (Exception e) {
        }
        order.setUser(user);
        order.setAddress(user.getAddress());
//        double sum = 0;
        List<Item> list = new ArrayList<Item>();
        System.err.println("----------------------error test ----------------------------");
        for (CartItem cat : vo.getList()) {
            System.err.println("cat-------------------" + cat + "-----------------------------------");
            Item item = new Item();
            item.setQuantity(cat.getNum());
            item.setItemid(MD5Util.setUUID());
            Product product = new Product();
            product.setPid(cat.getPid());
            item.setProduct(product);
            item.setTotal(cat.getSubTotal());
            item.setOrder(order);
            sum += item.getTotal();

            order.getList().add(item);
//            list.add(item);
//            System.err.println(item);
//            sum+=item.
        }
//        order.setList(list);
        order.setState(0);
        order.setTotal(vo.getTotal());
        System.err.println("------------------- error test ---------------------------------");
        return order;
    }

    @Autowired
    CartItemMapper cartItemMapper;


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Result saveOrder(CartVo vo) {
        Result result = new Result();
        result.setSuccess(false);
        if (vo.getUser() == null || vo.getList().size() == 0) {
            result.setMessage("你并没有添加任何商品,所以不可以结算to生成订单");
            return result;
        }
        Order order = null;
        try {
            order = dealOrder(vo);
            System.err.println(order);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --dealOrder in saveOrder");
            return result;
        }
        // 添加订单
        try {
//            mapper.insertInit(order);
            mapper.insertSelective(order);
        } catch (Exception e) {
            e.printStackTrace();
//            System.err.println(e.printStackTrace());
            result.setCode(500);
            result.setMessage("Server's problem,  --" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//
            return result;
        }
        // 添加订单项
        int rr = 0;
        try {
            for (Item item : order.getList()) {
                System.err.println(item.getItemid() + ": " + item.getQuantity() + " : " + item.getTotal() + item.getProduct().getPid() + " : " + item.getOrder().getOid());
            }
            List<Item> list = order.getList();
            rr = itemMapper.insertByList(list);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMessage("Server's problem,  -- insert order item ");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//
            return result;
        }
        System.err.println(rr + "----------------------------------------");
        // !!!  修改购物车表中的商品状态 is_finished ->true

        try {
            for (CartItem item : vo.getList()) {
                cartItemMapper.updateToFinished(item.getId(), vo.getUser().getUid());
            }
//            cartItemMapper.updateToFinishedInList(vo);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//
            result.setCode(500);
            result.setMessage("Server's problem,  -- change cart_item table ");
            return result;
        }
        // 修改缓存数据库
        redisTemplate.opsForValue().set("cart", null);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in save order ...");
        result.setData(rr);
        return result;
    }

    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public Result selectByPage(HttpServletRequest req, String uid) {
        Result result = new Result();
        result.setSuccess(false);
        User user = (User) req.getSession().getAttribute("user");
        if(user == null){
            user = new User();
            user.setUid(uid);
        }
        List<Order> list = null;
        //分装订单页
        PageInfo<Order> info = null;
        try {
            PageHelper.startPage(1, 10);
            info = PageInfo.of(mapper.selectByUid(user.getUid()));
            info.setTotal(mapper.selectCountByUid(user.getUid()));
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- get order list ");
            return result;
        }
        list = info.getList();
        for (Order ord : list) {
            ord.setList(itemMapper.selectByOid(ord.getOid()));
        }

        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in get orders page ");
        result.setData(info);
        return result;

    }

    @Autowired
    ItemMapper itemMapper;

    @Override
    public Result getDetail(String oid) {
        Result result = new Result();
        result.setSuccess(false);
        // 获取订单详情
        Order order = null;
        try {
            order = mapper.selectByOid(oid);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- get order ");
            return result;
        }
        // 获取订单项详情
        List<Item> list = null;
        try {
            list = itemMapper.selectByOid(oid);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
        order.setList(list);
        result.setData(order);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in get order detail by oid ");
        return result;

    }


    @Override
    public Result setState(String oid, Integer state) {
        Result result = new Result();
        result.setSuccess(false);
        int res = 0;
        try {
            res = mapper.updateStateByOid(oid,state);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- update order");
            return result;
        }
        result.setCode(202);
        if (res == 0) {
            result.setMessage("error in update order . never ever be happen ");
        } else {
            result.setMessage("update order to pay success");
            result.setSuccess(true);
        }
        return result;
    }


}
