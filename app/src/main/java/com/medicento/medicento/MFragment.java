package com.medicento.medicento;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by sid on 10/1/18.
 */

public class MFragment extends Fragment {
    public MainActivity mainActivity=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity=(MainActivity) getActivity();
    }
}
