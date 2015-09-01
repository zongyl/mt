package com.example.mt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class TimePickerActivity extends Activity{

	TextView tv;
	
	TimePicker tp;
	
	Button b1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_picker_main);
		tpdemo();
	}
	
	public void tpdemo(){
		tp = (TimePicker) findViewById(R.id.tp);
		tp.setIs24HourView(true);
		tp.setCurrentHour(21);
		tp.setCurrentMinute(26);
		tp.setOnTimeChangedListener(tpLis);
		
		tv = (TextView) findViewById(R.id.tv);
		b1 = (Button) findViewById(R.id.b1);
		b1.setOnClickListener(b1Lis);
	}
	
	private OnTimeChangedListener tpLis = new OnTimeChangedListener() {
		@Override
		public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
			tv.setText("["+hourOfDay +":"+ minute+"]["+view.getCurrentHour()+":"+view.getCurrentMinute()+"]");
		}
	};
	
	private OnClickListener b1Lis = new OnClickListener() {
		@Override
		public void onClick(View v) {
			setTitle(tp.getCurrentHour()+":"+tp.getCurrentMinute());
		}
	};
	
}
