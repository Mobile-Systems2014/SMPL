package com.application.smpl.database.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class StartUpDataBaseAdapter {
	public SQLiteDatabase db;
	DBAdapter instance;

	public StartUpDataBaseAdapter(Context _context) {
		instance = new DBAdapter(_context);
		instance.open();
		db = instance.getDatabaseInstance();
	}
	
	 public void InsertFirstLogin(String first,String last,String weight,String height,String pin)
     {
//    	 ContentValues newValues = new ContentValues();
    	 
//    	 newValues.put(DBAdapter.INFO_COLUMN_FIRSTNAME, first);
//    	 newValues.put(DBAdapter.INFO_COLUMN_LASTNAME, last);
//    	 newValues.put(DBAdapter.INFO_COLUMN_WEIGHT, weight);
//    	 newValues.put(DBAdapter.INFO_COLUMN_HEIGHT, height);
//    	 newValues.put(DBAdapter.INFO_COLUMN_PIN, pin);
//    	 newValues.put(DBAdapter.INFO_COLUMN_FIRSTLOGIN, 0);
    	 
    	 //Insert Information
//    	 long status = db.insert(DBAdapter.TABLE_LOGIN, null, newValues);
//    	 
//    	 if(status == -1)
//    	 {
//    		 Toast.makeText(instance.context, "Not Inserted FirstLogin", Toast.LENGTH_LONG).show();
//    	 }
//    	 else
//    	 {
//    		 Toast.makeText(instance.context, "Success", Toast.LENGTH_LONG).show();
//    	 } 
     }

	public void insertPin(String pin, String userName) {
//		Cursor cursor = db.query(DBAdapter.TABLE_LOGIN, null, " USERNAME=?",
//				new String[] { userName }, null, null, null);
//		if (cursor.getCount() < 1) // UserName Not Exist
//		{
//			cursor.close();
//		}
//
//		ContentValues newValue = new ContentValues();
//		newValue.put(DBAdapter.INFO_COLUMN_PIN, pin);
//		String where = "USERNAME= '" + userName + "'";
//		db.update(DBAdapter.TABLE_LOGIN, newValue, where, null);
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

	public void GetPin(String userName) {
//		Cursor cursor = db.query(DBAdapter.TABLE_LOGIN, null, " USERNAME=?",
//				new String[] { userName }, null, null, null);
//		if (cursor.getCount() < 1) // UserName Not Exist
//		{
//			cursor.close();
//		}
//		cursor.moveToFirst();
//		String pin = cursor.getString(cursor
//				.getColumnIndex(DBAdapter.INFO_COLUMN_PIN));
//		cursor.close();
//
//		return pin;
	}

	public void GetLoginIds() {
//		 String where = null;
//		 Cursor c = db.query(true, DBAdapter.TABLE_LOGIN, DBAdapter.LOGIN_ALLCOLUMNS,
//		 where, null, null, null, null, null);
//		 if (c != null) {
//		 c.moveToFirst();
//		 }
//		 return c;
	}
}
