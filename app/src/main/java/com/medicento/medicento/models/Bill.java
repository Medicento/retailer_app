package com.medicento.medicento.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sid on 18/1/18.
 */

public class Bill implements Serializable {
    public String name, doctorName, phNo, invoiceNm;
    public ArrayList<BillProduct> products = new ArrayList<>();
    public double total=0, totalDiscount=0;
    public double amountTopay,roundOff;
    public Date created;

    public Bill(){}
    public Bill(String name, String doctorName, String phNo, String invoiceNn, double total) {
        this.name = name;
        this.doctorName = doctorName;
        this.phNo = phNo;
        this.invoiceNm = invoiceNn;
        this.total = total;
    }
}
