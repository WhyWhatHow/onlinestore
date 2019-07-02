package com.sdut.onlinestore.controller;

import com.sdut.onlinestore.pojo.Category;
import com.sdut.onlinestore.utils.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/category")
@CrossOrigin
@RestController
public class CategoryController {
    /**
     *  TODO:
     *  后端: 通过category 修改category 对应的属性
     *  前端: 通过判断 result.success 对象进行判断
     * @param category
     * @return result ,
     */
    public Result updateCategory(Category category){
        return  null ;
    }


}
