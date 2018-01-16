package com.medicento.medicento.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;
import com.medicento.medicento.models.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by sid on 16/1/18.
 */

public class NewOrderScreen extends MFragment {
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

    }

    class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder>{
        ArrayList<Product> list = new ArrayList<>();
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
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View itemView) {
                super(itemView);

            }
        }
    }
}
