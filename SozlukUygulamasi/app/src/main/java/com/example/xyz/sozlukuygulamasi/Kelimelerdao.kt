/* package com.example.xyz.sozlukuygulamasi

import android.content.Context

class Kelimelerdao {

    fun tumKelimeler(vt:VeritabaniYardimcisi) : ArrayList<Kelimeler>{

        val kelimeListesi = ArrayList<Kelimeler>()
        val db = vt.writableDatabase

        val c = db.rawQuery("SELECT * FROM kelimeler",null)

        while (c.moveToNext()){
            val kelime = Kelimeler(c.getInt(c.getColumnIndex("kelime_id"))
            ,c.getString(c.getColumnIndex("ingilizce"))
                ,c.getString(c.getColumnIndex("turkce")))

            kelimeListesi.add(kelime)
        }
        return kelimeListesi
    }

    fun aramaYap(vt:VeritabaniYardimcisi,aramaYap:String) : ArrayList<Kelimeler> {

        val kelimeListesi = ArrayList<Kelimeler>()
        val db = vt.writableDatabase

        val c = db.rawQuery("SELECT * FROM kelimeler WHERE ingilizce LIKE '%$aramaYap%'", null)

        while (c.moveToNext()) {
            val kelime = Kelimeler(
                c.getInt(c.getColumnIndex("kelime_id")),
                c.getString(c.getColumnIndex("ingilizce")),
                c.getString(c.getColumnIndex("turkce"))
            )

            kelimeListesi.add(kelime)
        }
        return kelimeListesi
    }

} */