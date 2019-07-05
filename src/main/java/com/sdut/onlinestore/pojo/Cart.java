package com.sdut.onlinestore.pojo;

import com.sdut.onlinestore.vo.CartItemVo;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
    Map<String, CartItemVo> map = new HashMap<String, CartItemVo>();
    private double total = 0;

    public void addCartItemToCar(CartItemVo cartItem) {
        String pid = cartItem.getProduct().getPid();
        if (map.containsKey(pid)) {
            CartItemVo oldItem = map.get(pid);
            oldItem.setNum(oldItem.getNum() + cartItem.getNum());

        } else {
            map.put(pid, cartItem);
        }
    }

    public Collection<CartItemVo> getCartItems() {
        return map.values();
    }


    public void removeCartItem(String pid) {
        map.remove(pid);
    }

    public void clearCart() {
        map.clear();
    }

    public double getTotal() {
        total = 0;
        Collection<CartItemVo> values = map.values();

        for (CartItemVo cartItem : values) {
            total += cartItem.getSubTotal();
        }

        return total;
    }


    public void setTotal(double total) {
        this.total = total;
    }

    public Map<String, CartItemVo> getMap() {
        return map;
    }


    public void setMap(Map<String, CartItemVo> map) {
        this.map = map;
    }

}