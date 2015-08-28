package com.example.mt;

import com.example.http.Test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HttpActivity extends Activity implements OnClickListener{

	private static String TAG = "HttpActivity";
	
	Button http1, http0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http_main);

		http0 = (Button) findViewById(R.id.btn_http0);
		http1 = (Button) findViewById(R.id.btn_http1);

		http0.setOnClickListener(this);
		http1.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_http0:
			Log.d(TAG, "http0...........");
			new Test().test();
			break;
		case R.id.btn_http1:
			new Test().testOkHttp();
			Log.d(TAG, "http1...........");
			break;
		default:
			break;
		}
		
	};
}
