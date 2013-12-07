package info.androidhive.googlemapsv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GPSDatabase {
	private Context context;
	private DbHelper dbHelper;
	public final String DBNAME="gps1";
	public final int DBVERSION=3;
	public SQLiteDatabase db;
	public final static String COLUMN1="locationId";
	public final static String COLUMN2="latitude";
	public final static String COLUMN3="longitude";
	public final static String COLUMN4="locationname";
	public final static String COLUMN5="locationsignal";
	
	
	public final String TABLENAME="location";
	public final String CREATERDB="create table location(locationId integer primary key autoincrement,latitude text not null, longitude text not null, locationname text not null, locationsignal text not null);";
	//const
	public GPSDatabase(Context context){
		this.context=context;
		dbHelper=new DbHelper(context);
	}
	public class DbHelper extends SQLiteOpenHelper{
		public DbHelper(Context context){
			super(context,DBNAME,null,DBVERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREATERDB);
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
		}
	}
	public long insertRows(String column2, String column3, String column4, String column5){
		ContentValues value=new ContentValues();
		value.put(COLUMN2, column2);
		value.put(COLUMN3, column3);
		value.put(COLUMN4, column4);
		value.put(COLUMN5, column5);
		
		return db.insert(TABLENAME,null,value);
	}
	public Cursor getAllRows(){
		Cursor cursor=db.query(TABLENAME, new String[]{COLUMN1,COLUMN2,COLUMN3, COLUMN4, COLUMN5}, null,null, null, null, null);
		return cursor;
	}
	public void open() throws  SQLException{
		db=        dbHelper.getWritableDatabase();
		//return true;
	}
	public void close(){
		dbHelper.close();
		//return true;
	}
}