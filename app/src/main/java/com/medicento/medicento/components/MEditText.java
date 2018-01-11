package com.medicento.medicento.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.medicento.medicento.R;

/**
 * Created by sid on 10/1/18.
 */

public class MEditText extends LinearLayout {
    TextInputEditText editText;
    ImageView imageView;
    TextInputLayout editLayout;
    public MEditText(Context context) {
        super(context);
        //init(context);
    }

    public MEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    void init(Context context,AttributeSet attributeSet){
        init(context);
        String packageName = "http://schemas.android.com/apk/res-auto";
        int leftImage = attributeSet.getAttributeResourceValue(packageName,"leftImage",-1);
        if(leftImage>0){
            imageView.setImageResource(leftImage);
        }
        editLayout.setHint(attributeSet.getAttributeValue(packageName,"hint"));
        editText.setText(attributeSet.getAttributeValue(packageName,"text"));
        if(attributeSet.getAttributeBooleanValue(packageName,"isPassword",false))
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }
    void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.medittext,this);
        editText= (TextInputEditText)findViewById(R.id.editText);
        imageView =(ImageView)findViewById(R.id.leftImage);
        editLayout =findViewById(R.id.edit_cover);
    }

    public void revealPassword(){
        editText.setTransformationMethod(new PasswordTransformationMethod());
        editText.setSelection(editText.getText().length());
    }

    public void hidePassword(){
        editText.setTransformationMethod(new PasswordTransformationMethod());
        editText.setSelection(editText.getText().length());
    }

    public String getText(){
        return editText.getText().toString();
    }

    public void setText(String text){
        editText.setText(text);
    }

    public void setOnEditorActionListener(TextView.OnEditorActionListener listener){
        editText.setOnEditorActionListener(listener);
    }

    public void setOnEnterListener(View.OnKeyListener listener){
        editText.setOnKeyListener(listener);
    }
}
