package com.example.mt;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.adapter.ViewPagerAdapter;

public class ViewPagerActivity extends Activity {

	public static String TAG = "ViewPagerActivity";
	
	ViewPager viewPager; 
	
	ArrayList<View> viewList;
	
	Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_index);
		if( viewList == null){
			viewList = new ArrayList<View>();
		}
		
		LayoutInflater lf = LayoutInflater.from(this);
		viewList.add(lf.inflate(R.layout.viewpager0, null));
		viewList.add(lf.inflate(R.layout.viewpager1, null));
		viewList.add(lf.inflate(R.layout.viewpager2, null));
		viewList.add(lf.inflate(R.layout.viewpager3, null));
		
		viewPager = (ViewPager) findViewById(R.id.vp_list);

		viewPager.setAdapter(new ViewPagerAdapter(viewList));
		
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int index) {
				switch (index) {
				case 0:
					alert("0");
					break;
				case 1:
					alert("1");
					init();
					break;
				case 2:
					alert("2");
					break;
				case 3:
					alert("3");
					break;
				default:
					break;
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				Log.d(TAG, "onPageScrolled arg0:"+ arg0 +" arg1:"+ arg1 +" arg2:"+ arg2);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				Log.d(TAG, "onPageScrollStateChanged " + arg0);
			}
		});
		
		//setCurrentItem(1);
		//alert("0...");
	}
	
	private void alert(String text){
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
	
	
	public void init(){
		btn = (Button) findViewById(R.id.btn_viewpager_01);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alert("点我哦");
			}
		});
	}
	
	public void setCurrentItem(int index){
		Log.v(TAG, "setCurrentItem..."+index);
		if(viewPager != null){
			viewPager.setCurrentItem(index);
			Log.d(TAG, "toString："+viewPager.getChildAt(0).toString());
		}else{
			Log.d(TAG, "viewPager is null!");
		}
	}
	
}
