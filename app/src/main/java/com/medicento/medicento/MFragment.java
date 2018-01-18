package com.medicento.medicento;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicento.medicento.utils.Preference;

/**
 * Created by sid on 10/1/18.
 */

public class MFragment extends Fragment {
    public MainActivity mainActivity=null;
    public Preference preference=null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity=(MainActivity) getActivity();
        preference = new Preference(getActivity());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void finish(){
        mainActivity.getSupportFragmentManager().popBackStack();
    }

    public View findViewById(int id){
        return this.getView().findViewById(id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void goBack(){
        mainActivity.onBackPressed();
    }
}
