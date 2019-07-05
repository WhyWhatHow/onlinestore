package com.sdut.onlinestore.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdut.onlinestore.mapper.ItemMapper;
import com.sdut.onlinestore.mapper.OrderMapper;
import com.sdut.onlinestore.pojo.*;
import com.sdut.onlinestore.utils.MD5Util;
import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.CartVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Mapper
    OrderMapper mapper;

    public Order dealOrder(CartVo vo) {
        Order order = new Order();
        order.setOrdertime(new Date());
        order.setOid(MD5Util.setUUID());
        double sum = 0;
        List<Item> list = new ArrayList<Item>();
        System.err.println("----------------------error test ----------------------------");
        for (CartItem cat : vo.getList()) {

            Item item = new Item();
            item.setQuantity(cat.getNum());
            item.setItemid(MD5Util.setUUID());
            Product product = new Product();
            product.setPid(cat.getPid());
            item.setProduct(product);
            item.setTotal(cat.getSubTotal());
            item.setOrder(order);
            System.err.println(item);
        }
        order.setList(list);
        System.err.println("------------------- error test ---------------------------------");
        return order;
    }

    @Override
    @Transactional
    public Result saveOrder(CartVo vo) {
        Result result = new Result();
        result.setSuccess(false);
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
            mapper.insert(order);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
        // 添加订单项
        int rr = 0;
        try {
            rr = itemMapper.insertByList(order.getList());
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- insert order item ");
            return result;
        }
        System.err.println(rr + "----------------------------------------");

        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in save order ...");
        result.setData(rr);
        return result;
    }


    @Override
    public Result selectByPage(HttpServletRequest req) {
        Result result = new Result();
        result.setSuccess(false);
        User user = (User) req.getSession().getAttribute("user");
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
    public Result userPay(String oid) {
        Result result = new Result();
        result.setSuccess(false);
        int res = 0;
        try {
            res = mapper.updateByOidToPay(oid);
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
