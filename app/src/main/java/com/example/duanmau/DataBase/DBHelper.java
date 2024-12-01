package com.example.duanmau.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context,"DANGKYMONHOC",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
     String dbThuThu = "CREATE TABLE THUTHU(matt text primary key, hoten text, matkhau text)";
     sqLiteDatabase.execSQL(dbThuThu);

     String dbThanhVien = "CREATE TABLE THANHVIEN(matv integer primary key autoincrement, hoten text, namsinh text)";
     sqLiteDatabase.execSQL(dbThanhVien);

     String dbLoai = "CREATE TABLE LOAISACH(maloai integer primary key autoincrement, tenloai text)";
     sqLiteDatabase.execSQL(dbLoai);

     String dbSach ="CREATE TABLE SACH(masach integer primary key autoincrement, tensach text, giathue integer, maloai integer references LOAISACH(maloai))";
     sqLiteDatabase.execSQL(dbSach);

     String dbPhieuMuon = "CREATE TABLE PHIEUMUON(mapm integer primary key autoincrement,matv integer references THANHVIEN(matv), matt text references THUTHU(matt), masach integer references SACH(masach), ngay text, trasach integer, tienthue integer)";
     sqLiteDatabase.execSQL(dbPhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}