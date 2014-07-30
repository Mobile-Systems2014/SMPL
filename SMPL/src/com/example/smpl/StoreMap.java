package com.example.smpl;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.smpl.fragments.AreaDialog;

/**
 * Created by Richard Sherrill on 7/5/2014.
 */
public class StoreMap extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_map);

        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
            finish();
        }
    }

    public void displayItems(int areaNumber){
        Bundle bundle = new Bundle();
        bundle.putInt("AreaNumber",areaNumber);

//        AreaDialog areaDialog = new AreaDialog();
//        areaDialog.setArguments(bundle);
//        areaDialog.show(getFragmentManager(), "AreaDialog");
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
