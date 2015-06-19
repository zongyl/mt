package com.example.mt;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.PhoneNumberAdapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.widget.ListView;

/**
 * 读取联系人
 * @author pc
 *
 */
public class PhoneNumberActivity extends Activity {

	ListView listView;
	Cursor cursor;
	
	final String[] phones = {
			Phone.DISPLAY_NAME, 
			Phone.NUMBER, 
			Phone.PHOTO_ID, 
			Phone.CONTACT_ID};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.phonenumber_main);
		
		listView = (ListView)findViewById(R.id.phonenumberListview);
		
		cursor = getContentResolver().query(Phone.CONTENT_URI, phones, null, null, null);
		
		List<String> pns = new ArrayList<String>();
		String display_name;
		String number;
		while(cursor.moveToNext()){
			display_name = cursor.getString(cursor.getColumnIndex(phones[0]));
			number = cursor.getString(cursor.getColumnIndex(phones[1]));
			Log.i("", display_name);
			Log.i("", number);
			pns.add(display_name+"\n"+number);
		}
		
		listView.setAdapter(new PhoneNumberAdapter(this, pns));
	}
}