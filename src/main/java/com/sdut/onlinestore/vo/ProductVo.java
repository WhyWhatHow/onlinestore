package com.sdut.onlinestore.vo;

import com.sdut.onlinestore.pojo.Product;
import com.sdut.onlinestore.pojo.User;

public class ProductVo {
     private User user ;
     private Product product ;
     private Integer num =1 ;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    private Integer start  =1 ;
     private Integer rows =10 ;

    @Override
    public String toString() {
        return "ProductVo{" +
                "user=" + user +
                ", product=" + product +
                ", start=" + start +
                ", rows=" + rows +
                '}';
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProductVo() {
    }
}
