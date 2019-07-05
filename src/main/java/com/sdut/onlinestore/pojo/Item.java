package com.sdut.onlinestore.pojo;

public class Item {

    private String itemid;

    private Integer quantity;

    private Double total;

    private Product product;

    private Order order;

    @Override
    public String toString() {
        return "Item{" +
                "itemid='" + itemid + '\'' +
                ", quantity=" + quantity +
                ", total=" + total +
                ", product=" + product +
                ", order=" + order +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
//
//    private String pid;
//
//    private String oid;

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid == null ? null : itemid.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}