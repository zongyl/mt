package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mt.R;

public class PhoneNumberAdapter extends BaseAdapter {

	private Context context;
	
	private List pnItems;
	
	LayoutInflater inflater;
	
	public PhoneNumberAdapter(Context _context, List _items){
		context = _context;
		pnItems = _items;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return pnItems.size();
	}

	@Override
	public Object getItem(int position) {
		return pnItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = inflater.inflate(R.layout.phonenumber_list_item, null);
		TextView tv = (TextView)rowView.findViewById(R.id.pn_listItemTextView);
		tv.setText(pnItems.get(position).toString());
		return rowView;
	}

}