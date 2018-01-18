package com.medicento.medicento.screens;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;
import com.medicento.medicento.models.Bill;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by sid on 16/1/18.
 */

public class OrdersScreen extends MFragment {
    TextView dateView;
    LinearLayout selectDateView;
    Calendar selectedDate;
    TextView noProductView;
    RecyclerView bills;
    BillAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.getSupportActionBar().setTitle("Sales");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    void init(){
        noProductView = (TextView)findViewById(R.id.noProductView);
        bills = (RecyclerView)findViewById(R.id.bills);
        bills.setLayoutManager(new LinearLayoutManager(mainActivity));
        ArrayList<Bill> billList = new ArrayList<>();
        billList.add(new Bill("Consumer 1","Doctor 1","9790","inv1",236.44));
        billList.add(new Bill("Consumer 2","Doctor 2","97902","inv2",421.57));
        billList.add(new Bill("adfa","asdf","adfa","adfa",23.44));
        adapter = new BillAdapter(billList);
        bills.setAdapter(adapter);
        dateView = (TextView)findViewById(R.id.selectedDateView);
        selectDateView =(LinearLayout) findViewById(R.id.selectDateView);
        selectedDate = Calendar.getInstance();
        selectDateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog= new DatePickerDialog(mainActivity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDate.set(Calendar.YEAR,year);
                        selectedDate.set(Calendar.MONTH,month);
                        selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        Format format = new SimpleDateFormat("dd-MMM-yyyy");
                        dateView.setText(format.format(selectedDate.getTime()));
                    }
                },selectedDate.get(Calendar.YEAR),selectedDate.get(Calendar.MONTH),selectedDate.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
    }

    class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder>{
        ArrayList<Bill> list = new ArrayList<>();
        View.OnClickListener listener = null;

        BillAdapter(ArrayList<Bill> list){
            this.list=list;
            toggleNoProductVisibility();
        }

        public ArrayList<Bill> getList(){
            return this.list;
        }
        BillAdapter(){
            toggleNoProductVisibility();
        }
        public void add(Bill product){
            this.list.add(product);
            notifyItemInserted(list.size()-1);
            toggleNoProductVisibility();
        }

        void add(Bill product,int index){
            this.list.add(index,product);
            notifyItemInserted(index);
            toggleNoProductVisibility();
        }

        void toggleNoProductVisibility(){
            noProductView.setVisibility((this.list.size()==0)?View.VISIBLE:View.GONE);
        }

        @Override
        public BillAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history, parent, false);
            return new BillAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final BillAdapter.ViewHolder holder, int position) {
            Bill product = list.get(position);
            holder.name.setText(product.name);
            holder.phNo.setText(String.valueOf(product.phNo));
            holder.amount.setText(String.format("%.2f",product.total));
            holder.invoice_num.setText(product.invoiceNm);
            holder.viewInvoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Bill",list.get(holder.getAdapterPosition()));
                    mainActivity.switchFragment(new InvoiceScreen(),bundle,true);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView name,phNo,amount,invoice_num;
            Button viewInvoice;
            ViewHolder(View itemView) {
                super(itemView);
                name=(TextView)itemView.findViewById(R.id.name);
                phNo = (TextView)itemView.findViewById(R.id.ph_number);
                amount = (TextView)itemView.findViewById(R.id.total_amount);
                invoice_num = (TextView)itemView.findViewById(R.id.invoice_num);
                viewInvoice = (Button)itemView.findViewById(R.id.view_invoice);
            }
        }
    }
}
