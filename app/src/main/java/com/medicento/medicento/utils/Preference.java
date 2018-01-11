package com.medicento.medicento.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.google.gson.Gson;

import java.lang.reflect.Type;


/**
 * Created by sid on 1/10/17.
 */

public class Preference {
    //SHARED PREFERENCES INSTANCE OF THE APP
    private Context context;
    private String prefName;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public Preference(Context context){
        this.context = context;
        prefName = getAppName(context);
    }

    private String getAppName(Context context){
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "myPrefs";
    }
    private SharedPreferences getPrefsInstance(){
        if(preferences==null) preferences = context.getSharedPreferences(prefName,Context.MODE_PRIVATE);;
        return preferences;
    }

    //SHARED PREFERENCES EDITOR INSTANCE OF THE APP
    private  SharedPreferences.Editor getPrefEditor(){
        if(editor ==null) editor = getPrefsInstance().edit();
        return editor;
    }

    //BOOLEAN PREFERENCES
    public void put(String name, boolean value){
        SharedPreferences.Editor editor= getPrefEditor();
        editor.putBoolean(name,value);
        editor.apply();
    }
    public boolean get(String name,boolean defaultValue){
        return getPrefsInstance().getBoolean(name,defaultValue);
    }

    //INTEGER PREFERENCES
    public  void put(String name,int value){
        SharedPreferences.Editor editor= getPrefEditor();
        editor.putInt(name,value);
        editor.apply();
    }
    public  int get(String name, int defaultValue){
        return getPrefsInstance().getInt(name,defaultValue);
    }

    //GENERICS
    public <E> void put(String key,E value){
        if(value!=null && value.getClass().getSimpleName().equals("String")){
            getPrefEditor().putString(key,(String)value).apply();
            return;
        }
        if(value==null){
            getPrefEditor().putString(key,null).apply();
        }
        else {
            String toSave = (new Gson()).toJson(value);
            getPrefEditor().putString(key, toSave).apply();
        }
    }

    public String get(String key,String defaultValue){
        return getPrefsInstance().getString(key,defaultValue);
    }

    public <E> E get(String key,Type type){
        String json = getPrefsInstance().getString(key,null);
        if(json==null) return null;
        return (new Gson()).fromJson(json,type);
    }

    public void delete(String key){
        SharedPreferences.Editor editor = getPrefEditor();
        editor.remove(key);
        editor.apply();
    }

}
