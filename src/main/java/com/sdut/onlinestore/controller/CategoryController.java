package com.sdut.onlinestore.controller;

import com.sdut.onlinestore.pojo.Category;
import com.sdut.onlinestore.pojo.Product;
import com.sdut.onlinestore.service.CategoryService;
import com.sdut.onlinestore.service.ProductService;
import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.CategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("商品分类的相关接口-- Pass")
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
    @ApiOperation(value = "填写表单添加一个分类,pass" ,notes = "{\n" +
            "\t\"cname\":\"手环\",\n" +
            "\t\"parentid\":0\n" +
            "}" )
    @PostMapping("/add")
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
    @ApiOperation(value = "删除 一个分类", notes = "未考虑级联删除的情况,所以删除存在问题")
    @DeleteMapping("/del/{cid}")
    public Result deleteCategory(@PathVariable("cid") Integer cid) {
        return  service.deleteCategory(cid);
    }

    /**
     * TODO: 修改category
     * 后端: 通过category 修改category 对应的属性
     * 前端: 通过判断 result.success 对象进行判断
     *
     * @param category
     * @return result ,
     */
    @ApiOperation( value = "更新一个分类")
    @PostMapping("/update")
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
    @ApiOperation(value = "通过商品分类获取商品信息", notes = "与http://ip/product/cat方法重复", response = Product.class)
    @PostMapping("/get")
    public Result getCategory(@RequestBody CategoryVo categoryVo) {
        return productService.selectByCategory(categoryVo);
    }

    @ApiOperation(value = "查询全部分类 ", notes = "返回的是一个list", response = Category.class)
    @GetMapping("/all")
    public Result getAllCategory() {
        return service.getAll();
    }
}
