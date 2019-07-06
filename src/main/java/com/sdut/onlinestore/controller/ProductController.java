package com.sdut.onlinestore.controller;

import com.sdut.onlinestore.pojo.Category;
import com.sdut.onlinestore.pojo.Product;
import com.sdut.onlinestore.service.ProductService;
import com.sdut.onlinestore.utils.Result;
import com.sdut.onlinestore.vo.CategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("商品的相关接口 -- pass")
@RequestMapping("/product")
@CrossOrigin
@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @ApiOperation(value= "对商品的模糊查询" ,notes =  "返回值是list" ,response = Product.class)
    @GetMapping("/like/{pname}")
    public Result selectByLike(@PathVariable("pname")String pname) {
        return  service.selectByLike(pname);
    }

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO: 返回商品列表, 分页显示
     * 前端: 需要提供开始页码,与每页的记录数
     * let page = data.data;
     * that.tableData = page.list;
     * that.pageData.total = page.total;
     * 后端:
     *
     **/
    @ApiOperation(value = "商品分页展示,感觉有点没有用", response = Product.class)
    @GetMapping("/all")
    public Result selectByDefault(Integer start, Integer rows) {
        if (StringUtils.isEmpty(start)) {
            start = 1;
        }
        if (StringUtils.isEmpty(rows)) {
            rows = 10;
        }
        return service.selectByDefault(start, rows);
    }

    @ApiOperation(value = "商品分页展示,按分类查找,建议用category下的接口",response = Product.class)
//    @ApiImplicitParam(required = true,  value = "vo")
   @PostMapping("/cat")
//     notes =  "留下了不加requestBody的泪水"
    public Result selectByCategory(@RequestBody  CategoryVo vo) {
        return service.selectByCategory(vo);
    }


    @ApiOperation(value = "根据商品id 删除某一个订单" ,response = Integer.class)
    @GetMapping("/del/{pid}")
    public Result deleteByPid(@PathVariable("pid") String pid){
        return service.deleteByPid(pid);
    }
    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO:
     * 前端: 表单修改 product{pid} 需要提供 pid (必须)
     * 后端:  直接返回message ,result.data 不涉及,
     * @Param [product]
     **/
    @ApiOperation(value = "修改商品信息" , notes = "Ps: 前端 一定要保证数据正确,修改啥,传啥" +
            "数据类型:" +
            "{\n" +
            "\n" +
            "  \"pid\": \"10\",\n" +
            "  \"pname\": \"小米加不加1\"\n" +
            "\n" +
            "}  ")
    @PostMapping("/update")
    public Result updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO: 添加商品信息
     * 前端:  基本选项 不包括 pid(后端生成) , 商品分类
     * 后端:   返回提示信息,result.success == true or false
     * @Param [product]
     **/
    @ApiOperation(value = "添加商品" , notes =  "product 的各种信息, 以及category 的 cid ,不需要填充product 里面的category," +
            "只需要添加cid即可---product.getCid() ")
    @ApiImplicitParam(value = "product" , name = "{\n" +
            "  \n" +
            "  \"cid\": 1,\n" +
            "  \"cname\": \"string\",\n" +
            "  \"createTime\": \"2019-07-05T13:17:31.942Z\",\n" +
            "  \"deleted\": true,\n" +
            "  \"discount\": 0,\n" +
            "  \"info\": \"string\",\n" +
            "  \"location\": \"string\",\n" +
            "  \"output\": 0,\n" +
            "  \"pid\": \"string\",\n" +
            "  \"pname\": \"小米加步枪\",\n" +
            "  \"price\": 0,\n" +
            "  \"stock\": 0,\n" +
            "  \"viewNumber\": 0,\n" +
            "  \"vipPrice\": 0,\n" +
            "  \"volume\": 0\n" +
            "}"
    )
    @PostMapping("/add")
    public Result insertProduct(@RequestBody Product product) {
        return service.insertProduct(product);
    }

    @ApiOperation("批量修改商品")
    @GetMapping("/change")
    public  Result changeListByPidAndState(List<String> list, Boolean state){
        return service.changeListByPidAndState(list,state);
    }

    @ApiOperation("后台的模糊查询")
    @PostMapping("/list")
    public Result selectToList(Product product) {
        return service.selectTolist(product);
    }
}

