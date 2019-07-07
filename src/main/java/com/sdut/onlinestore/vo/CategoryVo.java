package com.sdut.onlinestore.vo;

import com.sdut.onlinestore.pojo.Category;

public class CategoryVo {
    private Category category;
    private Integer start = 1;
    private Integer rows = 10;

    @Override
    public String toString() {
        return "CategoryVo{" +
                "category=" + category +
                ", start=" + start +
                ", rows=" + rows +
                '}';
    }

    public CategoryVo() {
        this.start = 1;
        this.rows = 10;
    }

    public CategoryVo(Category category, Integer start, Integer rows) {
        this.category = category;
        this.start = start;
        this.rows = rows;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
