package com.example.mt;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {

	VideoView vv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videoview);
		
		vv = (VideoView)this.findViewById(R.id.VideoView);
		vv.setMediaController(new MediaController(this));
		//Uri uri = Uri.parse("file://"+Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/VID_20141125_095617.mp4");
		Uri uri = Uri.parse("http://10.12.6.112:8080/ttt/VID_20141127_172807.mp4");
		vv.setVideoURI(uri);
		vv.start();
	}
}