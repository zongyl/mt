package com.example.utils;

import java.io.File;

public class Root {

	/**
	 * 判断手机是否ROOT
	 * @return
	 */
	public boolean isRoot(){
		boolean root = false;
		if((!new File("/system/bin/su").exists())&&(!new File("/system/xbin/su").exists())){
			root = false;
		}else{
			root = true;
		}
		return root;
	}
}
