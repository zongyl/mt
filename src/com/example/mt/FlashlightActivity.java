package com.example.mt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FlashlightActivity extends Activity{

	Button btn, cmd;
	
	boolean open = false;
	
	Camera camera;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flashlight_main);
	
		btn = (Button) findViewById(R.id.btn_flashlight);
		cmd = (Button) findViewById(R.id.btn_cmd);
		btn.setOnClickListener(new Click());
		cmd.setOnClickListener(new Click());
	}

	class Click implements OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_flashlight:
				Log.d("", "open:"+open);
				if(open){
					btn.setText("open");
					turnLightOn(camera, false);
					camera.setPreviewCallbackWithBuffer(null);
					camera.release();
					camera = null;
					open = false;
				}else{
					if(camera == null){
						camera = Camera.open();
					}
					btn.setText("close");
					turnLightOn(camera, true);
					open = true;
				}
				break;

			case R.id.btn_cmd:
				try {
					Process process = Runtime.getRuntime().exec("getprop");
					InputStream in = process.getInputStream();
					Log.d("getprop:", inputStream2String(in));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
		
	}
	
	public String inputStream2String(InputStream in) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while((i = in.read())!=-1){
			baos.write(i);
		}
		return baos.toString();
	}
	
	public static void turnLightOn(Camera camera, boolean open){
		if(camera == null){
			return;
		}
		Parameters params = camera.getParameters();
		if(params == null){
			return;
		}
		List<String> flashModes = params.getSupportedFlashModes();
		if(flashModes == null){
			return;
		}
		String flashMode = params.getFlashMode();
		
		String status = "";
		if(open){
			status = Parameters.FLASH_MODE_TORCH;
		}else{
			status = Parameters.FLASH_MODE_AUTO;
		}
		
		if(!status.equals(flashMode)){
			if(flashModes.contains(status)){
				params.setFlashMode(status);
				camera.setParameters(params);
			}
		}
	}
}
