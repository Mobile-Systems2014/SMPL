package com.example.smpl;

import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.application.smpl.database.adapter.StartUpDataBaseAdapter;

public class DatabaseDebug extends FragmentActivity{

	StartUpDataBaseAdapter DB;
	Button showList, showTotal, showProducts;
	Spinner spinner;
	ArrayAdapter adapterList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dbdebuger);
				
		//Initilize DB
		DB = new StartUpDataBaseAdapter(this);
		showList = (Button) findViewById(R.id.button_dbdebuger_showlist);
		showTotal = (Button) findViewById(R.id.button_dbdebuger_total);
		showProducts = (Button) findViewById(R.id.button_dbdebuger_allproducts);

		showList.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//TODO
			}
		});
		
		showTotal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				double total = DB.GetTotal();
				Toast.makeText(getBaseContext(), "Total: $" + total, Toast.LENGTH_LONG).show();
			}
		});
		
		showProducts.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				List<HashMap<String, String>> nameList = DB.GetAllProducts();

				adapterList = new ArrayAdapter<HashMap<String, String>>(DatabaseDebug.this, android.R.layout.simple_list_item_1, nameList);

				ListView listView = (ListView) findViewById(R.id.listView_dbdebugger);
				listView.setAdapter(adapterList);
			}
		});
	}	
}
