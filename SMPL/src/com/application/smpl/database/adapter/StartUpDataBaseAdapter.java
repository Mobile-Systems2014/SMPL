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

	public void InsertProducts() {
		String[] products = new String[] { "apple", "bread", "pizza", "orange",
				"milk", "eggs", "butter", "chicken", "fish", "sprite" };
		double[] price = new double[] { 2.99, 1.00, 2.45, 1.50, 3.89, 1.89,
				1.39, 5.99, 8.99, 4.99 };
		String[] pCode = new String[] { "112", "113", "114", "115", "116",
				"117", "118", "119", "120", "121" };
		int[] pType = new int[] { 6, 1, 4, 6, 5, 5, 5, 3, 3, 2 };
		String[] isle = new String[] { "1", "2", "3", "10", "4", "4", "4", "7",
				"5", "6" };

		long status = 0;
		int count = 0;
		ContentValues newValues = new ContentValues();

		for (int index = 0; index < products.length; index++) {
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

			status = db.insert(DBAdapter.TABLE_MASTERLISTOFPRODUCTS, null,
					newValues);

			if (status == -1) {
				// do nothing
			} else {
				count++;
			}
		}
	}

	public void InsertpTypes() {
		ContentValues newValues = new ContentValues();
		String[] pType = new String[] { "Miscellaneous", "Bread/Bakery",
				"Beverages", "Meats", "Frozen Foods", "Dairy", "Produce" };

		for (int index = 0; index < pType.length; index++) {
			newValues.put(DBAdapter.FOODTYPE_COLUMN_TYPE, pType[index]);
		}
		db.insert(DBAdapter.TABLE_FOODTYPE, null, newValues);
	}

	public ArrayList<HashMap<String, String>> GetAllProducts() {
		List<String> nameList = new ArrayList<String>();
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		String where = null;
		Cursor c = db.query(true, DBAdapter.TABLE_MASTERLISTOFPRODUCTS,
				DBAdapter.MASTERLISTOFPRODUCTS_ALLCOLUMNS, where, null, null,
				null, null, null);
		if (c != null) {
			c.moveToFirst();
		}

		while (c.isAfterLast() == false) {
			HashMap<String, String> map = new HashMap<String, String>();
			String pName = c
					.getString(c
							.getColumnIndex(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_PRODUCTNAME));
			String pType = c
					.getString(c
							.getColumnIndex(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_TYPE));
			double price = c
					.getDouble(c
							.getColumnIndex(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_PRICE));

			map.put("ProductName: ", pName);
			map.put("ProductType: ", pType);
			map.put("Price", String.valueOf(price));
			nameList.add(pName);
			mylist.add(map);
			c.moveToNext();
		}
		return mylist;
	}

	public ArrayList<HashMap<String, String>> GetShoppingList() {
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		String where = null;
		Cursor c = db.query(true, DBAdapter.TABLE_MLSLPRODUCTS,
				DBAdapter.MLSLPRODUCTS_ALLCOLUMNS, where, null, null, null,
				null, null);
		if (c != null) {
			c.moveToFirst();
		}

		while (c.isAfterLast() == false) {
			HashMap<String, String> map = new HashMap<String, String>();

			String itemQuantity = c.getString(c
					.getColumnIndex(DBAdapter.MLSLPRODUCTS_COLUMN_QUANTITY));
			String productId = c.getString(c
					.getColumnIndex(DBAdapter.MLSLPRODUCTS_COLUMN_MLID));
			String product = getListProductName(productId);

			map.put("Quantity", itemQuantity);
			map.put("Product", String.valueOf(product));
			mylist.add(map);
			c.moveToNext();
		}
		return mylist;
	}

	public String GetShoppingListByIsle() {

		return "toDo";

	}

	public void SaveToList(String product) {
		int id = GetProduct(product);
		boolean update = UpdateOrNah(id);

		if (update) {
			ContentValues updatedValues = new ContentValues();
			int newQuantity = GetQuantity(id) + 1;

			updatedValues.put(DBAdapter.MLSLPRODUCTS_COLUMN_QUANTITY,
					newQuantity);

			String where = "MLID= ?";
			db.update(DBAdapter.TABLE_MLSLPRODUCTS, updatedValues, where,
					new String[] { String.valueOf(id) });
		} else {
			ContentValues newValues = new ContentValues();

			newValues.put(DBAdapter.MLSLPRODUCTS_COLUMN_LISTID, 0);
			newValues.put(DBAdapter.MLSLPRODUCTS_COLUMN_LISTNAME, "MyList1");
			newValues.put(DBAdapter.MLSLPRODUCTS_COLUMN_QUANTITY, 1);
			newValues.put(DBAdapter.MLSLPRODUCTS_COLUMN_MLID, id);

			db.insert(DBAdapter.TABLE_MLSLPRODUCTS, null, newValues);
		}
	}

	public void RemoveFromList(String product) {
		int id = GetProduct(product);
		int quantity = GetQuantity(id);
		
		if(quantity > 1)
		{
			ContentValues updatedValues = new ContentValues();
			int newQuantity = GetQuantity(id) - 1;
	
			updatedValues.put(DBAdapter.MLSLPRODUCTS_COLUMN_QUANTITY, newQuantity);
	
			String where = "MLID=?";
			db.update(DBAdapter.TABLE_MLSLPRODUCTS, updatedValues, where,
					new String[] { String.valueOf(id) });
		}
		else
		{
			String where = "MLID=?";
			db.delete(DBAdapter.TABLE_MLSLPRODUCTS, where,
					new String[] { String.valueOf(id) });
		}
	}

	public int GetQuantity(int id) {
		// TODO check for mlid and listid only supports 1 list right now
		Cursor cursor = db.query(DBAdapter.TABLE_MLSLPRODUCTS, null, "MLID=?",
				new String[] { String.valueOf(id) }, null, null, null);
		if (cursor.getCount() < 1) // not exist
		{
			cursor.close();
		}
		cursor.moveToFirst();
		int quantity = cursor.getInt(cursor
				.getColumnIndex(DBAdapter.MLSLPRODUCTS_COLUMN_QUANTITY));

		return quantity;
	}

	public int GetProduct(String productCode) {
		Cursor cursor = db.query(DBAdapter.TABLE_MASTERLISTOFPRODUCTS, null,
				" PRODUCTCODE=?", new String[] { productCode }, null, null,
				null);
		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
		}
		cursor.moveToFirst();
		int id = cursor.getInt(cursor
				.getColumnIndex(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_MLID));
		String productName = cursor
				.getString(cursor
						.getColumnIndex(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_PRODUCTNAME));
		cursor.close();

		return id;
	}

	public boolean UpdateOrNah(int mlId) {
		Cursor cursor = db.query(DBAdapter.TABLE_MLSLPRODUCTS, null, " MLID=?",
				new String[] { String.valueOf(mlId) }, null, null, null);
		if (cursor.getCount() < 1) // Id Not Exist
		{
			return false;
		}
		return true;
	}

	public double GetTotal() {
		// get all shopping list
		String where = null;
		double total = 0.0;
		Cursor c = db.query(true, DBAdapter.TABLE_MLSLPRODUCTS,
				DBAdapter.MLSLPRODUCTS_ALLCOLUMNS, where, null, null, null,
				null, null);
		if (c != null) {
			c.moveToFirst();
		}

		while (c.isAfterLast() == false) {
			String productId = c.getString(c
					.getColumnIndex(DBAdapter.MLSLPRODUCTS_COLUMN_MLID));
			int quantity = c.getInt(c
					.getColumnIndex(DBAdapter.MLSLPRODUCTS_COLUMN_QUANTITY));

			for (int index = 0; index < quantity; index++) {
				double price = getListPrice(productId);
				total += price;
			}
			c.moveToNext();
		}
		return total;
	}

	// get list product details multiple needed for each property
	// Price
	// Name
	// Isle
	public double getListPrice(String pId) {
		double price = 0;
		Cursor c = db.query(DBAdapter.TABLE_MASTERLISTOFPRODUCTS, null,
				"MLID=?", new String[] { String.valueOf(pId) }, null, null,
				null);
		if (c != null) {
			c.moveToFirst();
		}

		while (c.isAfterLast() == false) {
			price = c
					.getDouble(c
							.getColumnIndex(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_PRICE));
			c.moveToNext();
		}
		return price;
	}

	public String getListProductName(String pId) {
		String name = "";
		Cursor c = db.query(DBAdapter.TABLE_MASTERLISTOFPRODUCTS, null,
				"MLID=?", new String[] { String.valueOf(pId) }, null, null,
				null);
		if (c != null) {
			c.moveToFirst();
		}

		while (c.isAfterLast() == false) {
			name = c.getString(c
					.getColumnIndex(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_PRODUCTNAME));
			c.moveToNext();
		}
		return name;
	}

	public String getListProductIsle(String pId) {
		String name = "";
		Cursor c = db.query(DBAdapter.TABLE_MASTERLISTOFPRODUCTS, null,
				"MLID=?", new String[] { String.valueOf(pId) }, null, null,
				null);
		if (c != null) {
			c.moveToFirst();
		}

		while (c.isAfterLast() == false) {
			name = c.getString(c
					.getColumnIndex(DBAdapter.MASTERLISTOFPRODUCTS_COLUMN_ISLE));
			c.moveToNext();
		}
		return name;
	}

	public int deleteEntry(String UserName) {
		String where = "USERNAME=?";
		int numberOFEntriesDeleted = db.delete("TABLE", where,
				new String[] { UserName });
		return numberOFEntriesDeleted;
	}

	public void GetSinlgeEntry(String userName) {
		// Cursor cursor = db.query(DBAdapter.TABLE_LOGIN, null, " USERNAME=?",
		// new String[] { userName }, null, null, null);
		// if (cursor.getCount() < 1) // UserName Not Exist
		// {
		// cursor.close();
		// return "NOT EXIST";
		// }
		// cursor.moveToFirst();
		// String password = cursor.getString(cursor
		// .getColumnIndex(DBAdapter.LOGIN_COLUMN_PASSWORD));
		// cursor.close();
		// return password;
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
