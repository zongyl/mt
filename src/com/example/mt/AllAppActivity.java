package com.example.mt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

@SuppressLint({ "NewApi", "SimpleDateFormat" })
public class AllAppActivity extends Activity{

	public static final String TAG = "AllAppActivity";
	
	TextView tv_allapp;
	
	StringBuffer sbf;
	
	MyHandler handler;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	String temp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allapp_main);
		
		tv_allapp = (TextView) findViewById(R.id.tv_allapp);
		tv_allapp.setMovementMethod(new ScrollingMovementMethod());
		
		findViewById(R.id.btn_tt).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//tv_allapp.setText("loading...");
				//tv_allapp.setText(sbf.toString());
				
				handler = new MyHandler();
				loop();
				
			}
		});
	}
	
	private void loop(){
		//sbf = new StringBuffer();
		int index = 0;
		List<PackageInfo> packageInfos = getPackageManager().getInstalledPackages(0);
		for(PackageInfo packageInfo : packageInfos){
			index++;
			temp = toString(packageInfo, index);
			new Thread(runnable).start();
			//sbf.append(temp);
		}
	}
	
	private String toString(PackageInfo pi, int index){
		StringBuffer sb = new StringBuffer();
		sb.append("versionCode:" + pi.versionCode + "\n");
		sb.append("versionName:" + pi.versionName + "\n");
		sb.append("packageName:" + pi.packageName + "\n");
		sb.append("describeContents:" + pi.describeContents() + "\n");
		sb.append("lastUpdateTime:" + sdf.format(new Date(pi.lastUpdateTime)) + "\n");
		sb.append("firstInstallTime:" + sdf.format(new Date(pi.firstInstallTime)) + "\n");
		sb.append("label:" + pi.applicationInfo.loadLabel(getPackageManager()) + "\n");
		sb.append("sourceDir:" + pi.applicationInfo.sourceDir + "\n");
		sb.append(index + "\n");
		Log.d(TAG, "===============================================================================");
		Log.d(TAG, "debug!"+pi.versionCode);
		Log.d(TAG, "debug!"+pi.versionName);
		Log.d(TAG, "debug!"+pi.packageName);
		Log.d(TAG, "debug!"+pi.sharedUserId);
		Log.d(TAG, "debug!"+pi.sharedUserLabel);
		Log.d(TAG, "debug!"+pi.describeContents());
		Log.d(TAG, "debug!"+sdf.format(new Date(pi.lastUpdateTime)));
		Log.d(TAG, "debug!"+sdf.format(new Date(pi.firstInstallTime)));
		Log.d(TAG, "debug!"+pi.applicationInfo.loadLabel(getPackageManager()).toString());
		Log.d(TAG, "debug!"+pi.applicationInfo.loadIcon(getPackageManager()));
		Log.d(TAG, "debug!"+pi.applicationInfo.sourceDir);
		return sb.toString();
	}
	
	class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			String apk = msg.getData().getString("apkInfo");
			Log.d(TAG, apk);
			tv_allapp.setText(apk);
		}
	};
	
	Runnable runnable = new Runnable(){
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Message msg = new Message();
			Bundle data = new Bundle();
			data.putString("apkInfo", temp);
			msg.setData(data);
			handler.sendMessage(msg);
		}
	};
	
}
