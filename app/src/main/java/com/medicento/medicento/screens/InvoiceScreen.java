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

import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;
import com.medicento.medicento.models.BillProduct;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by sid on 17/1/18.
 */

public class InvoiceScreen extends MFragment {

    LinearLayout productsView;
    ProductAdapter adapter;
    TextView name,dName,phNo;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mainActivity.getSupportActionBar().setTitle("Invoice No : 98238782738");
        //mainActivity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    void init(){

        ArrayList<BillProduct> products = (ArrayList<BillProduct>)getArguments().getSerializable("products");
        productsView =(LinearLayout) findViewById(R.id.products_list);
        adapter= new ProductAdapter(products);

        name = (TextView) findViewById(R.id.nameView);
        phNo = (TextView)findViewById(R.id.phoneView);
        dName = (TextView)findViewById(R.id.doctorView);

        name.setText(getArguments().getString("name"));
        phNo.setText(getArguments().getString("phNo"));
        dName.setText(getArguments().getString("dName"));

        for(BillProduct product : products){
            productsView.addView(getItemView(product));
        }
    }

    View getItemView(BillProduct product){
        View itemView = View.inflate(mainActivity,R.layout.item_invoice_product,null);
        TextView medicineName,quantity,batchView,expiryView,mrpView,discountView;
        medicineName=(TextView)itemView.findViewById(R.id.product_name);
        quantity = (TextView)itemView.findViewById(R.id.product_quantity);
        //batchView = (TextView)itemView.findViewById(R.id.batchNoView);
        //expiryView = (TextView)itemView.findViewById(R.id.expiryDateView);
        mrpView = (TextView)itemView.findViewById(R.id.product_amount);
        //discountView = (TextView)itemView.findViewById(R.id.discountView);
        //deleteView = (ImageButton)itemView.findViewById(R.id.product_delete);

        medicineName.setText(product.productName);
        quantity.setText(String.valueOf(product.quantity));
        //holder.batchView.setText(product.batchNo);
        //holder.expiryView.setText(product.expDate);
        mrpView.setText(String.valueOf(product.mrp));
        //holder.discountView.setText(String.valueOf(product.discount)+"%");
        return itemView;
    }

    class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
        ArrayList<BillProduct> list = new ArrayList<>();
        View.OnClickListener listener = null;
        ProductAdapter(ArrayList<BillProduct> list){
            this.list=list;
        }


        public ArrayList<BillProduct> getList(){
            return this.list;
        }
        public void add(BillProduct product){
            this.list.add(product);
            notifyItemInserted(list.size()-1);
        }

        void add(BillProduct product,int index){
            this.list.add(index,product);
            notifyItemInserted(index);
        }

        @Override
        public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice_product, parent, false);
            return new ProductAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ProductAdapter.ViewHolder holder, int position) {
            BillProduct product = list.get(position);
            holder.medicineName.setText(product.productName);
            holder.quantity.setText(String.valueOf(product.quantity));
            //holder.batchView.setText(product.batchNo);
            //holder.expiryView.setText(product.expDate);
            holder.mrpView.setText(String.valueOf(product.mrp));
            //holder.discountView.setText(String.valueOf(product.discount)+"%");
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView medicineName,quantity,batchView,expiryView,mrpView,discountView;
            ImageButton deleteView;
            ViewHolder(View itemView) {
                super(itemView);
                medicineName=(TextView)itemView.findViewById(R.id.product_name);
                quantity = (TextView)itemView.findViewById(R.id.product_quantity);
                //batchView = (TextView)itemView.findViewById(R.id.batchNoView);
                //expiryView = (TextView)itemView.findViewById(R.id.expiryDateView);
                mrpView = (TextView)itemView.findViewById(R.id.product_amount);
                //discountView = (TextView)itemView.findViewById(R.id.discountView);
                //deleteView = (ImageButton)itemView.findViewById(R.id.product_delete);
            }
        }
    }
}
