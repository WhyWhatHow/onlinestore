package com.sdut.onlinestore.controller;

import com.sdut.onlinestore.pojo.Category;
import com.sdut.onlinestore.service.CategoryService;
import com.sdut.onlinestore.service.ProductService;
import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/category")
@CrossOrigin
@RestController
public class CategoryController {

    @Autowired
    CategoryService service;

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO: 添加一个分类
     * 前端: 提供相应的json 数据
     * 后端:
     * @Param [category]
     **/
    @RequestMapping("/add")
    public Result insertCategory(@RequestBody Category category) {
        return service.insertCategory(category);
    }

    /**
     * @return
     * @Author whywhathow
     * TODO: 根据category id删除category (修改标记位)
     * 前端:
     * 后端:  返回一个result.data 封装的是数组
     * @Param [category]
     **/
    @RequestMapping("/del")
    public Result deleteCategory(@RequestBody Category category) {
        return null;
    }

    /**
     * TODO: 修改category
     * 后端: 通过category 修改category 对应的属性
     * 前端: 通过判断 result.success 对象进行判断
     *
     * @param category
     * @return result ,
     */
    @RequestMapping("/update")
    public Result updateCategory(@RequestBody Category category) {

        return service.updateCategory(category);

    }

    @Autowired
    ProductService productService;

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO: 用户点击某个category, 获取 商品列表
     * 前端: {category: category,start:start, rows:rows}
     * 后端:  PageInfo{list[],total,...}
     * @Param [category]
     **/

    @RequestMapping("/get")
    public Result getCategory(@RequestBody CategoryVo categoryVo) {
        return productService.selectByCategory(categoryVo);
    }


    @RequestMapping("/all")
    public Result getAllCategory() {
        return service.getAll();
    }
}
