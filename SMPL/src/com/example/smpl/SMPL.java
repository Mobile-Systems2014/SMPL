package com.example.smpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.application.smpl.database.adapter.StartUpDataBaseAdapter;
import com.application.smpl.qr_reader.DecoderActivity;
import com.example.smpl.fragments.AddDialog;
import com.example.smpl.fragments.DeleteDialog;

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
	static SimpleAdapter  adapterList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
//        setContentView(R.layout.activity_main);
		// Initialize DB
		DB = new StartUpDataBaseAdapter(this);
		// Create master list of products
		DB.InsertpTypes();
		DB.InsertProducts();

/*		List<HashMap<String, String>> nameList = DB.GetShoppingList();
		
		ArrayList<String> myList = grocierylist(nameList);
				
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, myList);

		ListView listView = (ListView) findViewById(R.id.listview_grociery_list);
		listView.setAdapter(adapter);*/
	}
	
	/*private ArrayList<String> grocierylist(List<HashMap<String, String>> nameList)
	{
		ArrayList<String> arrayList = new ArrayList<String>();
		boolean flag = true;
		if (nameList != null) {
			for (HashMap<String, String> map : nameList) {
				String item = "";
				String quantity = "";
				for (Entry<String, String> mapEntry : map.entrySet()) {
					if (flag) {
						item = mapEntry.getValue();
						flag = false;
					} else {
						quantity = mapEntry.getValue();
						flag = true;
					}
				}
				if(Integer.parseInt(quantity) > 1)
				{
					arrayList.add(quantity + "     " + item + "(s)");
				}
				else
				{
					arrayList.add(quantity + "     " + item);
				}
			}
		}
		return arrayList;
	}*/
	
	public static void updateList()
	{
//		List<HashMap<String, String>> nameList = DB.GetShoppingList();
//
//		adapterList = new SimpleAdapter(this, nameList, R.layout.row ,new String[]{"Product","Quantity"}, new int[]{R.id.first, R.id.second});
//
//		ListView listView = (ListView) findViewById(R.id.listview_grociery_list);
//		listView.setAdapter(adapterList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
			DeleteDialog dialog = new DeleteDialog();
			dialog.show(getFragmentManager(), "DeleteDialog");
		} else if (selected == share) {
            //get list and send in email
            ArrayList<String> emailList = grocierylist(DB.GetShoppingList());

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");

            intent.putExtra(Intent.EXTRA_SUBJECT, "My Grocery List");
            intent.putExtra(Intent.EXTRA_TEXT, emailList.toString());

            startActivity(Intent.createChooser(intent, "Send Grocery List"));

		} else if (selected == shop) {
			Intent intentStart = new Intent(getApplicationContext(),Shop.class);
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

    private ArrayList<String> grocierylist(List<HashMap<String, String>> nameList)
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        boolean flag = true;
        if (nameList != null) {
            for (HashMap<String, String> map : nameList) {
                String item = "";
                String quantity = "";
                for (Map.Entry<String, String> mapEntry : map.entrySet()) {
                    if (flag) {
                        item = mapEntry.getValue();
                        flag = false;
                    } else {
                        quantity = mapEntry.getValue();
                        flag = true;
                    }
                }
                if(Integer.parseInt(quantity) > 1)
                {
                    arrayList.add(quantity + " " + item + "(s)");
                }
                else
                {
                    arrayList.add(quantity + " " + item);
                }
            }
        }
        return arrayList;
    }
}
