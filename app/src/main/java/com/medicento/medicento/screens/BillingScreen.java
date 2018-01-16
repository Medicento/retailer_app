package com.medicento.medicento.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;
import com.medicento.medicento.models.BillProduct;
import com.medicento.medicento.models.Product;

import java.util.ArrayList;

/**
 * Created by sid on 16/1/18.
 */

public class BillingScreen extends MFragment {
    RecyclerView recyclerView;
    BillingAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity.getSupportActionBar().setTitle("Billing");
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
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
        adapter = new BillingAdapter();
        recyclerView.setAdapter(adapter);

        BillProduct product = new BillProduct("Product Name",
                "BTC1234",
                "27-Jan-2018",
                2,
                37.55,
                27);
        adapter.add(product);
        adapter.add(product);

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
        public void add(BillProduct product){
            this.list.add(product);
            notifyItemInserted(list.size()-1);
        }

        void add(BillProduct product,int index){
            this.list.add(index,product);
            notifyItemInserted(index);
        }

        @Override
        public BillingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill_product, parent, false);
            return new BillingAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            BillProduct product = list.get(position);
            holder.medicineName.setText(product.productName+" "+(position+1));
            holder.quantity.setText(String.valueOf(product.quantity+position));
            holder.batchView.setText(product.batchNo);
            holder.expiryView.setText(product.expDate);
            holder.mrpView.setText(String.valueOf(product.mrp));
            holder.discountView.setText(String.valueOf(product.discount)+"%");
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView medicineName,quantity,batchView,expiryView,mrpView,discountView;
            ViewHolder(View itemView) {
                super(itemView);
                medicineName=(TextView)itemView.findViewById(R.id.nameView);
                quantity = (TextView)itemView.findViewById(R.id.quantity);
                batchView = (TextView)itemView.findViewById(R.id.batchNoView);
                expiryView = (TextView)itemView.findViewById(R.id.expiryDateView);
                mrpView = (TextView)itemView.findViewById(R.id.MRPView);
                discountView = (TextView)itemView.findViewById(R.id.discountView);
            }
        }
    }
}
