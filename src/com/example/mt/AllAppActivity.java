package com.example.mt;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

@SuppressLint("NewApi")
public class AllAppActivity extends Activity{

	public static final String TAG = "AllAppActivity";
	
	TextView tv_allapp;
	
	StringBuffer sbf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allapp_main);
		
		tv_allapp = (TextView) findViewById(R.id.tv_allapp);
		tv_allapp.setMovementMethod(new ScrollingMovementMethod());
		
		findViewById(R.id.btn_tt).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_allapp.setText("loading...");
				loop();
				tv_allapp.setText(sbf.toString());
			}
		});
	}
	
	private void loop(){
		sbf = new StringBuffer();
		List<PackageInfo> packageInfos = getPackageManager().getInstalledPackages(0);
		for(PackageInfo packageInfo : packageInfos){
			sbf.append(toString(packageInfo));
		}
	}
	
	
	private String toString(PackageInfo pi){
		StringBuffer sb = new StringBuffer();
		sb.append("name:" + pi.packageName + "\n");
		sb.append("name:" + pi.applicationInfo.loadLabel(getPackageManager()) + "\n");
		Log.d(TAG, "===============================================================================");
		Log.d(TAG, "debug!"+pi.versionCode);
		Log.d(TAG, "debug!"+pi.versionName);
		Log.d(TAG, "debug!"+pi.packageName);
		Log.d(TAG, "debug!"+pi.sharedUserId);
		Log.d(TAG, "debug!"+pi.sharedUserLabel);
		Log.d(TAG, "debug!"+pi.packageName);
		Log.d(TAG, "debug!"+pi.describeContents());
		Log.d(TAG, "debug!"+pi.lastUpdateTime);
		Log.d(TAG, "debug!"+pi.firstInstallTime);
		Log.d(TAG, "debug!"+pi.applicationInfo.loadLabel(getPackageManager()).toString());
		Log.d(TAG, "debug!"+pi.applicationInfo.loadIcon(getPackageManager()));
		Log.d(TAG, "debug!"+pi.applicationInfo.sourceDir);
		return sb.toString();
	}
	
}
