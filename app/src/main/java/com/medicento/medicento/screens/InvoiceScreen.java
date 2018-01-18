package com.medicento.medicento.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.medicento.medicento.Constant;
import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;
import com.medicento.medicento.models.BillProduct;
import com.medicento.medicento.models.LoginObject;

import org.w3c.dom.Text;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by sid on 17/1/18.
 */

public class InvoiceScreen extends MFragment {

    LinearLayout productsView;
    TextView name,dName,phNo,shopName,dateView,timeView;
    LoginObject user;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mainActivity.getSupportActionBar().setTitle("Invoice No : 98238782738");
        //mainActivity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        user = preference.get(Constant.LOGINDATA,new TypeToken<LoginObject>(){}.getType());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_invoice,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_invoice,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.print_invoice){
            Toast.makeText(mainActivity,"Your bill no. 97823 is printed.",Toast.LENGTH_LONG).show();
            goBack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void init(){
        Calendar now = Calendar.getInstance();
        ArrayList<BillProduct> products = (ArrayList<BillProduct>)getArguments().getSerializable("products");
        productsView =(LinearLayout) findViewById(R.id.products_list);

        name = (TextView) findViewById(R.id.nameView);
        phNo = (TextView)findViewById(R.id.phoneView);
        dName = (TextView)findViewById(R.id.doctorView);
        shopName =(TextView)findViewById(R.id.shopName);
        timeView=(TextView)findViewById(R.id.timeView);
        dateView=(TextView)findViewById(R.id.dateView);

        name.setText(getArguments().getString("name"));
        phNo.setText(getArguments().getString("phNo"));
        dName.setText(getArguments().getString("dName"));
        shopName.setText(user.shopName);

        Format format = new SimpleDateFormat("dd-MMM-yyyy");
        dateView.setText(format.format(now.getTime()));

        format = new SimpleDateFormat("hh : mm a");
        timeView.setText(format.format(now.getTime()));

        for(BillProduct product : products){
            productsView.addView(getItemView(product));
        }
    }

    View getItemView(BillProduct product){
        View itemView = View.inflate(mainActivity,R.layout.item_invoice_product,null);
        TextView medicineName,quantity,batchView,expiryView,mrpView,discountView;
        medicineName=(TextView)itemView.findViewById(R.id.product_name);
        quantity = (TextView)itemView.findViewById(R.id.product_quantity);
        batchView = (TextView)itemView.findViewById(R.id.batchNoView);
        expiryView = (TextView)itemView.findViewById(R.id.expiryDateView);
        mrpView = (TextView)itemView.findViewById(R.id.product_amount);
        //discountView = (TextView)itemView.findViewById(R.id.discountView);
        //deleteView = (ImageButton)itemView.findViewById(R.id.product_delete);

        medicineName.setText(product.productName);
        quantity.setText(String.valueOf(product.quantity));
        batchView.setText(product.batchNo);
        expiryView.setText(product.expDate);
        mrpView.setText(String.valueOf(product.mrp));
        //holder.discountView.setText(String.valueOf(product.discount)+"%");
        return itemView;
    }
}
