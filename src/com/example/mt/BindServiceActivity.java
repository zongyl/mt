package com.example.mt;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.service.BindService;
import com.example.service.BindService.MyBinder;

public class BindServiceActivity extends Activity implements OnClickListener{

	public static final String TAG = "BindServiceActivity";
	
	Button bind, serviceStatus, unbind;
	
	BindService.MyBinder binder;
	
	Intent intent;
	
	ServiceConnection conn = new ServiceConnection() {
		/**
		 * 当Activity与Service断开连接时，回调该方法
		 */
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.v(TAG, "Service onServiceDisconnected!");
		}

		/**
		 *当Activity与Service连接成功，回调该方法 
		 */
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = (MyBinder) service;
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bindservice_main);
		bind = (Button) findViewById(R.id.bind);
		serviceStatus = (Button) findViewById(R.id.serviceState);
		unbind = (Button) findViewById(R.id.unbind);

		bind.setOnClickListener(this);
		serviceStatus.setOnClickListener(this);
		unbind.setOnClickListener(this);
		
		intent = new Intent();
		intent.setAction("com.example.service.BIND_SERVICE");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bind:
			bindService(intent, conn, Service.BIND_AUTO_CREATE);
			break;
		case R.id.serviceState:
			Log.v(TAG, "Service status:"+binder.getCount());
			break;
		case R.id.unbind:
			unbindService(conn);
			break;
		default:
			break;
		}
	}
}