package com.example.yuekaole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * author:Created by WangZhiQiang on 2018/4/7.
 */

class JsonDao {


    private final MHalper mHalper;
    private SQLiteDatabase db;
    //构造器
    public JsonDao(Context context){
        mHalper = new MHalper(context);
    }
    //添加进数据库
    public void insertData(String url,String json){
        db = mHalper.getWritableDatabase();
//先删除  再添加
        db.delete("json3","url=?",new String[]{json});
        ContentValues values = new ContentValues();
        values.put("url",url);
        values.put("json",json);
        db.insert("json3",null,values);
    }
    //查找
    public String queryData(String url){
        String json="";
        SQLiteDatabase db = mHalper.getWritableDatabase();
        Cursor cursor = db.query("json3", null, "url=?", new String[]{url}, null, null, null);
        while (cursor.moveToNext()){
            json=cursor.getString(cursor.getColumnIndex("json"));
        }
        return json;
    }

}
