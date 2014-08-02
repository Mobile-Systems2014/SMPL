package com.example.smpl;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.application.smpl.database.adapter.StartUpDataBaseAdapter;
import com.application.smpl.qr_reader.DecoderActivity;
import com.example.smpl.fragments.AreaDialog;
import com.example.smpl.fragments.ProductListFragment;

/**
 * Created by Richard Sherrill on 7/24/2014.
 */
public class SMPL extends FragmentActivity {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    Button dataBase, map;
    StartUpDataBaseAdapter DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (DB == null) {
            //Initialize DB
            DB = new StartUpDataBaseAdapter(this);
            //Create master list of products
            DB.InsertpTypes();
            DB.InsertProducts();
        }


        //map = (Button) findViewById(R.id.button_main_interactivemap);

//        map.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intentStart = new Intent(getApplicationContext(),StoreMap.class);
//                startActivity(intentStart);
//            }
//        });

        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
            //finish();
            // todo: check to see if store map fragment is visible, and hide if it is
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction();


        } else {
            // todo: inflate both fragments
        }

    }

    public void displayItems(int areaNumber){
        Bundle bundle = new Bundle();
        bundle.putInt("AreaNumber",areaNumber);

        FragmentManager fm = getFragmentManager();

        AreaDialog areaDialog = new AreaDialog();
        areaDialog.setArguments(bundle);
        areaDialog.show(fm, "AreaDialog");
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        int selected = item.getItemId();
        final int settings = R.id.action_scanner;
        final int dbdebug = R.id.action_dbdebug;
        if(selected == settings)
        {
            Intent intentStart = new Intent(getApplicationContext(),DecoderActivity.class);
            startActivity(intentStart);
            return true;
        }
        if(selected == dbdebug)
        {
            Intent intentStart = new Intent(getApplicationContext(),DatabaseDebug.class);
            startActivity(intentStart);
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_scanner:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
