package com.sdut.onlinestore.service.impl;

import com.sdut.onlinestore.mapper.CategoryMapper;
import com.sdut.onlinestore.pojo.Category;
import com.sdut.onlinestore.service.CategoryService;
import com.sdut.onlinestore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper mapper;

    @Override
    public Result getAll() {
        Result result = new Result();
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

            categoryList = getChridrens(0, list);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
        result.setData(categoryList);
        result.setSuccess(true);
        result.setMessage("Success in selectAll category ");
        result.setCode(202);
        return result;
    }

    /**
     * @return java.util.List<com.sdut.onlinestore.pojo.Category>
     * @Author whywhathow
     * TODO: 利用递归实现无限分类
     * 后端:
     * @Param [parentId, list]
     **/
    public ArrayList<Category> getChridrens(Integer parentId, List<Category> list) {
        ArrayList<Category> arrayList = new ArrayList<>(100);
        for (Category cat : list) {
            if (parentId == cat.getParentid() ) {
                cat.setChridernList(getChridrens(cat.getCid(), list));
                arrayList.add(cat);
            }

        }
        return arrayList;
    }

}
