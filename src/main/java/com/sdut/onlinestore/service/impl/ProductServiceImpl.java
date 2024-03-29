package com.sdut.onlinestore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdut.onlinestore.mapper.ProductMapper;
import com.sdut.onlinestore.pojo.Product;
import com.sdut.onlinestore.service.ProductService;
import com.sdut.onlinestore.utils.MD5Util;
import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.CategoryVo;
import com.sdut.onlinestore.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

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
            res = mapper.updateByPrimaryKeySelective(product);
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
        result.setData(res);

        return result;


    }

    @Override
    public Result edit(Product product) {
        System.err.println(product);
        if (StringUtils.isEmpty(product.getPid())) {
            return insertProduct(product);
        } else {
            return updateProduct(product);
        }
    }

    @Override
    public Result insertProduct(Product product) {
        Result result = new Result();
        result.setSuccess(false);
        try {
            product.setPid(MD5Util.setUUID());
//            product.setCat(category);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- add product ");
            return result;
        }
        int res = 0;
        try {
            res = mapper.insertSelective(product);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
        result.setSuccess(true);
        result.setCode(202);
        result.setData(res);
        result.setMessage("Success in add Product ,");
        return result;
    }

    @Override
    public Result selectByLike(String pname) {
        Result result = new Result();
        result.setSuccess(false);
        List<Product> list = null;
        try {
            list = mapper.selectByLike(pname);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
        result.setCode(202);
        if (list != null && list.size() != 0) {
            result.setData(list);
            result.setSuccess(true);
            result.setMessage("Success in get product info ");
        } else {
            result.setMessage("No product contented !!!");
        }

        return result;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result changeListByPidAndState(List<String> list, Boolean state) {

        Result result = new Result();
        result.setSuccess(false);
        int res = 0;
        try {
            for (String pid : list) {
                res += mapper.updateByPidToChangeState(pid, state);
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//
            return result;
        }
        result.setData(res);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in update product list to change state ");
        return result;

    }


    @Override
    public Result selectTolist(ProductVo vo) {
        Result result = new Result();
        result.setSuccess(false);
        PageInfo<Product> info = null;
        try {
            PageHelper.startPage(vo.getStart(), vo.getRows());
            info = PageInfo.of(mapper.selectToList(vo.getProduct()));
            info.setTotal(mapper.selectToListCount(vo.getProduct()));

        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
//        for (Product prod : info.getList()) {
//            System.err.println(prod.getIsSelling());
//        }
//        System.err.println(123);
        result.setSuccess(true);
        result.setCode(202);
        result.setData(info);
        result.setMessage("Success in get product list background ");
        return result;

    }

    @Override
    public Result selectToSideShow() {
        Result result = new Result();
        result.setSuccess(false);
        List<Product> list = null;
        try {
            list = mapper.selectToSideShow();
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
        result.setData(list);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in get product side");
        return result;

    }


    @Override
    public Result selectByCategory(CategoryVo vo) {
        Result result = new Result();
        result.setSuccess(false);
        PageInfo<Product> of = new PageInfo<>();
        System.err.println(vo);
        try {
            PageHelper.startPage(vo.getStart(), vo.getRows());
            Integer cid = vo.getCategory().getCid();
            System.err.println(cid);
//            list = mapper.selectByCategory(category.getCategory());
            of = PageInfo.of(mapper.selectByCategory(cid));
            of.setTotal(mapper.selectCountByCategory(cid));
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- get product  by category ");
            return result;
        }
        result.setCode(202);
        List<Product> list = of.getList();
        if (list == null || list.size() == 0) {
            result.setMessage("抱歉,该分类暂时没有商品, 请联系管理员进行添加");
        } else {
            result.setSuccess(true);
            result.setMessage("通过分类获取用户商品成功");
            result.setData(of);
        }
        return result;
    }

    @Override
    public Result deleteByPid(String pid) {
        Result result = new Result();
        result.setSuccess(false);
        int res = 0;
        try {
            res = mapper.updateToDeleted(pid);
//            res = mapper.deleteByPrimaryKey(pid);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }


        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in delete Product");
        result.setData(res);
        return result;
    }


    @Override
    public Result detailByPid(String pid) {
        Result result = new Result();
        result.setSuccess(false);
        Product product = null;
//        int res = 0;
        try {
            product = mapper.selectByPrimaryKey(pid);
//            res = mapper.updateToDeleted(pid);
//            res = mapper.deleteByPrimaryKey(pid);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }

        result.setData(product);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in delete Product");
//        result.setData(res);
        return result;
    }
//    @Override
//    public Result insertProduct(Product product) {
//        return null;
//    }


}
