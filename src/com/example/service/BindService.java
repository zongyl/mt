package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BindService extends Service {

	public static final String TAG = "BindService";
	
	private int count;
	
	private boolean quit;
	
	private MyBinder binder = new MyBinder();
	
	public class MyBinder extends Binder{
		public int getCount(){
			return count;
		}
	} 
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.v(TAG, "Service onBind!");
		return binder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.v(TAG, "Service onCreate!");
		new Thread(){
			public void run() {
				while(!quit){
					try{
						Thread.sleep(1000);
					}catch(Exception e){
					}
					count++;
				}
			};
		}.start();
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.v(TAG, "Service onUnbind!");
		return true;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		quit = true;
		Log.v(TAG, "Service onDestroy!");
	}
}