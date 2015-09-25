package com.example.mt;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 显示web网页
 * @author pc
 *
 */
public class WebViewActivity extends Activity   {

	WebView webView;
	EditText et_url;
	Button btn, btn_load;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.webview_main);
		
		webView = (WebView)findViewById(R.id.webView01);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://app.ys7.com/");
		
		//webView.setScrollBarStyle(0);
		
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon){
				alert("载入开始!");
			}
			
			@Override
			public void onPageFinished(WebView view, String url){
				alert("载入结束!");
			}
		});
	//	getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.web_title);
		
		et_url = (EditText) findViewById(R.id.et_weburl);
		btn = (Button)findViewById(R.id.web_btn);
		btn_load = (Button) findViewById(R.id.btn_web_load);
		
		btn.setOnClickListener(new Click());
		btn_load.setOnClickListener(new Click());
		
	}

	class Click implements OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.web_btn:
				alert(webView.getUrl());
				break;
			case R.id.btn_web_load:
				String url = et_url.getText().toString();
				Log.d("url", "" + url);
				webView.loadUrl(url);
				break;
			default:
				break;
			}
		}
	}
	
	private void alert(String msg){
		Toast.makeText(this, msg, 10).show();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if((keyCode == event.KEYCODE_BACK) && webView.canGoBack()){
			webView.goBack();
			return true;
		}
		return false;
	}
	
}