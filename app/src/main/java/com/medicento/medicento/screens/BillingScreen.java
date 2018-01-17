package com.medicento.medicento.screens;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;
import com.medicento.medicento.models.BillProduct;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by sid on 16/1/18.
 */

public class BillingScreen extends MFragment {
    RecyclerView recyclerView;
    BillingAdapter adapter;
    TextInputEditText productName,productQuantity,productBatch,productExp,productMrp,productDiscount;
    FloatingActionButton addProductButton;
    TextView noProductView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mainActivity.getSupportActionBar().setTitle("Billing");
        //mainActivity.getSupportActionBar().show();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_billing,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
        adapter = new BillingAdapter();
        recyclerView.setAdapter(adapter);
        //adapter.add(new BillProduct("sfs","Adf","dfa",Integer.parseInt("23"),Double.parseDouble("23.4"),Double.parseDouble("12.6")));
    }

    void init(){
        productName=(TextInputEditText)findViewById(R.id.product_name);
        productQuantity=(TextInputEditText)findViewById(R.id.product_quantity);
        productBatch=(TextInputEditText)findViewById(R.id.product_batch_no);
        productExp=(TextInputEditText)findViewById(R.id.product_expiry_date);
        productMrp=(TextInputEditText)findViewById(R.id.product_mrp);
        productDiscount=(TextInputEditText)findViewById(R.id.product_discount);
        addProductButton=(FloatingActionButton)findViewById(R.id.add_product);
        noProductView =(TextView)findViewById(R.id.noProductView);


        productExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productExp.setText("");
                final Calendar today = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(mainActivity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if(today.get(Calendar.YEAR)<=year && today.get(Calendar.MONTH)<=month && today.get(Calendar.DAY_OF_MONTH)<=dayOfMonth){
                            Format format = new SimpleDateFormat("dd-MMM-yyyy");
                            Calendar cal = Calendar.getInstance();
                            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                            cal.set(Calendar.MONTH,month);
                            cal.set(Calendar.YEAR,year);
                            productExp.setText(format.format(cal.getTime()));
                        }
                        else{
                            Toast.makeText(mainActivity,"Invalid Date",Toast.LENGTH_SHORT).show();
                        }
                    }
                },today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(today.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=productName.getText().toString().trim(),
                        quantity = productQuantity.getText().toString().trim(),
                        batch = productBatch.getText().toString().trim(),
                        exp = productExp.getText().toString().trim(),
                        mrp = productMrp.getText().toString().trim(),
                        discount = productDiscount.getText().toString().trim();

                if(!(name.isEmpty() || quantity.isEmpty() || discount.isEmpty()
                || batch.isEmpty() || exp.isEmpty() || mrp.isEmpty())){
                    adapter.add(new BillProduct(name,batch,exp,Integer.parseInt(quantity),Double.parseDouble(mrp),Double.parseDouble(discount)));
                    recyclerView.scrollToPosition(adapter.getItemCount()-1);
                    reset();
                }
                else{
                    Toast.makeText(mainActivity,"Incomplete Data",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void reset(){
        productExp.setText("");
        productDiscount.setText("0.00");
        productMrp.setText("");
        productBatch.setText("");
        productQuantity.setText("");
        productExp.setText("");
        productName.setText("");
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_billing,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=  item.getItemId();
        if(id==R.id.generate_bill){
            //GENERATE BILL
        }
        return super.onOptionsItemSelected(item);
    }

    class BillingAdapter extends RecyclerView.Adapter<BillingAdapter.ViewHolder>{
        ArrayList<BillProduct> list = new ArrayList<>();
        View.OnClickListener listener = null;

        public ArrayList<BillProduct> getList(){
            return this.list;
        }
        BillingAdapter(){
            toggleNoProductVisibility();
        }
        public void add(BillProduct product){
            this.list.add(product);
            notifyItemInserted(list.size()-1);
            toggleNoProductVisibility();
        }

        void add(BillProduct product,int index){
            this.list.add(index,product);
            notifyItemInserted(index);
            toggleNoProductVisibility();
        }

        void toggleNoProductVisibility(){
            noProductView.setVisibility((this.list.size()==0)?View.VISIBLE:View.GONE);
        }

        @Override
        public BillingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill_product, parent, false);
            return new BillingAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder,int position) {
            BillProduct product = list.get(position);
            holder.medicineName.setText(product.productName);
            holder.quantity.setText(String.valueOf(product.quantity));
            holder.batchView.setText(product.batchNo);
            holder.expiryView.setText(product.expDate);
            holder.mrpView.setText(String.valueOf(product.mrp));
            holder.discountView.setText(String.valueOf(product.discount)+"%");
            holder.deleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    toggleNoProductVisibility();
                }
            });
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
                medicineName=(TextView)itemView.findViewById(R.id.nameView);
                quantity = (TextView)itemView.findViewById(R.id.quantity);
                batchView = (TextView)itemView.findViewById(R.id.batchNoView);
                expiryView = (TextView)itemView.findViewById(R.id.expiryDateView);
                mrpView = (TextView)itemView.findViewById(R.id.MRPView);
                discountView = (TextView)itemView.findViewById(R.id.discountView);
                deleteView = (ImageButton)itemView.findViewById(R.id.product_delete);
            }
        }
    }
}
