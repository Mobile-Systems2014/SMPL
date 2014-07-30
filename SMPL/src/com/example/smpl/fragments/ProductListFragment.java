package com.example.smpl.fragments;

import android.app.ListFragment;
import android.os.Bundle;

/**
 * Created by Richard Sherrill on 7/29/2014.
 */
public class ProductListFragment extends ListFragment {

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(savedInstanceState != null) {

        }
    }
}
