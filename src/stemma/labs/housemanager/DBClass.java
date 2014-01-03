package stemma.labs.housemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBClass extends SQLiteOpenHelper {
	
	private static final int DBversion = 1;
	private static final String DBname = "ReminderDB" ;
	private static final String TABLE_NAME = "ReminderTable" ;
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_TYPE = "type";
	private static final String COLUMN_TIMEOUT = "timeout";
	private static final String COLUMN_SWITCH = "switch";
	private static final String COLUMN_NAME_NULLABLE = "table_NULL";
	
	private static final String TEXT_TYPE = " TEXT NOT NULL";
	private static final String COMMA_SEP = ",";
	
	private static SQLiteDatabase db;
	
	
	private static final String query_create  =
		    "CREATE TABLE " + TABLE_NAME + " (" +
		    COLUMN_ID + " INTEGER PRIMARY KEY," +
		    COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
		    COLUMN_TYPE + TEXT_TYPE + COMMA_SEP + 
		    COLUMN_TIMEOUT + TEXT_TYPE + COMMA_SEP +
		    COLUMN_SWITCH + " )";
	private static final String allColumns [] = {COLUMN_ID, 
		COLUMN_NAME, COLUMN_TYPE, COLUMN_TIMEOUT, COLUMN_SWITCH	
	};
	
	private static Cursor C;

	


	public DBClass(Context context) {
		super(context, DBname, null, DBversion);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public long writeToDB (RowType rt){
		long newRowID;
		
		SQLiteDatabase db = getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put(COLUMN_ID, rt.getsno());
		values.put(COLUMN_NAME,rt.getName());
		values.put(COLUMN_TYPE,rt.getType());
		values.put(COLUMN_TIMEOUT,Long.toString(rt.getTimeout()));
		values.put(COLUMN_SWITCH,Boolean.toString(rt.getSwitch()));
		
		newRowID = db.insert(TABLE_NAME,COLUMN_NAME_NULLABLE,values) ;
		
		return newRowID;		
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(query_create);

		// TODO Auto-generated method stub
		
	}
	 
	
	public RowType readFromDB(int rowID)
	
	
	{
		//Log.v("tag:DBC", "I am here");
		RowType rt = new RowType();
		SQLiteDatabase db = getReadableDatabase();
		C = db.query(TABLE_NAME, allColumns, COLUMN_ID + " = " + rowID, null,null,null,null);
		if(C.moveToFirst())
		{
			
		rt.setsno(C.getInt(C.getColumnIndexOrThrow(COLUMN_ID)));
		rt.setName(C.getString(C.getColumnIndexOrThrow(COLUMN_NAME)));
		rt.setType(C.getString(C.getColumnIndexOrThrow(COLUMN_TYPE)));
		rt.setTimeout(Long.parseLong(C.getString(C.getColumnIndexOrThrow(COLUMN_TIMEOUT))));
		rt.setSwitch(Boolean.parseBoolean(C.getString(C.getColumnIndexOrThrow(COLUMN_SWITCH))));
		return rt;
		
		}
		
		return null;
		
	}

	
	public void updateTable_switch (Boolean Switch_value, int rowID){
		
		SQLiteDatabase db = getReadableDatabase();
		
		ContentValues values = 	new ContentValues() ;
		values.put(COLUMN_SWITCH,Boolean.toString(Switch_value));
		
		String selection = COLUMN_ID + " Like ?";
		String[] selectionArgs = {String.valueOf(rowID)};
		
		db.update(TABLE_NAME, values, selection, selectionArgs);
		
	}
	
	public void updateTable (RowType rt, int rowID) {
		
		SQLiteDatabase db = getReadableDatabase();
		
		ContentValues values = new ContentValues();
		
		//values.put(COLUMN_ID, rt.getsno());
		values.put(COLUMN_NAME,rt.getName());
		values.put(COLUMN_TYPE,rt.getType());
		values.put(COLUMN_TIMEOUT,Long.toString(rt.getTimeout()));
		values.put(COLUMN_SWITCH,Boolean.toString(rt.getSwitch()));
		
		String selection = COLUMN_ID + " Like ?";
		String[] selectionArgs = {String.valueOf(rowID)};
		
		db.update(TABLE_NAME, values, selection, selectionArgs);		
	}
	
	public void deleteRow (int rowID) {
		
		SQLiteDatabase db = getReadableDatabase();
		
		String selection = COLUMN_ID + " Like ?";
		String[] selectionArgs = {String.valueOf(rowID)};
		
		db.delete(TABLE_NAME, selection, selectionArgs);
			
	}
	
	public void deleteTable ()
	{
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	}
	
	public int getRowID(int rowID){
		
		return rowID;
		
		
		
	}
	
   

}
	