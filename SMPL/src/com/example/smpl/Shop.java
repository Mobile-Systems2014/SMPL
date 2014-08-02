package com.example.smpl;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.smpl.fragments.AreaDialog;

/**
 * Created by Big Guy on 8/2/2014.
 */
public class Shop extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {

        return super.onCreateView(name, context, attrs);

    }

    public void displayItems(int areaNumber){
//        Bundle bundle = new Bundle();
//        bundle.putInt("AreaNumber",areaNumber);
//
//        FragmentManager fm = getFragmentManager();
//
//        AreaDialog areaDialog = new AreaDialog();
//        areaDialog.setArguments(bundle);
//        areaDialog.show(fm, "AreaDialog");

        TextView tv = (TextView)findViewById(R.id.area_name);
        tv.setText("Area "+String.valueOf(areaNumber));
    }

    // Handles each area clicked

    public void onClick(final View view) {

        switch (view.getId()) {
            case R.id.area_1:
                displayItems(1);
                break;
            case R.id.area_2:
                displayItems(2);
                break;
            case R.id.area_3:
                displayItems(3);
                break;
            case R.id.area_4:
                displayItems(4);
                break;
            case R.id.area_5:
                displayItems(5);
                break;
            case R.id.area_6:
                displayItems(6);
                break;
            case R.id.area_7:
                displayItems(7);
                break;
            case R.id.area_8:
                displayItems(8);
                break;
            case R.id.area_9:
                displayItems(9);
                break;
            case R.id.area_10:
                displayItems(10);
                break;
        }
    }

}
