package com.example.xyz.bayrakquizuygulamasi

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VeritabaniYardimcisi(context: Context): SQLiteOpenHelper(context,"bayrakquiz.sqlite",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {                              // ? nul olduğunu temsil eder
        db?.execSQL("CREATE TABLE IF NOT EXISTS\"bayraklar\" (\n" +             //IF NOT EXISTS gerekli
                "\t`bayrak_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`bayrak_ad`\tTEXT,\n" +
                "\t`bayrak_resim`\tTEXT\n" +
                ")")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { // bir hata olursa çalışır
        db?.execSQL("DROP TABLE IF EXISTS bayraklar")
        onCreate(db)
    }
}