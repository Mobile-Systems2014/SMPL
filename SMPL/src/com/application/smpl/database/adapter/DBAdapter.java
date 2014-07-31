package com.application.smpl.database.adapter;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
	static final String DATABASE_NAME = "SMPL.db";
	static final int DATABASE_VERSION = 7;
	
	public static final String TABLE_MASTERLISTOFPRODUCTS = "MASTERLISTOFPRODUCTS";
	public static final String TABLE_SHOPPINGLIST = "SHOPPINGLIST";
	public static final String TABLE_FOODTYPE = "FOODTYPE";
	public static final String TABLE_MLSLPRODUCTS = "MLSLPRODUCTS"; 
	
	//MASTER LIST table columns
    public static final String MASTERLISTOFPRODUCTS_COLUMN_MLID = "MLID";
    public static final String MASTERLISTOFPRODUCTS_COLUMN_PRODUCTNAME = "PRODUCTNAME";
    public static final String MASTERLISTOFPRODUCTS_COLUMN_PRICE = "PRICE";
    public static final String MASTERLISTOFPRODUCTS_COLUMN_PRODUCTCODE = "PRODUCTCODE";
    public static final String MASTERLISTOFPRODUCTS_COLUMN_TYPE = "TYPE";
    public static final String MASTERLISTOFPRODUCTS_COLUMN_ISLE = "ISLE";
    
    //SHOPPING LIST table columns
    public static final String SHOPPINGLIST_COLUMN_LISTID = "LISTID";
    public static final String SHOPPINGLIST_COLUMN_LISTNAME = "LISTNAME";
    
    //FOOD TYPE table columns
    public static final String FOODTYPE_COLUMN_FOODID = "FOODID";
    public static final String FOODTYPE_COLUMN_TYPE = "TYPE";
    
    //ML_SLPRODUCTS table columns
    public static final String MLSLPRODUCTS_COLUMN_LISTID = "LISTID";
    public static final String MLSLPRODUCTS_COLUMN_LISTNAME = "LISTNAME";
    public static final String MLSLPRODUCTS_COLUMN_QUANTITY = "QUANTITY";
    public static final String MSLSPRODUCTS_COLUMN_CHECKED = "CHECKED"; 
    public static final String MLSLPRODUCTS_COLUMN_MLID = "MLID";
    
	public static String[] MASTERLISTOFPRODUCTS_ALLCOLUMNS = {
			MASTERLISTOFPRODUCTS_COLUMN_MLID,
			MASTERLISTOFPRODUCTS_COLUMN_PRODUCTNAME,
			MASTERLISTOFPRODUCTS_COLUMN_PRICE,
			MASTERLISTOFPRODUCTS_COLUMN_PRODUCTCODE,
			MASTERLISTOFPRODUCTS_COLUMN_TYPE, MASTERLISTOFPRODUCTS_COLUMN_ISLE };
	
	public static String[] MLSLPRODUCTS_ALLCOLUMNS = {
			MLSLPRODUCTS_COLUMN_LISTNAME,
			MLSLPRODUCTS_COLUMN_QUANTITY,
			MLSLPRODUCTS_COLUMN_MLID };
	
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String CREATE_TABLE_MASTERLISTOFPRODUCTS = " create table " 
    		+ TABLE_MASTERLISTOFPRODUCTS + "("
    		+ MASTERLISTOFPRODUCTS_COLUMN_MLID + " integer primary key autoincrement, " 
    		+ MASTERLISTOFPRODUCTS_COLUMN_PRODUCTNAME + " text, "
    		+ MASTERLISTOFPRODUCTS_COLUMN_PRICE + " decimal default 1, "
    		+ MASTERLISTOFPRODUCTS_COLUMN_PRODUCTCODE + " text, "
    		+ MASTERLISTOFPRODUCTS_COLUMN_ISLE + " text, "
    		+ MASTERLISTOFPRODUCTS_COLUMN_TYPE + " integer);";
    
    static final String CREATE_TABLE_SHOPPINGLIST = " create table " 
    		+ TABLE_SHOPPINGLIST + "("
    		+ SHOPPINGLIST_COLUMN_LISTID + " integer primary key autoincrement, " 
    		+ SHOPPINGLIST_COLUMN_LISTNAME + " text);";
    
    static final String CREATE_TABLE_FOODTYPE = " create table " 
    		+ TABLE_FOODTYPE + "("
    		+ FOODTYPE_COLUMN_FOODID + " integer primary key autoincrement, " 
    		+ FOODTYPE_COLUMN_TYPE + " text);";
    
    static final String CREATE_TABLE_MLSLPRODUCTS = " create table " 
    		+ TABLE_MLSLPRODUCTS + "("
    		+ MLSLPRODUCTS_COLUMN_LISTID + " integer, " 
    		+ MLSLPRODUCTS_COLUMN_LISTNAME + " text, "
			+ MLSLPRODUCTS_COLUMN_QUANTITY + " numeric default 0, "
			+ MSLSPRODUCTS_COLUMN_CHECKED + "integer, "
    		+ MLSLPRODUCTS_COLUMN_MLID + " text, primary key(" + MLSLPRODUCTS_COLUMN_LISTID + "," + MLSLPRODUCTS_COLUMN_MLID + "));";
    
    
	// Variable to hold the database instance
	public SQLiteDatabase db;
	// Context of the application using the database.
	public final Context context;
	// Database open/upgrade helper
	private DataBaseHelper dbHelper;

	public DBAdapter(Context _context) {
		context = _context;
		dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
				DATABASE_VERSION);
	}

	public DBAdapter open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		db.close();
	}

	public SQLiteDatabase getDatabaseInstance() {
		return db;
	}
}
