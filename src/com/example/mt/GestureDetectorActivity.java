package com.example.mt;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

/**
 * 手势探测
 * @author pc
 *
 */
public class GestureDetectorActivity extends Activity implements OnTouchListener{

	private GestureDetector mGestureDetector;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gesture_main);
		mGestureDetector = new GestureDetector(this, new MyOnGestureListener());
		Button btn = (Button)findViewById(R.id.btn_testgesture);
		btn.setOnTouchListener(this);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		mGestureDetector.onTouchEvent(event);
		return true;
	}
	
	private String getActionName(int action){
		if(MotionEvent.ACTION_DOWN == action){
			return "ACTION_DOWN";
		}else if(MotionEvent.ACTION_UP == action){
			return "ACTION_UP";
		}else if(MotionEvent.ACTION_MOVE == action){
			return "ACTION_MOVE";
		}else{
			
		}
		return "null";
	}
	
	class MyOnGestureListener extends SimpleOnGestureListener{
		
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			Log.i(getClass().getName(), "onSingleTapUp"+getActionName(e.getAction()));
			return false;
		}
		
		@Override
		public void onLongPress(MotionEvent e) { 
			Log.i(getClass().getName(), "onLongPress"+getActionName(e.getAction()));
		}
		
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			Log.i(getClass().getName(), "onScroll");
			return false;
		}
		
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			Log.i(getClass().getName(), "onFling");
			return false;
		}
		
		@Override
		public void onShowPress(MotionEvent e) {
			Log.i(getClass().getName(), "onShowPress:"+getActionName(e.getAction()));
			super.onShowPress(e);
		}
		
		@Override
		public boolean onDown(MotionEvent e) {
			Log.i(getClass().getName(), "onDown:"+getActionName(e.getAction()));
			return false;
		}
		
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			Log.i(getClass().getName(), "onDoubleTap:"+getActionName(e.getAction()));
			return false;
		}
		
		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			Log.i(getClass().getName(), "onDoubleTapEvent:"+getActionName(e.getAction()));
			return false;
		}
		
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			Log.i(getClass().getName(), "onSingleTapConfirmed:"+getActionName(e.getAction()));
			return false;
		}
	}
	
}