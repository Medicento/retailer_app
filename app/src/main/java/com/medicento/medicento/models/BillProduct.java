package com.medicento.medicento.models;

/**
 * Created by sid on 17/1/18.
 */

public class BillProduct {
    public String productName,batchNo,expDate;
    public int quantity;
    public double mrp,discount;

    public BillProduct(String productName, String batchNo, String expDate, int quantity, double mrp, double discount) {
        this.productName = productName;
        this.batchNo = batchNo;
        this.expDate = expDate;
        this.quantity = quantity;
        this.mrp = mrp;
        this.discount = discount;
    }
}
