package com.sdut.onlinestore.vo;

import com.sdut.onlinestore.pojo.Product;
import com.sdut.onlinestore.pojo.User;

public class ProductVo {
     private User user ;

     private Product product ;

     private Integer quantity = 1 ;

     private Double fee  ;

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
