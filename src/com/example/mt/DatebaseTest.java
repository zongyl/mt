package com.example.mt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.utils.SQLiteHelper;
/**
 * SQLite test
 * @author pc
 *
 */
public class DatebaseTest extends Activity{
	
	private static String TAG = "DatebaseTest";
	
	private Button btn_test;
	
	private SQLiteHelper sqlite;
	
	private SQLiteDatabase db;
	
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlite_main);
		
		context = this;
		
		btn_test = (Button) findViewById(R.id.btn_sqlite);
		btn_test.setOnClickListener(new Click());
		findViewById(R.id.btn_sqlite_create).setOnClickListener(new Click());
		findViewById(R.id.btn_sqlite_add).setOnClickListener(new Click());
		findViewById(R.id.btn_sqlite_del).setOnClickListener(new Click());
		findViewById(R.id.btn_sqlite_update).setOnClickListener(new Click());
		findViewById(R.id.btn_sqlite_find).setOnClickListener(new Click());
		
	}

	public void test(){
		SQLiteDatabase db = openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);
		db.execSQL("drop table if exists users");
		db.execSQL("create table users(id integer primary key autoincrement, name varchar, age smallint)");
		db.execSQL("insert into users values (null, ?, ?)", new Object[]{"along", "1988"});
		
		ContentValues cv = new ContentValues();
		cv.put("name", "along1");
		cv.put("age", "19881");
		db.insert("users", null, cv);
		
//		cv = new ContentValues();
//		cv.put("age", 18);
//		db.update("users", cv, "name = ?", new String[]{"along"});
		db.close();
	}
	
	@SuppressLint("NewApi")
	public void createDataBase(){
		Log.d("DatabaseTest", "createDataBase");
		SQLiteHelper sqlite = new SQLiteHelper(this, "dbname.db", null, 1);
		
		//sqlite.getDatabaseName();
		
		SQLiteDatabase db = sqlite.getWritableDatabase();
		db.execSQL("create table users23(id integer primary key autoincrement, name varchar, age smallint)");
		
		//sqlite.onCreate(db);
		sqlite.close();
		/*ContentValues values = new ContentValues();
		values.put("", "");
		sqlite.insert("", values);*/
	}
	
	class Click implements OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_sqlite:
				Log.d(TAG, "sqlite");
				sqlite = new SQLiteHelper(context, "mt.db", null, 1);
				break;
			case R.id.btn_sqlite_create:
				Log.d(TAG, "sqlite crete...");
				db = sqlite.getWritableDatabase();
				db.execSQL("create table if not exists mt_users(id integer primary key autoincrement, name varchar, age smallint)");
				break;
			case R.id.btn_sqlite_add:
				Log.d(TAG, "sqlite add...");
				ContentValues values = new ContentValues();
				values.put("name", "zongyl" + System.currentTimeMillis());
				values.put("age", 1);
				sqlite.insert("mt_users", values);
				break;
			case R.id.btn_sqlite_del:
				Log.d(TAG, "sqlite del...");
				break;
			case R.id.btn_sqlite_update:
				Log.d(TAG, "sqlite update...");
				break;
			case R.id.btn_sqlite_find:
				Log.d(TAG, "sqlite find...");
				break;

			default:
				break;
			}
		}
	}
	
}