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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Big Guy on 8/2/2014.
 */
public class Shop extends FragmentActivity {

	StartUpDataBaseAdapter DB;
    HashMap<Integer,String> areaList = new HashMap<Integer,String>();
    ArrayList<String> listToCompare;
    ArrayAdapter<String> mainAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DB = new StartUpDataBaseAdapter(this);
		setContentView(R.layout.activity_main);

        areaList.put(1,"apple");
        areaList.put(2,"bread");
        areaList.put(3,"pizza");
        areaList.put(10,"orange");
        areaList.put(4," milk ; eggs ; butter ");
        areaList.put(7,"chicken");
        areaList.put(5,"fish");
        areaList.put(6,"sprite");
        areaList.put(8,"none");
        areaList.put(9,"none");

		List<HashMap<String, String>> nameList = DB.GetShoppingList();
		ArrayList<String> myList = grocierylist(nameList);
        listToCompare = new ArrayList<String>(myList);
        listToCompare.add("milk");
        listToCompare.add("eggs");
        listToCompare.add("butter");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, myList);

		ListView listView = (ListView) findViewById(R.id.list_of_products);
		listView.setAdapter(adapter);
		
		double total = DB.GetTotal();
        DecimalFormat format = new DecimalFormat("##.00");
		
		TextView textTotal = new TextView(this);  
		textTotal = (TextView)findViewById(R.id.text_shopping);
		textTotal.setText("$" + format.format(total));

	}

	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		return super.onCreateView(name, context, attrs);

	}

	public void displayItems(int areaNumber) {
        String product = areaList.get(areaNumber);

        ArrayList<String> displayList = new ArrayList<String>();

        if (product.equals("none")){
            displayList.add("No item(s)");
        } else {

            for (String current : listToCompare) {
                if (current.contains(product)) {
                    displayList.add(current);
                }
                else if (product.contains(current)) {
                    displayList.add(current);
                }
            }
            if (displayList.isEmpty() || "".equals(product)) {
                displayList.add("No item(s)");
            }
        }
        mainAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, displayList);

        ListView listView = (ListView) findViewById(R.id.list_of_products);
        listView.setAdapter(mainAdapter);

	}

    public void refresh() {

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
