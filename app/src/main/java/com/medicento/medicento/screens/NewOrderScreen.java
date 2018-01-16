package com.medicento.medicento.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;
import com.medicento.medicento.components.MEditText;
import com.medicento.medicento.models.Product;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by sid on 16/1/18.
 */

public class NewOrderScreen extends MFragment {
    RecyclerView orderList;
    MEditText productNameView,quantityView;
    Button addButton;
    MedicineAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_order,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        init();

    }

    void init(){
        orderList = (RecyclerView)findViewById(R.id.order_list);
        orderList.setLayoutManager(new LinearLayoutManager(mainActivity));
        adapter = new MedicineAdapter();
        orderList.setAdapter(adapter);

        productNameView = (MEditText)findViewById(R.id.product_name);
        quantityView = (MEditText)findViewById(R.id.product_quantity);
        addButton = (Button)findViewById(R.id.button_add_product);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medicineName = productNameView.getText().trim();
                String quantity = quantityView.getText().trim();
                if(!medicineName.isEmpty() && !quantity.isEmpty()){
                    adapter.add(new Product(medicineName,Integer.parseInt(quantity)),0);
                }
            }
        });

    }

    class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder>{
        ArrayList<Product> list = new ArrayList<>();
        View.OnClickListener listener = null;

        public ArrayList<Product> getList(){
            return this.list;
        }
        public void add(Product product){
            this.list.add(product);
            notifyItemInserted(list.size()-1);
        }

        public void add(Product product,int index){
            this.list.add(index,product);
            notifyItemInserted(index);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_order, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Product product = list.get(position);
            holder.medicineName.setText(product.medicineName);
            holder.quantity.setText(String.valueOf(product.quantity));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView medicineName,quantity;
            ViewHolder(View itemView) {
                super(itemView);
                medicineName=(TextView)itemView.findViewById(R.id.med_name);
                quantity = (TextView)itemView.findViewById(R.id.med_quantity);
            }
        }
    }
}
