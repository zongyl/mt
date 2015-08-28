package com.example.utils;

import android.provider.BaseColumns;

public final class TestBean {

	public TestBean(){}
	
	public static abstract class Book implements BaseColumns{
		public static final String TABLE_NAME = "book";
		public static final String COLUMN_NAME_ID = "id";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_SUBJECT = "subject";
	}
}
