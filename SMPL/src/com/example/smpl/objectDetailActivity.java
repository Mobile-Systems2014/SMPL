package com.example.smpl;

import com.application.smpl.qr_reader.DecoderActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * An activity representing a single object detail screen. This activity is only
 * used on handset devices. On tablet-size devices, item details are presented
 * side-by-side with a list of items in a {@link objectListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link objectDetailFragment}.
 */
public class objectDetailActivity extends FragmentActivity {
	
	Button QrScanner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_object_detail);

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
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
		if(selected == settings)
		{
			Intent intentStart = new Intent(getApplicationContext(),DecoderActivity.class);
			startActivity(intentStart);
			finish();
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
