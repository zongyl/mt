package com.example.mt;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 显示大图像
 * @author pc
 *
 */
public class SizedCamteraActivity extends Activity {

	final static int CAMERA_RESULT = 0;
	
	ImageView imv;
	
	String imageFilePath;
	
	private void addListener(){
		findViewById(R.id.btn_ccc).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) { 
				imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/camtera_1417587909646.jpg";
				File imageFile = new File(imageFilePath);
				Uri uri = Uri.fromFile(imageFile);
				
				Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
				
				startActivityForResult(intent, CAMERA_RESULT);
			
				}
		});
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_main);
		addListener();
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(data == null){
			Log.v("DATA:", "data is null!");
		}else{
			Log.v("DATA:", "data is not null!");
		}
	//	print(requestCode, resultCode, data);
			if(data!=null){
			Log.i("111111111111111111111111", "000000000000000000000");
			DisplayMetrics dm = new DisplayMetrics();
					getWindowManager().getDefaultDisplay().getMetrics(dm);

					int width = dm.widthPixels;
					int height = dm.heightPixels;
					
			BitmapFactory.Options opts = new BitmapFactory.Options();
			
			opts.inJustDecodeBounds = true;
			
			Bitmap bmp = BitmapFactory.decodeFile(imageFilePath, opts);
			
			int heightRatio = (int)Math.ceil(opts.outHeight/(float)height);
			int widthRatio = (int)Math.ceil(opts.outWidth/(float)width);
			
			Log.v("widthRatio:", ""+widthRatio);
			Log.v("heightRatio:", ""+heightRatio);
			
			if(widthRatio > 1 && heightRatio > 1){
				if(heightRatio > widthRatio){
					opts.inSampleSize = heightRatio;
				}else{
					opts.inSampleSize = widthRatio;
				}
			}
			
			opts.inJustDecodeBounds =false;
			
			bmp = BitmapFactory.decodeFile(imageFilePath, opts);
			
			imv = (ImageView)findViewById(R.id.CameraImageView);
			imv.setImageBitmap(bmp);
			
			
		}
	}
	
	/**
	 * 打印调试信息
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	private void print(int requestCode, int resultCode, Intent data){
		Log.v("REQUEST:", ""+requestCode);
		Log.v("RESULT:", ""+resultCode);
		//Log.v("DATA:", ""+data.toString());
		//Log.v("DATA.Extras:", ""+data.getExtras().toString());
		Bundle bundle = data.getExtras();
		for(String key : bundle.keySet()){
			Log.v("key:"+key, "class:"+bundle.get(key).getClass());
			Log.v("key:"+key, "value:"+bundle.getString(key));
		}
	}

}