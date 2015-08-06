package com.example.mt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstServiceActivity extends Activity implements OnClickListener {

	Button start, stop;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstservice_main);

		start = (Button) findViewById(R.id.start);
		stop = (Button) findViewById(R.id.stop);

		start.setOnClickListener(this);
		stop.setOnClickListener(this);
		
		intent = new Intent();
		intent.setAction("com.example.service.FIRST_SERVICE");
	}

	/**
	 * startService stopService 方法不能进行数据交换.
	 * 
	 * 如果需要进行数据交换则需要使用bindService unBindService
	 * 
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start:
			//startService(intent);
			/**
			 * @params intent service
			 * @params conn
			 * conn是一个ServiceConnection对象。该对象用于监听访问者与service之间的连接情况。
			 * 当访问者与service连接成功时，将回调该ServiceConnection对象的onServiceConnectioned( )方法。
			 * 当Service所在的宿主进程发生异常中止时，导致该Service与与访问者之间断开连接时回调该ServiceConnection对象的onServiceDisconnectioned( )方法。
			 * flags： 指定绑定时是否自动创建Service(如果还未创建)。可指定为0(不自动创建)或者BIND_AUTO_CREATE(自动创建)。
			 * @params intent service
			 */
			//bindService(intent, conn, flags);
			break;
		case R.id.stop:
			stopService(intent);
			break;
		default:
			break;
		}
	}
}