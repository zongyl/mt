package com.example.mt;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.adapter.VideoGalleryAdapter;
import com.example.bean.VideoViewInfo;

public class MediaStoreActivity extends Activity implements OnItemClickListener{

	Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mediastore);
		
		ListView listView = (ListView)this.findViewById(R.id.mediastoreListview);
		
		String[] thumColumns = {
				MediaStore.Video.Thumbnails.DATA,
				MediaStore.Video.Thumbnails.VIDEO_ID
		};
		
		String[] mediaColumns = {
				MediaStore.Video.Media._ID,
				MediaStore.Video.Media.DATA,
				MediaStore.Video.Media.TITLE,
				MediaStore.Video.Media.MIME_TYPE
		};
		
		cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, mediaColumns, 
				null, null, null); 
		
		ArrayList<VideoViewInfo> videoRows = new ArrayList<VideoViewInfo>();
		
		if(cursor.moveToFirst()){
			do {
				VideoViewInfo vvi = new VideoViewInfo();
				int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media._ID));
				Cursor thumCursor = getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
						thumColumns, MediaStore.Video.Thumbnails.VIDEO_ID + "=" +id, null, null);
				
				if(thumCursor.moveToFirst()){
					vvi.setThumPath(thumCursor.getString(thumCursor.getColumnIndex(MediaStore.Video.Media.DATA)));
				}
				vvi.setFilePath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
				vvi.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)));
				vvi.setMimeType(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)));
				
				videoRows.add(vvi);
			}while(cursor.moveToNext());
		}
		
		listView.setAdapter(new VideoGalleryAdapter(this, videoRows));
		listView.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> l, View v, int position, long id) {
		if(cursor.moveToPosition(position)){
			int fileColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
			int mimeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE);
			String videoFilePath = cursor.getString(fileColumn);
			String mimeType = cursor.getString(mimeColumn);
			Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(new File(videoFilePath)), mimeType);
			startActivity(intent);
		}
	}
}