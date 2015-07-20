package com.example.mt;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.utils.Loger;
import com.example.utils.SDCard;

/**
 * 文件操作  生成日志文件 
 * @author pc
 *
 */
public class FileActivity extends Activity{

	public String TAG = "FileActivity";
	
	Button btn, btn_log, btn_log_print, btn_log_close;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_main);
		
		btn = (Button) findViewById(R.id.btn_file_tt);
		btn_log = (Button) findViewById(R.id.btn_file_log);
		btn_log_print = (Button) findViewById(R.id.btn_file_log_print);
		btn_log_close = (Button) findViewById(R.id.btn_file_log_close);
		
		btn.setOnClickListener(new Click());
		btn_log.setOnClickListener(new Click());
		btn_log_print.setOnClickListener(new Click());
		btn_log_close.setOnClickListener(new Click());
		
	}
	
	public void alert(String text){
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
	
	class Click implements OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_file_tt:
				SDCard sd = new SDCard();
				String path = sd.getSdPath();
				Log.d(TAG, "sdpath:"+path);
				alert(path);
				break;

			case R.id.btn_file_log:
				Loger.openPrint(); 
				break;

			case R.id.btn_file_log_print:
				Loger.print("current Time：" + System.currentTimeMillis());
				break;

			case R.id.btn_file_log_close:
				Loger.closePrint(); 
				break;

			default:
				break;
			}
		}
		
	}
}
