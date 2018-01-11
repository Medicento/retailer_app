package com.medicento.medicento.screens;

import android.os.Bundle;
import android.os.Handler;
import android.util.EventLog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;
import com.medicento.medicento.components.MEditText;

/**
 * Created by sid on 10/1/18.
 */

public class GSTScreen extends MFragment {

    MEditText gstNumView;
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
        return inflater.inflate(R.layout.fragment_ask_gst, container, false);
    }

    @Override
    public void onViewCreated(View view, final Bundle bundle){
        init();
    }

    void init(){
        View view = this.getView();
        gstNumView = (MEditText)view.findViewById(R.id.gstNumView);

        /*gstNumView.setOnEnterListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == EditorInfo.IME_ACTION_SEARCH ||
                        keyCode == EditorInfo.IME_ACTION_DONE ||
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode()==KeyEvent.KEYCODE_NUMPAD_ENTER){
                    toSignUp();
                    return true;
                }
                return false;
            }
        });*/
        view.findViewById(R.id.toRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!gstNumView.getText().trim().isEmpty()) toSignUp();
                else{
                    Toast.makeText(mainActivity,"Incomplete Data",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void toSignUp(){
        Bundle gstData = new Bundle();
        gstData.putString("gstNum",gstNumView.getText());
        mainActivity.switchFragment(new SignUpScreen(),gstData,true);
    }
}
