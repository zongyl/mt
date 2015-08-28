package com.example.mt;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class TelephonyActivity extends Activity{

	TextView tv;
	String context = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.telephony_main);
	
		tv = (TextView)findViewById(R.id.telephony_tv);
		
		TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
		getDeviceInfo(tm);
		tv.setText(context);
	}
	
	public String getDeviceInfo(TelephonyManager tm){
		/**
		 * 电话状态
		 * CALL_STATE_IDLE = 0 无活动
		 * CALL_STATE_RINGING = 1  响铃
		 * CALL_STATE_OFFHOOK = 2  摘机
		 */
		//tm.getCallState();
		context +="callstate:"+tm.getCallState()+"\n";
		//电话方位
		/*CellLocation local = tm.getCellLocation();
		context +="local:"+local.toString()+"\n";*/
		//设备号
		tm.getDeviceId();
		context +="deviceId:"+tm.getDeviceId()+"\n";
		//设备的软件版本号
		tm.getDeviceSoftwareVersion();
		context +="deviceSoftwareVersion:"+tm.getDeviceSoftwareVersion()+"\n";
		//手机号
		tm.getLine1Number();
		context +="Line1Number:"+tm.getLine1Number()+"\n";
		/**
		 * 附近的电话信息
		 * 类型:List<MeighboringCellInfo>
		 * permission# ACCESS_COARSE_UPDATES
		 */
		/*List<NeighboringCellInfo> ll = tm.getNeighboringCellInfo();
		for(NeighboringCellInfo info : ll){
			context +="info.cid:"+info.getCid()+"\n";
		}*/
		//
		tm.getNetworkCountryIso();
		//
		tm.getNetworkOperator();
		//
		tm.getNetworkOperatorName();
		//
		tm.getNetworkType();
		/**
		 * PHONE_TYPE_NONE 无信号
		 * PHONE_TYPE_GSM GSM信号
		 * PHONE_TYPE_CDMA CDMA信号
		 */
		tm.getPhoneType();
		context +="phoneType:"+tm.getPhoneType()+"\n";
		//获取ISO的国家码,相当于提供SIM卡的国家码.
		tm.getSimCountryIso();
		context +="simCountryIso:"+tm.getSimCountryIso()+"\n";
		//获取SIM卡提供的移动国家码和移动网络码,5或6位的十进制数字.SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断)
		tm.getSimOperator();
		context +="tm.getSimOperator():"+tm.getSimOperator()+"\n";
		//服务商的名称 如：移动、联通、电信. SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断) 
		tm.getSimOperatorName();
		context +="tm.getSimOperatorName():"+tm.getSimOperatorName()+"\n";
		//SIM卡的序列号 READ_PHONE_STATE
		tm.getSimSerialNumber();
		context +="tm.getSimSerialNumber():"+tm.getSimSerialNumber()+"\n";
		/**
		 * SIM的状态信息
		 * SIM_STATE_UNKNOW 0 未知状态
		 * SIM_STATE_ABSENT 1 没插卡
		 * SIM_STATE_PIN_REQUIRED 2  锁定状态，需要用户的PIN码解锁 
		 * SIM_STATE_PUK_REQUIRED 3  锁定状态，需要用户的PUK码解锁 
		 * SIM_STATE_NETWORK_LOCKED 4  锁定状态，需要网络的PIN码解锁 
		 * SIM_STATE_READY 5 就绪状态
		 */
		tm.getSimState();
		context +="sim.state:"+tm.getSimState()+"\n";
		//唯一的用户ID. example：IMSI(国际移动用户识别码) for a GSM phone. READ_PHONE_STATE
		tm.getSubscriberId();
		context +="SubscriberId:"+tm.getSubscriberId()+"\n";
		//获取语音邮箱相关的标签，即为 识别符 READ_PHONE_STATE
		tm.getVoiceMailAlphaTag();
		//获取语音邮箱号码  READ_PHONE_STATE
		tm.getVoiceMailNumber();
		//ICC卡是否存在
		tm.hasIccCard();
		context +="hasIccCard:"+tm.hasIccCard()+"\n";
		//是否漫游(在GSM用途下)
		tm.isNetworkRoaming();
		context +="netWorkRoaming:"+tm.isNetworkRoaming()+"\n";
		context += "===================="+"\n";
		context += "MODEL:"+android.os.Build.MODEL+"\n";
		context += "VERSION.SDK_INT:"+android.os.Build.VERSION.SDK_INT+"\n";
		context += "VERSION.RELEASE:"+android.os.Build.VERSION.RELEASE+"\n";
		context += "VERSION.CODENAME:"+android.os.Build.VERSION.CODENAME+"\n";
		context += "CPU_ABI:"+android.os.Build.CPU_ABI+"\n";
		context += "MANUFACTURER:"+android.os.Build.MANUFACTURER+"\n";
		return context;
	}
	
}