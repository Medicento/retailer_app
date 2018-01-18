package com.medicento.medicento.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sid on 18/1/18.
 */

public class Bill implements Serializable {
    public String name, doctorName, phNo, invoiceNn;
    public ArrayList<BillProduct> products;
    public double total=0, totalDiscount=0;
}
