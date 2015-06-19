package com.example.mt;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

public class SMSActivity extends Activity {

	public static final String CONTENT_URI_SMS = "content://sms";
	public static final String CONTENT_URI_SMS_INBOX = "content://sms/inbox";
	public static final String CONTENT_URI_SMS_SENT = "content://sms/sent";

	Cursor cursor;
	int index, type, i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		cursor = getContentResolver().query(Uri.parse(CONTENT_URI_SMS), null, null, null, null);
		
		while(cursor.moveToNext()){
			
		}
		
	}
}