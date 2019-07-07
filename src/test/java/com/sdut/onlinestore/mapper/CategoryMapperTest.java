package com.sdut.onlinestore.mapper;

import com.sdut.onlinestore.pojo.Category;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryMapperTest {

    @Before
    void setUp() {
    }

    @Autowired
    CategoryMapper mapper;

    @Test
    void countByExample() {
    }

    @Test
    void deleteByExample() {
    }

    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void insert() {
    }

    @Test
    void insertSelective() {
    }

    @Test
    void selectByExample() {
    }

    @Test
    void selectByPrimaryKey() {
        Category category = mapper.selectByPrimaryKey(1);
        System.out.println(category);
    }

    @Test
    void updateByExampleSelective() {
    }

    @Test
    void updateByExample() {
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }

    @Test
    void selectAll() {
        List<Category> categories = mapper.selectAll();
        for (Category cat :
                categories) {
            System.out.println(cat);
        }
    }
}