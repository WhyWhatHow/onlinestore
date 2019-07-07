package com.sdut.onlinestore.service.impl;

import java.util.List;

public class ProductChangeVo {
    private List<String> list ;
    private boolean state;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
