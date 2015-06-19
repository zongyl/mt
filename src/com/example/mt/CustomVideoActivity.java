package com.example.mt;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;

public class CustomVideoActivity extends Activity /*implements OnCompletionListener, OnErrorListener, OnInfoListener, 
OnPreparedListener, OnSeekCompleteListener, OnVideoSizeChangedListener, SurfaceHolder.Callback ,MediaPlayerControl*/{

	Display currentDisplay;
	
	SurfaceView surfaceView;
	
	SurfaceHolder surfaceHolder;
	
	MediaPlayer mediaPlayer;

	int videoWidth = 0;
	
	int videoHeight = 0;
	
	boolean readyToPlay = false;
	
	MediaController controller;
	
	public final static String LOGTAG = "CUSTOM_VIDEO_PLAYER";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_video_player);
		
		surfaceView = (SurfaceView)this.findViewById(R.id.SurfaceView);
		//surfaceHolder = surfaceView.getHolder();
		//surfaceHolder.addCallback(this);
	//	surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_HARDWARE);
		
	/**/mediaPlayer = new MediaPlayer();
//		mediaPlayer.setOnCompletionListener(this);
//		mediaPlayer.setOnErrorListener(this);
//		mediaPlayer.setOnInfoListener(this);
//		mediaPlayer.setOnPreparedListener(this);
//		mediaPlayer.setOnSeekCompleteListener(this);
//		mediaPlayer.setOnVideoSizeChangedListener(this);
		
		controller = new MediaController(this);
	//	controller.setMediaPlayer(this);
		controller.setAnchorView(this.findViewById(R.id.MainView));
		controller.setEnabled(true);
		controller.show();
		
//		String filePath = Environment.getExternalStorageDirectory().getPath()
//				+"/DCIM/Camera/VID_20141125_095617.mp4";
		
		String filePath = "http://10.12.6.112:8080/ttt/VID_20141127_172807.mp4";
		
		try {
			mediaPlayer.setDataSource(filePath);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		//currentDisplay = this.getWindowManager().getDefaultDisplay();
		
	}

	/**
	 * 当发生错误时，调用onError方法。  错误信息常量 如下。
	 */
//	@Override
//	public boolean onError(MediaPlayer mediaPlayer, int error, int extra) {
//		Log.v(LOGTAG, "onError....................");
//		if(error == MediaPlayer.MEDIA_ERROR_SERVER_DIED){
//			
//		}else if(error == MediaPlayer.MEDIA_ERROR_UNKNOWN){
//			
//		}else if(error == MediaPlayer.MEDIA_ERROR_MALFORMED){
//			
//		}
//		return false;
//	}
//
//	/**
//	 * 当出现关于播放视频的特定信息或者需要发出警告时，将调用oninfo 方法。
//	 */
//	@Override
//	public boolean onInfo(MediaPlayer mediaPlayer, int info, int extra) {
//		Log.v(LOGTAG, "onInfo....................");
//		if(info == MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING){
//			
//		}else if(info == MediaPlayer.MEDIA_INFO_NOT_SEEKABLE){
//			
//		}
//
//		return false;
//	}
//
//	/**
//	 * 当mediaPlayer 完成播放文件时，将调用onCompletion方法。
//	 */
//	@Override
//	public void onCompletion(MediaPlayer mediaPlayer) {
//		Log.v(LOGTAG, "onCompletion....................");
//	}
//
//	/**
//	 * 
//	 */
//	@Override
//	public void onVideoSizeChanged(MediaPlayer arg0, int arg1, int arg2) {
//		Log.v(LOGTAG, "onVideoSizeChanged....................");
//	}
//
//	/**
//	 * 
//	 */
//	@Override
//	public void onSeekComplete(MediaPlayer arg0) {
//		Log.v(LOGTAG, "onSeekComplete....................");
//	}
//
//	/**
//	 * 当mediaPlayer成功的准备开始播放之后，将调用方法。一旦调用该方法，MediaPlayer就进入“准备就绪”状态，准备开始播放。
//	 */
//	@Override
//	public void onPrepared(MediaPlayer mediaPlayer) {
//		Log.v(LOGTAG, "onPrepared....................");
//		//获取视频的尺寸
//		videoWidth = mediaPlayer.getVideoWidth();
//		videoHeight = mediaPlayer.getVideoHeight();
//		
//		/*if(videoWidth > currentDisplay.getWidth() || videoHeight > currentDisplay.getHeight()){
//			
//		}*/
//		//mediaPlayer.start();
//	}
//
//	/**
//	 * 
//	 */
//	@Override
//	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//		Log.v(LOGTAG, "surfaceChanged....................");
//		
//	}
//
//	@Override
//	public void surfaceCreated(SurfaceHolder arg0) {
//		Log.v(LOGTAG, "surfaceCreated Called");
//		mediaPlayer.setDisplay(surfaceHolder);
//		
//		try {
//			mediaPlayer.prepare();
//		} catch (IllegalStateException e) {
//			Log.v(LOGTAG, e.getMessage());
//			finish();
//		} catch (IOException e) {
//			Log.v(LOGTAG, e.getMessage());
//			finish();
//		}
//	}
//
//	@Override
//	public void surfaceDestroyed(SurfaceHolder arg0) {
//		Log.v(LOGTAG, "surfaceDestroyed....................");
//		
//	}
//
//	@Override
//	public boolean canPause() {
//		Log.v(LOGTAG, "canPause....................");
//		return false;
//	}
//
//	@Override
//	public boolean canSeekBackward() {
//		Log.v(LOGTAG, "canSeekBackward....................");
//		return false;
//	}
//
//	@Override
//	public boolean canSeekForward() {
//		Log.v(LOGTAG, "canSeekForward....................");
//		return false;
//	}
//
//	@Override
//	public int getAudioSessionId() {
//		Log.v(LOGTAG, "getAudioSessionId....................");
//		return 0;
//	}
//
//	@Override
//	public int getBufferPercentage() {
//		Log.v(LOGTAG, "getBufferPercentage....................");
//		return 0;
//	}
//
//	@Override
//	public int getCurrentPosition() {
//		Log.v(LOGTAG, "getCurrentPosition....................");
//		return mediaPlayer.getCurrentPosition();
//	}
//
//	@Override
//	public int getDuration() {
//		Log.v(LOGTAG, "getDuration....................");
//		return mediaPlayer.getDuration();
// 	}
//
//	@Override
//	public boolean isPlaying() {
//		Log.v(LOGTAG, "isPlaying....................");
//		return mediaPlayer.isPlaying();
//	}
//
//	@Override
//	public void pause() {
//		Log.v(LOGTAG, "pause....................");
//		if(mediaPlayer.isPlaying()){
//			mediaPlayer.pause();
//		}
//	}
//
//	@Override
//	public void seekTo(int pos) {
//		Log.v(LOGTAG, "seekTo....................");
//		mediaPlayer.seekTo(pos);
//	}
//
//	@Override
//	public void start() {
//		Log.v(LOGTAG, "start....................");
//		mediaPlayer.start();
//	}
	
}