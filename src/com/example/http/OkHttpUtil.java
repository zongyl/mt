package com.example.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class OkHttpUtil {

	private static final OkHttpClient client = new OkHttpClient();
	
	static{
		client.setConnectTimeout(30, TimeUnit.SECONDS);
	}
	
	public static Response execute(Request request) throws IOException{
		return client.newCall(request).execute();
	}
	
	public static void enqueue(Request request, Callback responseCallback) throws IOException{
		client.newCall(request).enqueue(responseCallback);
	}
}
