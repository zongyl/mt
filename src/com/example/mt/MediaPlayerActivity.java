package com.example.mt;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MediaPlayerActivity extends Activity {
	  /** Called when the activity is first created. */ 

  private SeekBar skb_video=null;
  private Button btn_start_video = null;  
  private Button btn_stop_video = null;
  private Button btn_end_video = null;
  private SurfaceView surfaceView; 
  private SurfaceHolder surfaceHolder; 
  
  private MediaPlayer m = null;  
  private Timer mTimer;
  private TimerTask mTimerTask;
  
  private boolean isChanging=false;//互斥变量，防止定时器与SeekBar拖动时进度冲突
   @Override  
  public void onCreate(Bundle savedInstanceState) {  
      super.onCreate(savedInstanceState);  
      setContentView(R.layout.mediaplayer);  
      
      //----------Media控件设置---------//
      m=new MediaPlayer();
      
      //播放结束之后弹出提示
      m.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
			@Override
			public void onCompletion(MediaPlayer arg0) {
				
				Log.i("MediaPlayer.OnCompletionListener", "播放结束");
				
				Toast.makeText(MediaPlayerActivity.this, "结束", 1000).show();
				m.release();
				
			}
      });
      //
      btn_start_video = (Button) this.findViewById(R.id.Button03);  
      btn_stop_video = (Button) this.findViewById(R.id.Button04);   
      btn_end_video = (Button) this.findViewById(R.id.Button05);  
      btn_start_video.setOnClickListener(new ClickEvent());
      btn_stop_video.setOnClickListener(new ClickEvent());
      btn_end_video.setOnClickListener(new ClickEvent());
      skb_video=(SeekBar)this.findViewById(R.id.SeekBar02);
      skb_video.setOnSeekBarChangeListener(new SeekBarChangeEvent());
      surfaceView = (SurfaceView) findViewById(R.id.SurfaceView01);
      surfaceHolder = surfaceView.getHolder();
      surfaceHolder.setFixedSize(100, 100);
     // surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
      
      //----------定时器记录播放进度---------//
      mTimer = new Timer();
      mTimerTask = new TimerTask() {
          @Override
          public void run() {	
          	if(isChanging==true)
          		return;
          	skb_video.setProgress(m.getCurrentPosition());
          }
      };

      mTimer.schedule(mTimerTask, 0, 10);
      
  }  
   
/*
 * 按键事件处理
 */
class ClickEvent implements View.OnClickListener{
	@Override
	public void onClick(View v) {
		 if( v==btn_stop_video)
		{
			m.stop();
			Log.i(" View.OnClickListener", "停止播放");
		}
		else if(v==btn_start_video)
		{
			m.reset();//恢复到未初始化的状态
			m=MediaPlayer.create(MediaPlayerActivity.this, Uri.parse(
					//"file://"+Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/VID_20141125_095617.mp4"
					"http://10.12.6.112:8080/ttt/VID_20141127_172807.mp4"
					));//读取视频
			try {
			    m.prepare();
			} catch ( Exception e) {
				e.printStackTrace();
			}
			skb_video.setMax(m.getDuration());//设置SeekBar的长度
			m.setAudioStreamType(AudioManager.STREAM_MUSIC);
			m.setDisplay(surfaceHolder);//设置屏幕
			
			m.start();
		}else if(v == btn_end_video){
			m.release();
			Log.i(" View.OnClickListener", "release 結束播放");
		}
	}
}

/*
 * SeekBar进度改变事件
 */
class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener{

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		
		Log.i("SeekBarChangeEvent", "onProgressChanged");
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
  	isChanging=true;

	Log.i("SeekBarChangeEvent", "onStartTrackingTouch");
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		m.seekTo(seekBar.getProgress());
  	isChanging=false;	

	Log.i("SeekBarChangeEvent", "onStopTrackingTouch");
	}
	  
}
}