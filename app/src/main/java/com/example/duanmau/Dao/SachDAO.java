package com.example.duanmau.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duanmau.DataBase.DBHelper;
import com.example.duanmau.Model.Sach;

import java.util.ArrayList;

public class SachDAO {
    DBHelper dbHelper;
    public SachDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<Sach> getDSDauSach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteOpenHelper = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteOpenHelper.rawQuery("SELECT * FROM SACH", null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do{
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));
            }while(cursor.moveToNext());
        }
        return list;
    }
}
