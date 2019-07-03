package com.sdut.onlinestore.pojo;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private Integer id;

    private String name;

    private String uri;

    private Integer parentid;

    private Integer type;

    private ArrayList<Menu> childrenList;

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uri='" + uri + '\'' +
                ", parentid=" + parentid +
                ", type=" + type +
                ", childrenList=" + childrenList +
                '}';
    }

    public ArrayList<Menu> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(ArrayList<Menu> childrenList) {
        this.childrenList = childrenList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}