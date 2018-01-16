package com.medicento.medicento.screens;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.CircularProgressDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.medicento.medicento.Constant;
import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;
import com.medicento.medicento.components.MEditText;
import com.medicento.medicento.models.LoginObject;
import com.medicento.medicento.utils.HttpRequest;
import com.medicento.medicento.utils.Method;
import com.medicento.medicento.utils.Preference;

import org.json.JSONException;
import org.json.JSONObject;

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
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle bundle){
        init();
    }

    void init(){
        emailView=(MEditText) findViewById(R.id.emailView);
        passwordView=(MEditText)findViewById(R.id.passwordView);
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
        final ProgressDialog dialog = new ProgressDialog(mainActivity);
        dialog.setMessage("Logging In");
        dialog.show();
        (new HttpRequest("/login", Method.POST))
                .addParam("username",username)
                .addParam("password",password)
                .sendRequest(new HttpRequest.OnResponseListener() {
                    @Override
                    public void OnResponse(String response) {
                        if(response!=null){
                            try {
                                JSONObject loginResponse = new JSONObject(response);
                                if(loginResponse.getBoolean("success")){
                                    Log.d("Response",response);
                                    LoginObject object = new LoginObject(
                                            loginResponse.getString("id"),
                                            loginResponse.getString("shopname"),
                                            loginResponse.getString("username")
                                    );
                                    preference.put(Constant.LOGINDATA,object);
                                    Bundle toHome = new Bundle();
                                    toHome.putSerializable(Constant.LOGINDATA,object);
                                    mainActivity.switchFragment(new HomeScreen(),toHome,false);
                                }
                                else{
                                    Toast.makeText(mainActivity, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            Log.d("Response","Error");
                        }
                        dialog.cancel();
                    }
                });
    }

}
