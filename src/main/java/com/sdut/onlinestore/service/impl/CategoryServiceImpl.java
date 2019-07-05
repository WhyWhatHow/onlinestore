package com.sdut.onlinestore.service.impl;

import com.sdut.onlinestore.mapper.CategoryMapper;
import com.sdut.onlinestore.pojo.Category;
import com.sdut.onlinestore.service.CategoryService;
import com.sdut.onlinestore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper mapper;

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
//    @Cacheable(value = "category")
    public Result getAll() {
        // 如果缓存服务器中存在category对象
        Result result = (Result) redisTemplate.opsForValue().get("category");
        if (result != null && result.isSuccess() && result.getData() != null && !StringUtils.isEmpty(result)) {
            System.err.println("这并不是第一次查询.....,数据存在redis 中");
            result.setMessage(result.getMessage() + " redis.........................");
            return result;
        }
        // 缓存服务器并不存在category对象
        result = new Result();
        result.setSuccess(false);
        List<Category> list = null;

        try {
            list = mapper.selectAll();
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem , selectAllCategory");
            return result;
        }
        // 递归生成分类
        List<Category> categoryList = null;
        try {
            categoryList = getChildrens(0, list);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
        // TODO redis 缓存数据库的设置
        result.setData(categoryList);
        result.setSuccess(true);
        result.setMessage("Success in selectAll category ");
        result.setCode(202);
        redisTemplate.opsForValue().set("category", result);
        return result;
    }

    @Override
    public Result insertCategory(Category category) {
        Result result = new Result();
        result.setSuccess(false);
        int res = 0;
        try {
            res = mapper.insertSelective(category);

        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- insert category");
            return result;
        }
        result.setCode(202);
        result.setMessage("Success insert category ");
        result.setSuccess(true);
        // try catch 处理下 ...
        redisTemplate.opsForValue().set("category", null);

        return result;

    }

    @Override
    public Result updateCategory(Category category) {
        Result result = new Result();
        result.setSuccess(false);
        try {
            mapper.updateByPrimaryKeySelective(category);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
        redisTemplate.opsForValue().set("category", null);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in update category! ");
        result.setData(1);
        return result;


    }

    /**
     * @return java.util.List<com.sdut.onlinestore.pojo.Category>
     * @Author whywhathow
     * TODO: 利用递归实现无限分类
     * 后端:
     * @Param [parentId, list]
     **/
    public ArrayList<Category> getChildrens(Integer parentId, List<Category> list) {
        ArrayList<Category> arrayList = new ArrayList<>(50);
        for (Category cat : list) {
            if (parentId == cat.getParentid()) {
                cat.setChildernList(getChildrens(cat.getCid(), list));
                arrayList.add(cat);
            }

        }
        return arrayList;
    }

}
