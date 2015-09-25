package com.example.mt;

import android.app.Activity;
import android.net.TrafficStats;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class TrafficStatsActivity extends Activity{

	private static String TAG = "TrafficStatsActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		long mobile_rx = TrafficStats.getMobileRxBytes();
		long mobile_tx = TrafficStats.getMobileTxBytes();

		TrafficStats.getTotalRxBytes();
		TrafficStats.getTotalTxBytes();

		Log.d(TAG, ""+mobile_rx);
		Log.d(TAG, ""+mobile_tx);
		alert("接受流量:"+mobile_rx);
		alert("发送流量:"+mobile_tx);
	}
	
	
	private void alert(String text){
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
}
