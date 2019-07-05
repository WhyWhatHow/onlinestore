package com.sdut.onlinestore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdut.onlinestore.mapper.ProductMapper;
import com.sdut.onlinestore.pojo.Category;
import com.sdut.onlinestore.pojo.Product;
import com.sdut.onlinestore.service.ProductService;
import com.sdut.onlinestore.utils.MD5Util;
import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper mapper;

    @Override
    public Result selectByDefault(Integer start, Integer rows) {
        Result result = new Result();
        result.setSuccess(false);
//        PageHelper
        PageInfo<Product> of = null;
        try {
            PageHelper.startPage(start, rows);
            of = PageInfo.of(mapper.selectAll());
            of.setTotal(mapper.selectCount());
            System.err.println(of);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("服务器异常,请联系管理员,500");
            return result;

        }
        result.setData(of);
        result.setSuccess(true);
        result.setMessage("加载成功!!!");
        result.setCode(202);
        return result;

    }

    @Override
    public Result updateProduct(Product product) {

        Result result = new Result();
        result.setSuccess(false);
        int res = 0;
        try {
            res = mapper.updateByPrimaryKey(product);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --error update product");
            return result;
        }
        result.setCode(202);
        if (res == 1) {

            result.setSuccess(true);
            result.setMessage("Susscess update product info");

        } else {
            result.setMessage("error in update product");
        }

        return result;


    }

    @Override
    public Result insertProduct(Product product, Category category) {
        Result result = new Result();
        result.setSuccess(false);
        try {
            product.setPid(MD5Util.setUUID());
            product.setCat(category);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- add product ");
            return result;
        }
        result.setSuccess(true);
        result.setCode(202);
        result.setData(1);
        result.setMessage("Success in add Product ,");
        return result;
    }


    @Override
    public Result selectByCategory(CategoryVo category) {
        Result result = new Result();
        result.setSuccess(false);
        List<Product> list = null;
        PageInfo<Product> of = null;
        try {
            PageHelper.startPage(category.getStart(), category.getRows());
//            list = mapper.selectByCategory(category.getCategory());
            of = PageInfo.of(mapper.selectByCategory(category.getCategory()));
            of.setTotal(mapper.selectCountByCategory(category.getCategory()));
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- get product  by category ");
            return result;
        }
        result.setCode(202);
        if (list.size() == 0 || list == null) {
            result.setMessage("抱歉,该分类暂时没有商品, 请联系管理员进行添加");
        } else {
            result.setSuccess(true);
            result.setMessage("通过分类获取用户商品成功");
            result.setData(of);
        }
        return result;
    }



}
