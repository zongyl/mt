package com.example.mt;

import java.io.DataOutputStream;
import java.io.IOException;

import com.example.utils.Root;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class RootActivity extends Activity{

	Button check;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.root_main);
		
		check = (Button) findViewById(R.id.btn_root_c);
		
		String apkRoot = "chmod 777 " + getPackageCodePath();
		
		alert("root:"+RootCommand(apkRoot));
		
		check.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alert("ROOT:"+new Root().isRoot());
			}
		});
	}
	
	public void alert(String text){
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 应用程序 运行命令获取 ROOT权限，设备必须已破解(获得ROOT权限)
	 * @param command
	 *       命令:String apkRoot = "chmod 777" + getPackageCodePAth();
	 *       RootCommand(apkRoot);
	 *       
	 * @return 应用程序是否获取root权限
	 */
	public static boolean RootCommand(String command){
		Process process = null;
		DataOutputStream os = null;
		try {
			process = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes(command + "\n");
			os.writeBytes("exit\n");
			os.flush();
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			try {
				if(os != null){
					os.close();
				}
				if(process!=null){
					process.destroy();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
}
