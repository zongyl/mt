package com.example.mt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

public class SensorActivity extends Activity {

	static Map sensorMap;
	
	static {
		sensorMap = new HashMap();
		sensorMap.put(Sensor.TYPE_ACCELEROMETER, "加速度传感器");
		sensorMap.put(Sensor.TYPE_AMBIENT_TEMPERATURE, "温度传感器");
		sensorMap.put(Sensor.TYPE_GAME_ROTATION_VECTOR, "游戏旋转矢量");
		sensorMap.put(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR, "地磁旋转矢量传感器");
		sensorMap.put(Sensor.TYPE_GRAVITY, "重力传感器");
		sensorMap.put(Sensor.TYPE_GYROSCOPE, "陀螺仪");
		sensorMap.put(Sensor.TYPE_GYROSCOPE_UNCALIBRATED, "陀螺仪无标定");
		sensorMap.put(Sensor.TYPE_LIGHT, "光线传感器");
		sensorMap.put(Sensor.TYPE_LINEAR_ACCELERATION, "线性加速度传感器");
		sensorMap.put(Sensor.TYPE_MAGNETIC_FIELD, "磁场");
		sensorMap.put(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED, "磁场-无标定");
		sensorMap.put(Sensor.TYPE_ORIENTATION, "方向传感器");
		sensorMap.put(Sensor.TYPE_PRESSURE, "压力传感器");
		sensorMap.put(Sensor.TYPE_PROXIMITY, "接近");
		sensorMap.put(Sensor.TYPE_RELATIVE_HUMIDITY, "相对湿度传感器");
		sensorMap.put(Sensor.TYPE_ROTATION_VECTOR, "旋转矢量");
		sensorMap.put(Sensor.TYPE_SIGNIFICANT_MOTION, "显著-运动");
		sensorMap.put(Sensor.TYPE_STEP_COUNTER, "步骤-计数器");
		sensorMap.put(Sensor.TYPE_STEP_DETECTOR, "步骤-探测器");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sensor_main);
	SensorManager sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
	
	List<Sensor> ss = sm.getSensorList(Sensor.TYPE_ALL);
	
	StringBuffer sbf = new StringBuffer();
	sbf.append("一共有"+ss.size()+"个传感器！\n");
	
	Log.i("count:", "一共有"+ss.size()+"个传感器！");
	
	for(Sensor s : ss){
		Log.i("设备名称：", s.getName());
		Log.i("供应商：", s.getVendor());
		Log.i("版本：", String.valueOf(s.getVersion()));
		Log.i("传感器类型：", String.valueOf(s.getType()));

		sbf.append("\n传感器："+sensorMap.get(s.getType()));
		sbf.append("\n名称："+s.getName());
		sbf.append("\n供应商："+s.getVendor());
		sbf.append("\n版本："+s.getVersion());
		sbf.append("\n*******************************");
		
	}
	
	
	
	TextView tv = (TextView)findViewById(R.id.sensorMainText);
	tv.setText(sbf.toString());
	tv.setMovementMethod(new ScrollingMovementMethod());
	//tv.setScrollContainer("");
	
	
	
	}
}