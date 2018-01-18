package com.medicento.medicento.screens;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.medicento.medicento.Constant;
import com.medicento.medicento.MFragment;
import com.medicento.medicento.R;

/**
 * Created by sid on 11/1/18.
 */



public class HomeScreen extends MFragment implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawer;
    FragmentManager fragmentManager;
    MFragment NewOrderFragment, InventoryFragment, OrdersFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            /*mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
        mainActivity.getSupportActionBar().show();
        fragmentManager = getChildFragmentManager();
        NewOrderFragment = new BillingScreen();
        InventoryFragment = new InventoryScreen();
        OrdersFragment = new OrdersScreen();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, final Bundle bundle){
        init(view);
    }

    void init(View view){
        initNav();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.page_content,NewOrderFragment).commit();
    }

    void logout(){
        preference.delete(Constant.LOGINDATA);
        mainActivity.switchFragment(new LoginScreen());
    }

    void initNav(){
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mainActivity.toggle = new ActionBarDrawerToggle(
                mainActivity, drawer, mainActivity.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(mainActivity.toggle);
        mainActivity.toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_new);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragmentToOpen=null;
        switch(id){
            case R.id.nav_logout:
                logout();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_new:
                //fragmentToOpen = dashboardFragment;
                mainActivity.getSupportActionBar().setTitle("New Bill");
                switchFragment(NewOrderFragment);
                break;
            case R.id.nav_inventory:
                //fragmentToOpen = payslipFragment;
                mainActivity.getSupportActionBar().setTitle("Inventory");
                switchFragment(InventoryFragment);
                break;
            case R.id.nav_orders:
                mainActivity.getSupportActionBar().setTitle("Orders");
                switchFragment(OrdersFragment);
                break;
        }
        /*if(fragmentToOpen!=null){
            fragmentManager.beginTransaction().replace(R.id.page_content, fragmentToOpen).commit();
        }*/
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*@Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            fragmentManager.beginTransaction().replace(R.id.page_content, dashboardFragment).commit();
            toolbar.setTitle("Overview");
            //super.onBackPressed();
        }
    }*/

    void switchFragment(Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(fragment!=null){
            transaction.replace(R.id.page_content,fragment).commit();
        }
    }
}
