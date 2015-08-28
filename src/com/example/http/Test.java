package com.example.http;

import java.io.IOException;

import org.apache.http.Header;
import org.json.JSONObject;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class Test {

	private static final String WEB = "http://www.mny9.com";

	private long async_http = 0, async_http1 = 0;
	private long OKhttp = 0, OKhttp1 = 0;
	
	public void test(){
		AsyncHttpClient client = new AsyncHttpClient();
		async_http = System.currentTimeMillis();
		Log.d("", "async请求前:" + async_http);
		client.get(WEB, new TextHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				async_http1 = System.currentTimeMillis();
				Log.d("", "onSuccess time : " + async_http1);
				Log.d("", "所用时间 : " + (async_http1 - async_http));
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				async_http1 = System.currentTimeMillis();
				Log.d("", "onFailure time : " + async_http1);
			}
		}/*; JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, JSONObject arg2) {
				async_http1 = System.currentTimeMillis();
				Log.d("", "onSuccess time : " + async_http1);
				Log.d("", "所用时间 : " + (async_http1 - async_http));
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				// TODO Auto-generated method stub
				super.onFailure(statusCode, headers, responseString, throwable);
			}
			
		}*/);
	}
	
	/**
	 * OkHtttp 非异步
	 */
	public void test1(){
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(WEB).build();
		try {
			Response response = client.newCall(request).execute();
			if(response.isSuccessful()){
				Log.d("successful", response.toString());
			}else{
				Log.d("okhttp", "failure");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testOkHttp(){
		Request request = new Request.Builder().url(WEB).build();
		try {
			
			OkHttpUtil.enqueue(request, new Callback() {
				@Override
				public void onResponse(Response response){
					Log.d("OkHttp async", response.toString());
				}
				@Override
				public void onFailure(Request arg0, IOException arg1) {
					Log.d("OkHttp async", "faliure!");
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
