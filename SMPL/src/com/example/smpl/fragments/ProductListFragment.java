package com.example.smpl.fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.application.smpl.database.adapter.StartUpDataBaseAdapter;
import com.example.smpl.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Richard Sherrill on 7/29/2014.
 */
public class ProductListFragment extends Fragment {


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.product_list_fragment, container, false);


        return view;
    }
}
