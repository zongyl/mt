package com.example.mt;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


/**
 * 
 * 通话记录读取
 * @author pc
 *
 */
@SuppressLint("NewApi")
public class CallLogActivity extends Activity {

	public static final String CONTENT_URI_SMS = "content://sms";
	public static final String CONTENT_URI_SMS_INBOX = "content://sms/inbox";
	public static final String CONTENT_URI_SMS_SENT = "content://sms/sent";
	
	Cursor cursor;
	int index, type, i;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this, null));
		//readContent(CallLog.Calls.CONTENT_URI, null, "number = ?", new String[]{"1517"}, null, 40);
		readContent(Uri.parse(CONTENT_URI_SMS), null, null, null, "date desc ", 40);
	}
	
	
	public void readContent(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, int loopCount){
		cursor = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
		i = 0;
		while(cursor.moveToNext()){
			Log.i("log_index:", "——————————————————————————————"+i);
			Log.i("log_index:", "date:"+ sdf.format(new Date(Long.parseLong(cursor.getString(4)))));
			Log.i("log_index:", "date:"+ sdf.format(new Date(Long.parseLong(cursor.getString(5)))));
			
			for(String key : cursor.getColumnNames()){
				Log.i("key:", key);
				index = cursor.getColumnIndex(key);
				type = cursor.getType(index);
				switch (type) {
				case 0:
					Log.i("value", "null:"+cursor.getString(index));
					break;
				case 1:
					Log.i("value", "integer:"+cursor.getInt(index));
					break;
				case 2:
					Log.i("value", "float:"+cursor.getFloat(index));
					break;
				case 3:
					Log.i("value", "string:"+cursor.getString(index));
					break;
				case 4:
					Log.i("value", "blob:"+cursor.getBlob(index));
					break;

				default:
					break;
				}
			}
			i++;
			if(i > loopCount){
				break;
			}
		}
		cursor.close();
	}
	
	class MyView extends View{

		Context _context;
		TextView tv;
		public MyView(Context context, AttributeSet attrs) {
			super(context, attrs);
			// TODO Auto-generated constructor stub
			_context = context;
			tv = new TextView(_context);
			tv.setText("AAAAAAAAAAAAAAAAAA");
		}
		 
	}
}