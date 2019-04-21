package com.example.qghomeworkapp;

/**
 * Created by 杨美端 on 2019/4/16.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


/**
 * Created by 杨美端 on 2019/4/16.
 */
public class Database extends SQLiteOpenHelper {

    public Context mContext;
    public static final String CRETE_BOOK = "create table Book("
            +"id integer primary key autoincrement,"
            +"English text)";

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRETE_BOOK);
        Toast.makeText(mContext,"数据库创建成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
