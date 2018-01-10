package com.medicento.medicento.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;

/**
 * Created by sid on 10/1/18.
 */

public class LoginScreen extends MFragment {

    View view=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        mainActivity.getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login_screen, container, false);
        init();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle bundle){
    }

    private View findViewById(int id){
        return view.findViewById(id);
    }

    void init(){
        (findViewById(R.id.toSignupView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.switchFragment(new SignUpScreen(),true);
            }
        });
    }

}
