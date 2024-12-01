package com.example.duanmau.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.DataBase.DBHelper;

public class ThuThuDAO {
    DBHelper dbHelper;
    public ThuThuDAO(Context context){
        dbHelper = new DBHelper(context);
    }
    public boolean checkDangNhap(String matt, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{matt, matkhau});
        if (cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }
}
