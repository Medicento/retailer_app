package com.medicento.medicento.screens;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;
import com.medicento.medicento.components.MEditText;
import com.medicento.medicento.utils.HttpRequest;
import com.medicento.medicento.utils.Method;

/**
 * Created by sid on 10/1/18.
 */

public class LoginScreen extends MFragment {

    View view=null;
    MEditText emailView,passwordView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
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
        emailView=(MEditText) findViewById(R.id.emailView);
        passwordView=(MEditText)findViewById(R.id.passwordView);
        mainActivity.getSupportActionBar().hide();
        (findViewById(R.id.toSignupView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.switchFragment(new GSTScreen(),true);
            }
        });

        (findViewById(R.id.button_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailView.getText().trim();
                String password = passwordView.getText();
                if(!(email.isEmpty() || password.isEmpty())){
                    tryLogin(email,password);
                }
                else{
                    Toast.makeText(mainActivity,"Incomplete Data",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void tryLogin(String username,String password){
        username = "mehtab@gmail.com";
        password = "25nov1992";
        (new HttpRequest("/login", Method.POST))
                .addParam("username",username)
                .addParam("password",password)
                .sendRequest(new HttpRequest.OnResponseListener() {
                    @Override
                    public void OnResponse(String response) {
                        if(response!=null){
                            Log.d("Response",response);
                        }
                        else{
                            Log.d("Response","Error");
                        }
                    }
                });
    }

}
