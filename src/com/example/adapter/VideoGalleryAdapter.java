package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.VideoViewInfo;
import com.example.mt.R;

/**
 *  视频列表适配器
 * @author zongyl
 *
 */
public class VideoGalleryAdapter extends BaseAdapter {

	private Context context;
	
	private List<VideoViewInfo> videoItems;
	
	LayoutInflater inflater; 
	
	public VideoGalleryAdapter(Context _context, List<VideoViewInfo> _items){
		context = _context;
		videoItems = _items;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	
	@Override
	public int getCount() {
		return videoItems.size();
	}

	@Override
	public Object getItem(int index) {
		return videoItems.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View videoRow = inflater.inflate(R.layout.mediastore_list_item, null);
		
		ImageView videoThumb = (ImageView)videoRow.findViewById(R.id.listItemImageView);
		if(videoItems.get(position).getThumPath() != null){
			videoThumb.setImageURI(Uri.parse(videoItems.get(position).getThumPath()));
		}
		TextView videoTitle = (TextView)videoRow.findViewById(R.id.listItemTextView);
		videoTitle.setText(videoItems.get(position).getTitle());
		
		return videoRow;
	}

}