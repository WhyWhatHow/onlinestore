package com.sdut.onlinestore.model;

import java.io.Serializable;

/**
 * Created by Donghua.Chen on 2018/6/7.
 */
public class User implements Serializable{
    private static final long serialVersionUID = 8655851615465363473L;
    private Long id;
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // TODO  省略get set
}
