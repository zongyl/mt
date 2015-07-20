package com.example.adapter;

import java.util.ArrayList;

import com.example.mt.ViewPagerActivity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

public class ViewPagerAdapter extends PagerAdapter{
	
	public String TAG = "ViewPagerAdapter";
	
	private int mChildCount = 0;
	//界面列表
    private ArrayList<View> views;
    
    public ViewPagerAdapter (ArrayList<View> views){
        this.views = views;
    }
       
	/**
	 * 获得当前界面数
	 */
	@Override
	public int getCount() {
		 if (views != null) {
             return views.size();
         }      
         return 0;
	}

	/**
	 * 初始化position位置的界面
	 */
    @Override
    public Object instantiateItem(View view, int position) {
    	Log.v(TAG, "instantiateItem:"+position);
        ((ViewPager) view).addView(views.get(position), 0);
        //new ViewPagerActivity().handler.sendEmptyMessage(0);
        return views.get(position);
    }
    
    /** 
	 * 判断是否由对象生成界面
	 */
	@Override
	public boolean isViewFromObject(View view, Object arg1) {
		return (view == arg1);
	}

	/**
	 * 销毁position位置的界面
	 */
    @Override
    public void destroyItem(View view, int position, Object arg2) {
    	Log.v(TAG, "destroyItem:"+position);
        ((ViewPager) view).removeView(views.get(position));       
    }
    @Override
    public void notifyDataSetChanged() {         
          mChildCount = getCount();
          super.notifyDataSetChanged();
    }
    
    @Override
    public int getItemPosition(Object object)   {          
          if ( mChildCount > 0) {
          mChildCount --;
          return POSITION_NONE;
          }
          return super.getItemPosition(object);
    }
    


}
