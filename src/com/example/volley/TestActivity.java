package com.example.volley;


import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.mt.R;

/**
 * Volley 测试
 * @author pc
 * copy from http://www.open-open.com/lib/view/open1380637096353.html
 *
 */
@SuppressLint("NewApi")
public class TestActivity extends Activity {

	private ImageView imageView;
	
	private NetworkImageView networkImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.volley_test);
		init();
	}
	
	private void init(){
		imageView = (ImageView) findViewById(R.id.imageView);
		networkImageView = (NetworkImageView) findViewById(R.id.networkImageView);
		getJSONByVolley();
		getImageByVolley();
		showImage();
	}
	
	/**
	 * 用Volley获取JSON数据 
	 */
	private void getJSONByVolley(){
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		String url = "http://www.mny9.com/android/getDevices?userId=Q04hAQ0AADE0Mzg2NzU0NjkyMTh1";
		
		final ProgressDialog progressDialog = ProgressDialog.show(this, "title", "message",
				/*indeterminate*/false, /*cancelable*/true, new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				Log.d("", "click cancel button!");
			}
		});
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET,
				url, 
				"", 
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d("", response.toString());
						if(progressDialog.isShowing()&&progressDialog!=null){
							progressDialog.dismiss();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						error.getMessage();
					}
				});
		requestQueue.add(jsonObjectRequest);
	}
	
	/**
	 * 
	 */
	private void getImageByVolley(){
		String imageUrl = "http://www.mny9.com/images/1.jpeg";
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		
		final LruCache<String, Bitmap> lrnCache = new LruCache<String, Bitmap>(maxMemory/8){
			@Override
			protected int sizeOf(String key, Bitmap value) {
				if(android.os.Build.VERSION.SDK_INT >= 12){
					return value.getByteCount();
				}else{
					return value.getRowBytes() * value.getHeight();
				}
			}
		};
		
		ImageCache imageCache = new ImageCache(){
			@Override
			public Bitmap getBitmap(String url) {
				return lrnCache.get(url);
			}
			@Override
			public void putBitmap(String url, Bitmap bitmap) {
				lrnCache.put(url, bitmap);
			}};
			
		ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
		ImageListener listener = ImageLoader.getImageListener(imageView, R.drawable.ic_launcher, R.drawable.ic_launcher);
		imageLoader.get(imageUrl, listener);
	}
	
	/**
	 * 
	 */
	private void showImage(){
		String imageUrl = "http://www.mny9.com/images/1.jpeg";
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		final LruCache<String, Bitmap> lrnCache = new LruCache<String, Bitmap>(20);
		ImageCache imageCache = new ImageCache(){
			@Override
			public Bitmap getBitmap(String url) {
				return lrnCache.get(url);
			}
			@Override
			public void putBitmap(String url, Bitmap bitmap) {
				lrnCache.put(url, bitmap);
			}};
		ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
		networkImageView.setTag("url");
		networkImageView.setImageUrl(imageUrl, imageLoader);
	}
}
