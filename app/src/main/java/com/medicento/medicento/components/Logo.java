package com.medicento.medicento.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.medicento.medicento.R;

/**
 * Created by sid on 11/1/18.
 */

public class Logo extends RelativeLayout {
    public Logo(Context context) {
        super(context);
        init(context);
    }

    public Logo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Logo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.logo,this);
        ImageView imageView = (ImageView) view.findViewById(R.id.logoView);
        Glide.with(context).load(R.drawable.logomedicento_1024).into(imageView);
    }

}
