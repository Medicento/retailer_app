package com.medicento.medicento.models;

import java.io.Serializable;

/**
 * Created by sid on 11/1/18.
 */


public class LoginObject implements Serializable {
    public String id;
    public String shopName,username;

    public LoginObject(String id, String shopName, String username) {
        this.id = id;
        this.shopName = shopName;
        this.username = username;
    }
}
