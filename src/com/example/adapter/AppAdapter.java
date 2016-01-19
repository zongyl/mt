package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mt.R;

public class AppAdapter extends BaseAdapter{

	private Context context;
	
	private List<?> items;

	private LayoutInflater inflater; 
	
	public static class ViewHolder{
		public TextView title;//APP名称
		public TextView packageName;//包名称
		public ImageView icon;//APP ICON
		public Button more_btn;//更多按钮
	}
	
	public AppAdapter(Context context, List<?> items){
		this.context = context;
		this.items = items;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if(convertView == null){
			final PackageInfo info = (PackageInfo)items.get(position);
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.app_items, null);
			holder.packageName = (TextView) convertView.findViewById(R.id.app_text);
			holder.packageName.setText("qqqqqqqqqqq" + position);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}
}
