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

     sqLiteDatabase.execSQL("INSERT INTO LOAISACH VALUES (1, 'Thiếu nhi'),(2,'Tình Cảm'),(3,'Giáo Khoa')");
     sqLiteDatabase.execSQL("INSERT INTO SACH VALUES(1,'Hãy đợi đấy',2500,1),(2,'Chú cuội',1000,1),(3,'Lập Trình Android',2000,3)");
     //tai khoan mat khau
        sqLiteDatabase.execSQL("INSERT INTO THUTHU VALUES ('thuthu1','Thủ thư 1','abc123'),('sinh','Sùng Chí Sinh','sinh123')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THUTHU");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(sqLiteDatabase);
        }
    }
}
