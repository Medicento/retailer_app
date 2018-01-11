package com.medicento.medicento.screens;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;

public class SplashScreen extends MFragment {

    public SplashScreen() {
        // Required empty public constructor
    }


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
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle){
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mainActivity.switchFragment(new LoginScreen());
            }
        },700);
    }
}
