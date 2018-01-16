package com.medicento.medicento;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.medicento.medicento.screens.SplashScreen;

public class MainActivity extends AppCompatActivity {

    public Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        switchFragment(new SplashScreen());
    }

    public void switchFragment(Fragment fragment,boolean addToBackStack,Bundle bundle,boolean hasTransitionAnimation,boolean addFragment){
        String backStateName = fragment.getClass().getName();
        boolean fragmentPopped = false;
        try{
            fragmentPopped = getSupportFragmentManager().popBackStackImmediate(backStateName,0);
        }catch (IllegalStateException e){/*HANDLED*/}

        if(!fragmentPopped){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (bundle != null) {
                fragment.setArguments(bundle);
            }

            if (addToBackStack) {
                fragmentTransaction.addToBackStack(backStateName);
            }
            if(addFragment) fragmentTransaction.add(R.id.container,fragment).commitAllowingStateLoss();
            else fragmentTransaction.replace(R.id.container, fragment).commitAllowingStateLoss();
        }
    }

    public void switchFragment(Fragment fragment){
        switchFragment(fragment,false,null,false,false);
    }

    public void switchFragment(Fragment fragment,boolean addTobackStack){
        switchFragment(fragment,addTobackStack,null,false,false);
    }

    public void switchFragment(Fragment fragment,Bundle bundle,boolean addTobackStack){
        switchFragment(fragment,addTobackStack,bundle,false,false);
    }

    public void popScreen(){
        getSupportFragmentManager().popBackStack();
    }
    public void popScreens(int number){
        for(int i=0;i<number;i++){
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
