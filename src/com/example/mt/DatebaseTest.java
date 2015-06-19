package com.example.mt;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
/**
 * SQLite test
 * @author pc
 *
 */
public class DatebaseTest extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
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

}