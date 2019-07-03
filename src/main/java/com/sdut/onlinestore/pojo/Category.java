package com.sdut.onlinestore.pojo;

import com.sun.tools.javac.util.List;

import java.util.ArrayList;

public class Category {

    private Integer cid;

    private String cname;

    private Integer parentid;

    private String location;

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", parentid=" + parentid +
                ", location='" + location + '\'' +
                ", ChildernList=" + ChildernList +
                '}';
    }

    public ArrayList<Category> getChildernList() {
        return ChildernList;
    }

    public void setChildernList(ArrayList<Category> ChildernList) {
        this.ChildernList = ChildernList;
    }

    private ArrayList<Category> ChildernList ;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }
}