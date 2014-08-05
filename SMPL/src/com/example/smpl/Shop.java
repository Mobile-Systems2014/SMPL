package com.example.smpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.application.smpl.database.adapter.StartUpDataBaseAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Big Guy on 8/2/2014.
 */
public class Shop extends FragmentActivity {

	StartUpDataBaseAdapter DB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DB = new StartUpDataBaseAdapter(this);
		setContentView(R.layout.activity_main);

		List<HashMap<String, String>> nameList = DB.GetShoppingList();
		ArrayList<String> myList = grocierylist(nameList);

		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, myList);

		// Getting the reference to the listview object of the layout
		ListView listView1 = (ListView) findViewById(R.id.list_of_products);

		// Setting adapter to the listview
		listView1.setAdapter(adapter1);

		double total = DB.GetTotal();
		DecimalFormat format = new DecimalFormat("##.00");

		TextView textTotal = new TextView(this);
		textTotal = (TextView) findViewById(R.id.text_shopping);
		textTotal.setText("$" + format.format(total));

	}

	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		return super.onCreateView(name, context, attrs);

	}

	public void displayItems(int areaNumber) {

	}

	// Handles each area clicked

	public void onClick(final View view) {

		switch (view.getId()) {
		case R.id.area_1:
			displayItems(1);
			Log.d("blah", "area still works");
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

	private ArrayList<String> grocierylist(
			List<HashMap<String, String>> nameList) {
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
				if (Integer.parseInt(quantity) > 1) {
					arrayList.add(quantity + " " + item + "(s)");
				} else {
					arrayList.add(quantity + " " + item);
				}
			}
		}
		return arrayList;
	}
}
