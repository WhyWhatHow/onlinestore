package com.sdut.onlinestore.vo;

import com.sdut.onlinestore.pojo.CartItem;
import com.sdut.onlinestore.pojo.User;

import java.io.Serializable;
import java.util.List;

public class CartVo implements Serializable {
    List<CartItem> list ;
    private User user ;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CartVo() {
    }

    @Override
    public String toString() {
        return "CartVo{" +
                "list=" + list +
                '}';
    }

    public List<CartItem> getList() {
        return list;
    }

    public void setList(List<CartItem> list) {
        this.list = list;
    }
}
