package com.example.yuekaole;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * author:Created by WangZhiQiang on 2018/4/7.
 */

public class MHalper extends SQLiteOpenHelper {
    //构造器 建立数据库
    public MHalper(Context context) {
        super(context, "lianxi3", null, 1);
    }
    //建表
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table json3(id integer primary key autoincrement,url text not null,json text not null )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
