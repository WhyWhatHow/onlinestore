package com.sdut.onlinestore.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private User user ;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String oid;

//    private String uid;

    private Date ordertime;

    private Double total;

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", oid='" + oid + '\'' +
                ", ordertime=" + ordertime +
                ", total=" + total +
                ", list=" + list +
                ", state=" + state +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    private List<Item> list = new ArrayList<>();

    // 0 生成订单,(未确定收货地址) 1. 确认订单(未付款)  2. 付款未发货,  3. 发货已收到
    private Integer state;

    private String address;

    private String telephone;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

//    public String getUid() {
//        return uid;
//    }
//
//    public void setUid(String uid) {
//        this.uid = uid == null ? null : uid.trim();
//    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }
}