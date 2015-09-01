package com.example.mt;

import java.sql.Time;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.gps.GPSActivity;
import com.example.gps.LocationAct;
import com.example.gps.NetActivity;

public class MainActivity extends Activity implements OnClickListener {
	
	private static String TAG = "MainActivity";
	
	private void forward(Class<?> clazz){
		Intent intent = new Intent(this, clazz);
		Log.i(TAG, "forward:" + clazz.getName());
		startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btn_camera).setOnClickListener(this);
		findViewById(R.id.btn_mediaplayer).setOnClickListener(this);
		findViewById(R.id.btn_mediastore).setOnClickListener(this);
		findViewById(R.id.btn_sensor).setOnClickListener(this);
		findViewById(R.id.btn_sized_camera).setOnClickListener(this);
		findViewById(R.id.btn_sqlite).setOnClickListener(this);
		findViewById(R.id.btn_phones).setOnClickListener(this);
		findViewById(R.id.btn_video_view).setOnClickListener(this);
		findViewById(R.id.btn_player).setOnClickListener(this);
		findViewById(R.id.btn_qp).setOnClickListener(this);
		findViewById(R.id.btn_gd).setOnClickListener(this);
		findViewById(R.id.btn_web).setOnClickListener(this);
		findViewById(R.id.btn_tele).setOnClickListener(this);
		findViewById(R.id.btn_gps1).setOnClickListener(this);
		findViewById(R.id.btn_gps2).setOnClickListener(this);
		findViewById(R.id.btn_gps3).setOnClickListener(this);
		findViewById(R.id.btn_calllog).setOnClickListener(this);
		findViewById(R.id.btn_allapp).setOnClickListener(this);
		findViewById(R.id.btn_root).setOnClickListener(this);
		findViewById(R.id.btn_file).setOnClickListener(this);
		findViewById(R.id.btn_viewpager).setOnClickListener(this);
		findViewById(R.id.btn_fields).setOnClickListener(this);
		
		findViewById(R.id.btn_httptest).setOnClickListener(this);
		findViewById(R.id.btn_flashlight).setOnClickListener(this);
		findViewById(R.id.btn_timepicker).setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {

		if(R.id.btn_camera == v.getId()){
			forward(CameraActivity.class);
		}else if(R.id.btn_mediaplayer == v.getId()){
			forward(MediaPlayerActivity.class);
		}else if(R.id.btn_mediastore == v.getId()){
			forward(MediaStoreActivity.class);
		}else if(R.id.btn_sensor == v.getId()){
			forward(SensorActivity.class);			
		}else if(R.id.btn_sized_camera == v.getId()){
			forward(SizedCamteraActivity.class);
		}else if(R.id.btn_sqlite == v.getId()){
			forward(DatebaseTest.class);
		}else if(R.id.btn_phones == v.getId()){
			forward(PhoneNumberActivity.class);
		}else if(R.id.btn_video_view == v.getId()){
			forward(VideoActivity.class);
		}else if(R.id.btn_qp == v.getId()){
			forward(QpActivity.class);
		}else if(R.id.btn_gd == v.getId()){
			forward(GestureDetectorActivity.class);
		}else if(R.id.btn_web == v.getId()){
			forward(WebViewActivity.class);
		}else if(R.id.btn_tele == v.getId()){
			forward(TelephonyActivity.class);
		}else if(R.id.btn_gps1 == v.getId()){
			forward(LocationAct.class);
		}else if(R.id.btn_gps2 == v.getId()){
			forward(GPSActivity.class);
		}else if(R.id.btn_gps3 == v.getId()){
			forward(NetActivity.class);
		}else if(R.id.btn_calllog == v.getId()){
			forward(CallLogActivity.class);
		}else if(R.id.btn_allapp == v.getId()){
			forward(AllAppActivity.class);
		}else if(R.id.btn_root == v.getId()){
			forward(RootActivity.class);
		}else if(R.id.btn_file == v.getId()){
			forward(FileActivity.class);
		}else if(R.id.btn_viewpager == v.getId()){
			forward(ViewPagerActivity.class);
		}else if(R.id.btn_fields == v.getId()){
			forward(BuildFieldActivity.class);
		}else if(R.id.btn_httptest == v.getId()){
			forward(HttpActivity.class);
		}else if(R.id.btn_flashlight == v.getId()){
			forward(FlashlightActivity.class);
		}else if(R.id.btn_timepicker == v.getId()){
			forward(TimePickerActivity.class);
		}else if(R.id.btn_player == v.getId()){
			//Toast.makeText(MainActivity.this, etHeight.getText(), 3000).show();
			
			//Intent intent = new Intent(android.content.Intent.ACTION_DIAL);
			
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			Uri uri = Uri.parse("file://"+Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/VID_20141125_095617.mp4");
			intent.setDataAndType(uri, "video/*");
			
		//	Uri data = Uri.parse("http://www.baidu.com");
			intent.setData(uri);
			//Toast.makeText(MainActivity.this, Environment.getExternalStorageDirectory().getPath(), 3000).show();
			
			startActivity(intent);
		}else {
			
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		Log.d("onKeyDown", "keyCode:"+keyCode);
		if(keyCode == event.KEYCODE_BACK){
			Log.d("", "返回按钮");
		}
		return true;
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event){
		Log.d("onKeyUp", "keyCode:"+keyCode);
		return true;
	}

}