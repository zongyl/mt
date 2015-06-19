package com.example.gps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mt.R;
/** 
 * Google定位的实现.<br/> 
 * Geolocation的详细信息请参见：<br/> 
 * <a 
 * href="http://code.google.com/apis/gears/geolocation_network_protocol.html" mce_href="http://code.google.com/apis/gears/geolocation_network_protocol.html"> 
 * http://code.google.com/apis/gears/geolocation_network_protocol.html</a> 
 */  
public class LocationAct extends Activity {  
    private TextView txtInfo;  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.gps_main);  
        Button btn = (Button) findViewById(R.id.btnStart);  
        txtInfo = (TextView) findViewById(R.id.txtInfo);  
        btn.setOnClickListener(new Button.OnClickListener() {  
            public void onClick(View view) {  
                getLocation();  
            	//Toast.makeText(LocationAct.this, "click", 1000).show();
            }  
        });  
    }  
    private void getLocation() {  
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);  
        GsmCellLocation gsmCell = (GsmCellLocation) tm.getCellLocation();  
        int cid = gsmCell.getCid();  
        int lac = gsmCell.getLac();  
        String netOperator = tm.getNetworkOperator();  
        int mcc = Integer.valueOf(netOperator.substring(0, 3));  
        int mnc = Integer.valueOf(netOperator.substring(3, 5));  
        JSONObject holder = new JSONObject();  
        JSONArray array = new JSONArray();  
        JSONObject data = new JSONObject();  
        try {  
            holder.put("version", "1.1.0");  
            holder.put("host", "maps.google.com");  
            holder.put("address_language", "zh_CN");  
            holder.put("request_address", true);  
            holder.put("radio_type", "gsm");  
            holder.put("carrier", "HTC");  
            data.put("cell_id", cid);  
            data.put("location_area_code", lac);  
            data.put("mobile_countyr_code", mcc);  
            data.put("mobile_network_code", mnc);  
            array.put(data);  
            holder.put("cell_towers", array);  
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
        DefaultHttpClient client = new DefaultHttpClient();  
        HttpPost httpPost = new HttpPost("http://www.google.com/loc/json");  
        StringEntity stringEntity = null;  
        try {  
            stringEntity = new StringEntity(holder.toString());  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        httpPost.setEntity(stringEntity);  
        HttpResponse httpResponse = null;  
        try {  
            httpResponse = client.execute(httpPost);  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        HttpEntity httpEntity = httpResponse.getEntity();  
        InputStream is = null;  
        try {  
            is = httpEntity.getContent();  
        } catch (IllegalStateException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        InputStreamReader isr = new InputStreamReader(is);  
        BufferedReader reader = new BufferedReader(isr);  
        StringBuffer stringBuffer = new StringBuffer();  
        try {  
            String result = "";  
            while ((result = reader.readLine()) != null) {  
                stringBuffer.append(result);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        txtInfo.setText(stringBuffer.toString());  
    }
}