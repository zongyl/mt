package com.example.bean;

import android.content.pm.ProviderInfo;
import android.graphics.drawable.Drawable;

/**
 * 
 * @author pc
 *
 */
public class AppInfo {

	public int versionCode = 0;
	public String appname = "";
	public String packagename = "";
	public Drawable appicon = null;
	public long lastinstall;
	public ProviderInfo[] provider;
	public String InstallPath;
}