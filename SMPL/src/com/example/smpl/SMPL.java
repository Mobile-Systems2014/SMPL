package com.example.smpl;

import android.app.Activity;
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
import android.widget.TextView;
import com.application.smpl.database.adapter.StartUpDataBaseAdapter;
import com.application.smpl.qr_reader.DecoderActivity;
import com.example.smpl.fragments.AddDialog;
import com.example.smpl.fragments.AreaDialog;
import com.example.smpl.fragments.ProductListFragment;

/**
 * Created by Richard Sherrill on 7/24/2014.
 */
public class SMPL extends Activity {
	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;
	Button dataBase;
	StartUpDataBaseAdapter DB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		// Initialize DB
		DB = new StartUpDataBaseAdapter(this);
		// Create master list of products
		DB.InsertpTypes();
		DB.InsertProducts();
		
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


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		int selected = item.getItemId();
		final int settings = R.id.action_scanner;
		final int dbdebug = R.id.action_dbdebug;
		final int add = R.id.action_add;
		final int remove = R.id.action_remove;
		final int share = R.id.action_share;
		final int shop = R.id.action_shop;
		if (selected == settings) {
			Intent intentStart = new Intent(getApplicationContext(),
					DecoderActivity.class);
			startActivity(intentStart);
			return true;
		} else if (selected == dbdebug) {
			Intent intentStart = new Intent(getApplicationContext(),
					DatabaseDebug.class);
			startActivity(intentStart);
			return true;
		} else if (selected == add) {
			AddDialog dialog = new AddDialog();
			dialog.show(getFragmentManager(), "AddDialog");
			return true;
		} else if (selected == remove) {

		} else if (selected == share) {

		} else if (selected == shop) {
			Intent intentStart = new Intent(getApplicationContext(),StoreMap.class);
			startActivity(intentStart);
			getRequestedOrientation(); 
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
