package com.example.smpl;

import com.application.smpl.database.adapter.StartUpDataBaseAdapter;
import com.application.smpl.qr_reader.DecoderActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * An activity representing a list of objects. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link objectDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link objectListFragment} and the item details (if present) is a
 * {@link objectDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link objectListFragment.Callbacks} interface to listen for item selections.
 */
public class objectListActivity extends FragmentActivity{

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
	    //Initialize DB
		DB = new StartUpDataBaseAdapter(this);
		//Create master list of products
		DB.InsertpTypes();
		DB.InsertProducts();
		
		map = (Button) findViewById(R.id.button_main_interactivemap);
		
		map.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intentStart = new Intent(getApplicationContext(),StoreMap.class);
				startActivity(intentStart);
			}
		});
		
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
