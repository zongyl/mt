package com.example.mt;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class BuildFieldActivity extends Activity{

	Button btn;

	String context;
	
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.build_main);
		
		btn = (Button) findViewById(R.id.btn_build);
		
		tv = (TextView) findViewById(R.id.tv_build_fields);
		
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				Log.v("........", "QQQQQQQQQQQQQQQ" + new Date());
				Log.v("........", "QQQQQQQQQQQQQQQ" + calendar.getTime());
				
				int day = calendar.get(Calendar.DATE);
				Log.v("........", "day:" + day);
				calendar.set(Calendar.DATE, day - 1);
				Log.v("........", "QQQQQQQQQQQQQQQ" + calendar.getTime());
			}
		});
		
		Field[] fields = Build.class.getDeclaredFields();
		
		for(Field f : fields){
			f.setAccessible(true);
			try {
				Log.d("", f.getName()+":"+f.get(null));
				context += f.getName()+":"+f.get(null) + "\n";
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}

		tv.setText(context);
		
	}
}
