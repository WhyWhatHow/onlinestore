package com.sdut.onlinestore.controller;

import com.sdut.onlinestore.pojo.Category;
import com.sdut.onlinestore.pojo.Product;
import com.sdut.onlinestore.service.ProductService;
import com.sdut.onlinestore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/product")
@CrossOrigin
@RestController
public class ProductController {

    @Autowired
    ProductService service ;
    /**
     * @Author whywhathow
     * TODO: 返回商品列表, 分页显示
     * 前端: 需要提供开始页码,与每页的记录数
     * 				let page = data.data;
     * 				that.tableData = page.list;
     * 				that.pageData.total = page.total;
     * 后端:
     * @Param []
     * @return com.sdut.onlinestore.utils.Result
     **/
    @RequestMapping("/all")
    public  Result selectByDefault(Integer start, Integer rows){
        if(StringUtils.isEmpty(start) ){
            start = 1 ;
        }
        if (StringUtils.isEmpty(rows)){
            rows  = 1;
        }
        return service.selectByDefault(start, rows);
    }
    /**
     * @Author whywhathow
     * TODO:
     * 前端: 表单修改 product{pid} 需要提供 pid (必须)
     * 后端:  直接返回message ,result.data 不涉及,
     * @Param [product]
     * @return com.sdut.onlinestore.utils.Result
     **/
    @RequestMapping("/update")
    public Result updateProduct(@RequestBody Product product){
        return service.updateProduct(product);
    }
    /**
     * @Author whywhathow
     * TODO: 添加商品信息
     * 前端:  基本选项 不包括 pid(后端生成) , 商品分类
     * 后端:   返回提示信息,result.success == true or false
     *
     * @Param [product]
     * @return com.sdut.onlinestore.utils.Result
     **/
    @RequestMapping("/add")
    public Result insertProduct(@RequestBody Product product,Category category){
        return service.insertProduct(product,category );
    }
}

