package com.medicento.medicento.screens;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;
import com.medicento.medicento.components.MEditText;
import com.medicento.medicento.models.Registration;
import com.medicento.medicento.utils.HttpRequest;
import com.medicento.medicento.utils.Method;

import org.json.JSONObject;

/**
 * Created by sid on 10/1/18.
 */

public class SignUpScreen extends MFragment {
    LayoutInflater inflater;
    ViewGroup container;

    MEditText nameView,emailView,dlNumberView,phoneView,passwordView,shopView,gstView;
    Button register;
    ImageButton togglePassword;
    Registration data = new Registration();

    boolean isPasswordShown = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            /*mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
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
        register = (Button)getView(view,R.id.register);
        gstView =(MEditText)getView(view,R.id.gstNumView);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailView.getText().trim(),
                        name = nameView.getText().trim(),
                        dlno = dlNumberView.getText().trim(),
                        phno = phoneView.getText().trim(),
                        password = passwordView.getText(),
                        shopname = shopView.getText().trim(),
                        gstNum=gstView.getText().trim();

                if(!(
                email.isEmpty() || name.isEmpty() ||
                dlno.isEmpty() || phno.isEmpty() ||
                password.isEmpty() || shopname.isEmpty()) || gstNum.isEmpty()){
                    trySignUp(new String[]{shopname,email,password,gstNum,
                    dlno,phno,name});
                }
                else {
                    Toast.makeText(mainActivity,"Incomplete Data",Toast.LENGTH_SHORT).show();
                }
            }
        });
        togglePassword = (ImageButton)getView(view,R.id.togglepassword);
        togglePassword.setImageResource(isPasswordShown?R.drawable.ic_unview:R.drawable.ic_view);
        if(isPasswordShown) passwordView.revealPassword();
        else passwordView.hidePassword();
        togglePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPasswordShown){
                    passwordView.hidePassword();
                    togglePassword.setImageResource(R.drawable.ic_view);
                }
                else{
                    passwordView.revealPassword();
                    togglePassword.setImageResource(R.drawable.ic_unview);
                }
                isPasswordShown = !isPasswordShown;
            }
        });
    }

    void trySignUp(String[] args){
        final ProgressDialog dialog = new ProgressDialog(mainActivity);
        dialog.setMessage("Registering");
        dialog.show();
        (new HttpRequest("/register", Method.POST))
                .addParam("shopname",args[0])
                .addParam("username",args[1])
                .addParam("password",args[2])
                .addParam("gstNo",args[3])
                .addParam("dlNo",args[4])
                .addParam("phno",args[5])
                .addParam("owner",args[6])
                .sendRequest(new HttpRequest.OnResponseListener() {
                    @Override
                    public void OnResponse(String response) {
                        if(response!=null){
                            try{
                                JSONObject registerResponse = new JSONObject(response);
                                if(registerResponse.getBoolean("success")){
                                    Toast.makeText(mainActivity,"Successful",Toast.LENGTH_SHORT).show();
                                    mainActivity.popScreen();
                                }
                                else{
                                    Toast.makeText(mainActivity,"Failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch (Exception e){

                            }
                        }
                        else{
                            Toast.makeText(mainActivity,"Check Internet Connectivity!",Toast.LENGTH_LONG).show();
                        }
                        dialog.cancel();
                    }
                });
    }

    void saveData(){
        data.email=emailView.getText();
        data.dlNumber=dlNumberView.getText();
        data.name=nameView.getText();
        data.phoneNumber=phoneView.getText();
        data.password=passwordView.getText();
        data.shopName=shopView.getText();
        data.gstNumber = gstView.getText();
    }

    void populateData(){
        emailView.setText(data.email);
        dlNumberView.setText(data.dlNumber);
        nameView.setText(data.name);
        phoneView.setText(data.phoneNumber);
        passwordView.setText(data.password);
        shopView.setText(data.shopName);
        gstView.setText(data.gstNumber);
    }
}
