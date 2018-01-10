package com.medicento.medicento.screens;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;
import com.medicento.medicento.components.MEditText;
import com.medicento.medicento.models.Registration;

/**
 * Created by sid on 10/1/18.
 */

public class SignUpScreen extends MFragment {
    LayoutInflater inflater;
    ViewGroup container;

    MEditText nameView,emailView,dlNumberView,phoneView,passwordView,shopView;

    Registration data = new Registration();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            /*mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
        mainActivity.getSupportActionBar().hide();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.container=container;
        this.inflater=inflater;
        int orientation = getResources().getConfiguration().orientation;
        View view= inflater.inflate((orientation==Configuration.ORIENTATION_LANDSCAPE)?
                R.layout.fragment_signup_screen_tab:
                R.layout.fragment_signup_screen, container, false);
        init(view);
        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        saveData();
        int orientation = getResources().getConfiguration().orientation;
        ViewGroup view = (ViewGroup)this.getView();
        view.removeAllViews();
        View newView;
        if(orientation== Configuration.ORIENTATION_PORTRAIT){
            newView = inflater.inflate(R.layout.fragment_signup_screen, container, false);
        }
        else{
            newView = inflater.inflate(R.layout.fragment_signup_screen_tab, container, false);
        }
        init(newView);
        populateData();
        view.addView(newView);
    }

    View getView(View view,int id){
        return view.findViewById(id);
    }

    void init(View view){
        emailView=(MEditText) getView(view,R.id.emailView);
        nameView=(MEditText) getView(view,R.id.emailView);
        dlNumberView=(MEditText) getView(view,R.id.dlNumberView);
        phoneView=(MEditText) getView(view,R.id.phoneView);
        passwordView=(MEditText) getView(view,R.id.passwordView);
        shopView=(MEditText) getView(view,R.id.shopView);
    }

    void saveData(){
        data.email=emailView.getText();
        data.dlNumber=dlNumberView.getText();
        data.name=nameView.getText();
        data.phoneNumber=phoneView.getText();
        data.password=passwordView.getText();
        data.shopName=shopView.getText();
    }

    void populateData(){
        emailView.setText(data.email);
        dlNumberView.setText(data.dlNumber);
        nameView.setText(data.name);
        phoneView.setText(data.phoneNumber);
        passwordView.setText(data.password);
        shopView.setText(data.shopName);
    }
}
