package com.sdut.onlinestore.service.impl;

import com.sdut.onlinestore.pojo.Category;
import com.sdut.onlinestore.service.CategoryService;
import com.sdut.onlinestore.utils.Result;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    CategoryService service;

    @Test
    void getAll() {
        Result all = service.getAll();
        System.err.println(all);
        for (Object cat :
                (List)all.getData()    ) {
            System.out.println(cat);

        }
    }
    @Test
    void getChridrens() {
    }
}