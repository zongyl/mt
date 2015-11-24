package com.example.activitesmanager;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.os.Bundle;

public class ActivitesManager extends Activity{

	private ActivityManager am;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		am = (ActivityManager) this.getSystemService(this.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> df = am.getRunningAppProcesses();
		//http://blog.csdn.net/songjinshi/article/details/46633897
		
		//http://www.2cto.com/kf/201411/348415.html 判断程序运行状态
		List<RunningTaskInfo> rs = am.getRunningTasks(0);
		for(RunningTaskInfo info : rs){
			info.topActivity.getPackageName();
			info.baseActivity.getPackageName();
		}
	}
	
}
