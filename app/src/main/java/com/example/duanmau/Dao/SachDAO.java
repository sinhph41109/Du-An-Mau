package com.example.duanmau.Dao;

import android.content.Context;

import com.example.duanmau.DataBase.DBHelper;

public class SachDAO {
    DBHelper dbHelper;
    public SachDAO(Context context){
        dbHelper = new DBHelper(context);
    }
}
