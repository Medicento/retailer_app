package com.medicento.medicento.models;

/**
 * Created by sid on 16/1/18.
 */

public class Product {
    public String medicineName;
    public int quantity;

    public Product(String medicineName, int quantity) {
        this.medicineName = medicineName;
        this.quantity = quantity;
    }
}
