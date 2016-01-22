package com.example.mt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.AppAdapter;

@SuppressLint({ "NewApi", "SimpleDateFormat" })
public class AllAppActivity extends Activity{

	public static final String TAG = "AllAppActivity";
	
	TextView tv_allapp, tv_currapp;
	
	StringBuffer sbf = new StringBuffer();
	
	MyHandler handler;
	
	ListView appList;
	
	ListAdapter adapter;
	
	ImageView icon;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	String temp, temp_all;
	
	Drawable temp_icon;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allapp_main);

		tv_currapp = (TextView) findViewById(R.id.tv_currapp);
		tv_allapp = (TextView) findViewById(R.id.tv_allapp);
		tv_allapp.setMovementMethod(new ScrollingMovementMethod());
		icon = (ImageView) findViewById(R.id.icon_currapp);
		
		appList = (ListView) findViewById(R.id.appList);
		List<PackageInfo> apkList = getPackageManager().getInstalledPackages(0);
		Log.d(TAG, "apkList.size()" + apkList.size());
		adapter = new AppAdapter(this, apkList);

		appList.setAdapter(adapter);
		
		findViewById(R.id.btn_tt).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//handler方式
//				handler = new MyHandler();
//				new Thread(runnable).start();

				//runOnUiThread
				new Thread(runnable1).start();
			}
		});
		
		findViewById(R.id.btn_getall).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alert(sbf.toString());
				tv_allapp.setText(sbf.toString());
			}
		});
		
	}
	
	public void alert(String text){
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
	
	public String apktoString(PackageInfo pi, int index){
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
		Log.d(TAG, "==============================================================================="+index);
//		Log.d(TAG, "debug!"+pi.versionCode);
//		Log.d(TAG, "debug!"+pi.versionName);
//		Log.d(TAG, "debug!"+pi.packageName);
//		Log.d(TAG, "debug!"+pi.sharedUserId);
//		Log.d(TAG, "debug!"+pi.sharedUserLabel);
//		Log.d(TAG, "debug!"+pi.describeContents());
//		Log.d(TAG, "debug!"+sdf.format(new Date(pi.lastUpdateTime)));
//		Log.d(TAG, "debug!"+sdf.format(new Date(pi.firstInstallTime)));
//		Log.d(TAG, "debug!"+pi.applicationInfo.loadLabel(getPackageManager()).toString());
//		Log.d(TAG, "debug!"+pi.applicationInfo.loadIcon(getPackageManager()));
//		Log.d(TAG, "debug!"+pi.applicationInfo.sourceDir);
		return sb.toString();
	}
	
	class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			Bundle data = msg.getData(); 
			String apk = data.getString("apkInfo");
			Log.d(TAG, " handler index:"+data.getInt("index"));
			tv_allapp.setText(apk);
			tv_currapp.setText(""+data.getInt("index"));//data.getString("currapp")
		}
	};
	
	/**
	 * 用handler更新主线程
	 */
	Runnable runnable = new Runnable(){
		@Override
		public void run() {
			String apks = "";
			int index = 0;
			List<PackageInfo> packageInfos = getPackageManager().getInstalledPackages(0);
			for(PackageInfo packageInfo : packageInfos){
				index++;
				temp = apktoString(packageInfo, index);
				apks += temp;
				Message msg = new Message();
				Bundle data = new Bundle();
				data.putString("apkInfo", apks);
				data.putString("currapp", temp);
				data.putInt("index", index);
				msg.setData(data);
				handler.sendMessage(msg);
			}
		}
	};
	
	/**
	 * 用runOnUiThread 更新主线程
	 */
	Runnable runnable1 = new Runnable(){
		@Override
		public void run() {
			int index = 0;
			List<PackageInfo> packageInfos = getPackageManager().getInstalledPackages(0);
			for(PackageInfo packageInfo : packageInfos){
				index++;
				temp = apktoString(packageInfo, index);
				//temp_all += temp;
				sbf.append(temp);
				temp_icon = packageInfo.applicationInfo.loadIcon(getPackageManager());
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						tv_currapp.setText(temp);
						icon.setImageDrawable(temp_icon);
					}
				});
			}
		}
	};
	
}
