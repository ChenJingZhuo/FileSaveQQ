package com.cjz.filesaveqq.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cjz.filesaveqq.bean.MyQQ;

import java.util.ArrayList;
import java.util.List;

/**
 *  author: ChenJingZhuo
 *  date: 2019/11/22
 */

public class MySQL extends SQLiteOpenHelper {

    private Context context;
    private MySQL mySQL;
    private SQLiteDatabase db;

    public MySQL(Context context){
        super(context,"cjz.db",null,2);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table information(_id integer primary key autoincrement,account varchar(8),password varchar(8))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(String account,String password){
        mySQL=new MySQL(context);
        db=mySQL.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("account",account);
        values.put("account",password);
        long isSave=db.insert("information",null,values);
        db.close();
        return isSave;
    }

    public void delete(){
        mySQL=new MySQL(context);
        db=mySQL.getWritableDatabase();
        db.execSQL("delete from information");
        db.close();
    }

    public void update(String account,String password){
        mySQL=new MySQL(context);
        db=mySQL.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("password",password);
        db.update("information",values,"name=?",new String[]{account});
        db.close();
    }

    public List<MyQQ> find(int id){
        List<MyQQ> myQQList=new ArrayList<>();
        mySQL=new MySQL(context);
        db=mySQL.getReadableDatabase();
        Cursor cursor=db.query("information",null,"_id=?",new String[]{id+""},null,null,null);
        if (cursor.getCount()!=0){
            while (cursor.moveToNext()){
                int _id=cursor.getInt(cursor.getColumnIndex("_id"));
                String account=cursor.getString(cursor.getColumnIndex("account"));
                String password=cursor.getString(cursor.getColumnIndex("password"));
                MyQQ myQQ=new MyQQ(_id,account,password);
                myQQList.add(myQQ);
            }
        }
        cursor.close();//关闭游标
        db.close();
        return myQQList;
    }
}
