package com.sdut.onlinestore.pojo;

import java.io.Serializable;
import java.util.Date;

public class CartItem implements Serializable {
    // pid , price , subtotal , num

    private Integer quantity ;
    private Double fee ;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "price=" + price +
                ", subTotal=" + subTotal +
                ", id=" + id +
                ", uid='" + uid + '\'' +
                ", pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", plocation='" + plocation + '\'' +
                ", num=" + num +
                ", created=" + created +
                ", updated=" + updated +
                ", isFinished=" + isFinished +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public void setProduct(Product product) {
        this.price = product.getPrice();
        this.pid = product.getPid();
        this.pname = product.getPname();
        this.plocation = product.getImageUrl();
        this.created = new Date();
//        this.subTotal = this.price;
    }

    private Double price;

    public Double getPrice() {
        return price;
    }

    public double subTotal;

    public double getSubTotal() {
//        return price * num;
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void setSubTotal() {
        this.subTotal = this.price * this.num;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    private Long id;

    private String uid;

    private String pid;

    private String pname;

    private String plocation;

    private Integer num = 1;

    private Date created;

    private Date updated;

    private Boolean isFinished;

    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public String getPlocation() {
        return plocation;
    }

    public void setPlocation(String plocation) {
        this.plocation = plocation == null ? null : plocation.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}