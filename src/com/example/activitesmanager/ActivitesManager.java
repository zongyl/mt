package com.example.activitesmanager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.mt.R;

/**
 * //http://www.2cto.com/kf/201411/348415.html 判断程序运行状态
 * //http://blog.csdn.net/songjinshi/article/details/46633897
 * @author pc
 *
 */
public class ActivitesManager extends Activity{

	private static String TAG = "ActivitesManager";
	
	private ActivityManager am;

	private Context context;
	
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitymanager);
		am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
		context = this;
		tv = (TextView) findViewById(R.id.tv_am);
		findViewById(R.id.btn_am).setOnClickListener(click);
		findViewById(R.id.btn_am1).setOnClickListener(click);
		findViewById(R.id.btn_am2).setOnClickListener(click);
		findViewById(R.id.btn_am3).setOnClickListener(click);
	}
	
	/**
	 * 获取Android手机内安装的所有桌面
	 * @param context
	 * @return
	 */
	private static List<String> getAllTheLauncher(Context context)  
    {  
        List<String> names = null;  
        PackageManager pkgMgt = context.getPackageManager();  
        Intent it = new Intent(Intent.ACTION_MAIN);  
        it.addCategory(Intent.CATEGORY_HOME);  
        List<ResolveInfo> ra = pkgMgt.queryIntentActivities(it, 0);  
        if (ra.size() != 0)  
        {  
            names = new ArrayList<String>();  
        }  
        for (int i = 0; i < ra.size(); i++)  
        {  
            String packageName = ra.get(i).activityInfo.packageName;  
            names.add(packageName);  
        }  
        return names;  
    } 
	
	/**
	 * 获取正在运行的桌面
	 * @param context
	 * @return
	 */
	private String getLauncherRunnig(Context context){
		String ret = "";
		List<RunningAppProcessInfo> df = am.getRunningAppProcesses();
		for(RunningAppProcessInfo running : df){
 			if(running.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND){
 				ret += "running.processName:"+running.processName.toString()+"\n";
			}
		}
		return ret;
	}
	
	private String isTopActivy(Context context){
		String ret = "";
				List<RunningTaskInfo> rs = am.getRunningTasks(Integer.MAX_VALUE);
				Log.d(TAG, ""+rs.size());
				ret += "size:"+rs.size()+"\n";
				for(RunningTaskInfo info : rs){
					Log.d(TAG, ""+info.topActivity.getPackageName());
					Log.d(TAG, ""+info.baseActivity.getPackageName());
					ret += info.topActivity.getPackageName()+"\n";
				}
				return ret;
	}

	private String isApponForeground(Context context){
		String ret = "";
		String pakeageName = context.getPackageName();
		List<RecentTaskInfo> appTask = am.getRecentTasks(Integer.MAX_VALUE, 1);
		for(RecentTaskInfo info : appTask){
			ret += info.baseIntent.toString()+"\n";
		}
		return "pakeageName:" + pakeageName + "\nreturns:" + ret;
	}
	
	OnClickListener click = new OnClickListener(){
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_am:
				tv.setText(isTopActivy(context));
				break;
			case R.id.btn_am1:
				tv.setText(isApponForeground(context));
				break;
			case R.id.btn_am2:
				List<String> list = getAllTheLauncher(context);
				tv.setText(list.toString());
				break;
			case R.id.btn_am3:
				tv.setText(getLauncherRunnig(context));
				break;
			default:
				break;
			}
		}
	};
}
