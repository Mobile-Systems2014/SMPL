package com.application.smpl.database.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.appcompat.R.string;
import android.widget.Toast;

public class StartUpDataBaseAdapter {
	public SQLiteDatabase db;
	DBAdapter instance;

	public StartUpDataBaseAdapter(Context _context) {
		instance = new DBAdapter(_context);
		instance.open();
		db = instance.getDatabaseInstance();
	}
	
	 public void InsertProducts()
     {
		 String[] products = new String[]{"apple","bread","pizza","orange","milk","eggs","butter","chicken","fish","sprite"};
		 double[] price = new double[]{2.99,1.00,2.45,1.50,3.89,1.89,1.39,5.99,8.99,4.99};
		 String[] pCode = new String[]{"112","113","114","115","116","117","118","119","120","121"};
		 int[] pType = new int[]{6,1,4,6,5,5,5,3,3,2};
		 String[] isle = new String[]{"1","2","3","1","4","4","4","5","5","6"};
		 
		 long status = 0;
		 int count = 0;
    	 ContentValues newValues = new ContentValues();
    	 
    	 for(int index = 0; index < products.length; index++)
    	 {
			newValues.put(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_PRODUCTNAME,
					products[index]);
			newValues.put(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_PRICE,
					price[index]);
			newValues.put(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_PRODUCTCODE,
					pCode[index]);
			newValues.put(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_TYPE,
					pType[index]);
			newValues.put(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_ISLE,
					isle[index]);

			status = db.insert(DBAdapter.TABLE_MASTERLISTOFPRODUCTS, null, newValues);

			if (status == -1) {
				// do nothing
			} else {
				count++;
			}
		}

		Toast.makeText(instance.context, "Count" + count, Toast.LENGTH_LONG)
				.show();
     }

	public void InsertpTypes() {		
		ContentValues newValues = new ContentValues();
		String[] pType = new String[] { "Miscellaneous", "Bread/Bakery", "Beverages",
				"Meats", "Frozen Foods", "Dairy", "Produce" };

		for (int index = 0; index < pType.length; index++) 
		{
			newValues.put(DBAdapter.FOODTYPE_COLUMN_TYPE, pType[index]);
		}
		db.insert(DBAdapter.TABLE_FOODTYPE, null, newValues);
	}

	public ArrayList<HashMap<String, String>> GetAllProducts() {
		List<String> nameList = new ArrayList<String>();
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		String where = null;
		Cursor c = db.query(true, DBAdapter.TABLE_MASTERLISTOFPRODUCTS, DBAdapter.MASTERLISTOFPRODUCTS_ALLCOLUMNS,where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}

		while (c.isAfterLast() == false) {
			HashMap<String, String> map = new HashMap<String, String>();
			String pName = c.getString(c.getColumnIndex(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_PRODUCTNAME));
			String pType = c.getString(c.getColumnIndex(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_TYPE));
			double price = c.getDouble(c.getColumnIndex(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_PRICE));

			map.put("ProductName: ", pName);
			map.put("ProductType: ", pType);
			map.put("Price", String.valueOf(price));
			nameList.add(pName);
			mylist.add(map);
			c.moveToNext();
		}
/*		
		 if (c.moveToFirst()) {
		        do {
		            HashMap<String, String> map = new HashMap<String, String>();
		            for(int i=0; i<c.getColumnCount();i++)
		            {
		                map.put(c.getColumnName(i), cursor.getString(i));
		            }

		            maplist.add(map);
		        } while (cursor.moveToNext());
		    }
		    db.close();
		    // return contact list
		    return maplist;*/  
		return mylist;
	}
	
	public void SaveToList(String product)
	{
		ContentValues newValues = new ContentValues();
		int id = GetProduct(product);
		boolean update = UpdateOrNah(id);
		
		
		if(update)
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			int newQuantity;
			// Assign values for each row.
			//updatedValues.put(DBAdapter.MLSLPRODUCTS_COLUMN_QUANTITY, newQuantity);

			String where = "MLID= ?";
			db.update(DBAdapter.TABLE_MLSLPRODUCTS, updatedValues, where, new String[] { String.valueOf(id) });
		}
		else
		{
			newValues.put(DBAdapter.MLSLPRODUCTS_COLUMN_LISTID, 0);
			newValues.put(DBAdapter.MLSLPRODUCTS_COLUMN_QUANTITY, 1);
			newValues.put(DBAdapter.MLSLPRODUCTS_COLUMN_MLID, id);
			
			db.insert(DBAdapter.TABLE_MLSLPRODUCTS, null, newValues);
		}
	}
	
	public int GetProduct(String productCode) {
		Cursor cursor = db.query(DBAdapter.TABLE_MASTERLISTOFPRODUCTS, null, " PRODUCTCODE=?",
				new String[] { productCode }, null, null, null);
		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
		}
		cursor.moveToFirst();
		int id = cursor.getInt(cursor.getColumnIndex(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_PRODUCTCODE));
		String productName = cursor.getString(cursor.getColumnIndex(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_PRODUCTNAME));
		cursor.close();

		Toast.makeText(instance.context, "Added    " + productName, Toast.LENGTH_LONG).show();
		return id;
	}

	public double GetTotal()
	{
		String where = null;
		double total = 0.0;
		Cursor c = db.query(true, DBAdapter.TABLE_MASTERLISTOFPRODUCTS, DBAdapter.MASTERLISTOFPRODUCTS_ALLCOLUMNS,where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}

		while (c.isAfterLast() == false) {
			double price = c.getDouble(c.getColumnIndex(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_PRICE));
			total += price;
			
			c.moveToNext();
		}
		return total;
		
	}
	
	public boolean UpdateOrNah(int mlId)
	{
		Cursor cursor = db.query(DBAdapter.TABLE_MLSLPRODUCTS, null, " MLID=?",
				new String[] { String.valueOf(mlId)}, null, null, null);
		if (cursor.getCount() < 1) // Id Not Exist
		{
			cursor.close();
			return false;
		}
		return true;
	}
	
	

	
	
	public int deleteEntry(String UserName) {
		String where = "USERNAME=?";
		int numberOFEntriesDeleted = db.delete("TABLE", where,
				new String[] { UserName });
		return numberOFEntriesDeleted;
	}

	public void GetSinlgeEntry(String userName) {
//		Cursor cursor = db.query(DBAdapter.TABLE_LOGIN, null, " USERNAME=?",
//				new String[] { userName }, null, null, null);
//		if (cursor.getCount() < 1) // UserName Not Exist
//		{
//			cursor.close();
//			return "NOT EXIST";
//		}
//		cursor.moveToFirst();
//		String password = cursor.getString(cursor
//				.getColumnIndex(DBAdapter.LOGIN_COLUMN_PASSWORD));
//		cursor.close();
//		return password;
	}	

	public void updateEntry(String userName, String password) {
		// Define the updated row content.
		ContentValues updatedValues = new ContentValues();
		// Assign values for each row.
		updatedValues.put("USERNAME", userName);
		updatedValues.put("COLUMN_PASSWORD", password);

		String where = "USERNAME = ?";
		db.update("TABLE", updatedValues, where, new String[] { userName });
	}
}
