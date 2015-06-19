package com.example.mt;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

/**
 * 摄像头
 * @author pc
 *
 */
public class CameraActivity extends Activity {

	final static int CAMERA_RESULT = 0;
	
	ImageView imv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_main);
		
		String imagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mycamera.jpg";
		File imageFile = new File(imagePath);
		Uri uri = Uri.fromFile(imageFile);
		
		Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		//intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(intent, CAMERA_RESULT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("#########requestCode:", String.valueOf(requestCode));
		if(resultCode == RESULT_OK){
			Log.i("..................", "result_ok");
			Bundle extras = data.getExtras();
			Bitmap bmp = (Bitmap)extras.get("data");
		//	Log.i("bitmap.toString()", bmp.toString());
			imv = (ImageView)findViewById(R.id.CameraImageView);
			imv.setImageBitmap(bmp);
		}
	}
}