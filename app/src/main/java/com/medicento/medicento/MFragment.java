package com.medicento.medicento;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.medicento.medicento.utils.Preference;

/**
 * Created by sid on 10/1/18.
 */

public class MFragment extends Fragment {
    public MainActivity mainActivity=null;
    public Preference preference=null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity=(MainActivity) getActivity();
        preference = new Preference(getActivity());
    }

    public void finish(){
        mainActivity.getSupportFragmentManager().popBackStack();
    }
}
