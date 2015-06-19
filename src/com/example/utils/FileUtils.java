package com.example.utils;

import java.io.File;

/**
 * file oper
 * @author pc
 *
 */
public class FileUtils { 
	
	public static void createFile(String path){
		File file = new File(path);
		file.mkdir();
	}
}