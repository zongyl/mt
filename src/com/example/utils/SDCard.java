package com.example.utils;

import java.io.File;

import android.os.Environment;

public class SDCard {

	public String getSdPath(){
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		
		if(sdCardExist){
			sdDir = Environment.getExternalStorageDirectory();
		}
		return sdDir.toString();
	}
	
}
