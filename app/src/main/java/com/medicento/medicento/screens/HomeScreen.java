package com.medicento.medicento.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicento.medicento.Constant;
import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;

/**
 * Created by sid on 11/1/18.
 */

public class HomeScreen extends MFragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            /*mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
        mainActivity.getSupportActionBar().hide();
        mainActivity.clearBackStack();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, final Bundle bundle){
        init(view);
    }

    void init(View view){
        (view.findViewById(R.id.logout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preference.delete(Constant.LOGINDATA);
                mainActivity.switchFragment(new LoginScreen());
            }
        });
    }
}
