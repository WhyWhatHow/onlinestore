package com.sdut.onlinestore.vo;

import com.sdut.onlinestore.pojo.Product;

/**
 * @Author whywhathow
 * 购物车 中每一件物品
 **/
public class CartItemVo {
    private Product product;

    private Integer num;

    private Double subTotal;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getSubTotal() {
        return product.getPrice() * num;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
}
