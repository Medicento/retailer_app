package com.medicento.medicento.screens;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;
import com.medicento.medicento.Constant;
import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;
import com.medicento.medicento.models.LoginObject;
import com.medicento.medicento.utils.Preference;

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
        mainActivity.clearBackStack();
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
                LoginObject object = preference.get(Constant.LOGINDATA,new TypeToken<LoginObject>(){}.getType());
                if(object==null) mainActivity.switchFragment(new LoginScreen());
                else{
                    Bundle toHome = new Bundle();
                    toHome.putSerializable(Constant.LOGINDATA,object);
                    mainActivity.switchFragment(new HomeScreen(),toHome,false);
                }
            }
        },1000);
    }
}
