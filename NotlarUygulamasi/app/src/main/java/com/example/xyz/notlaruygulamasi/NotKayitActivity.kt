package com.example.xyz.notlaruygulamasi

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.xyz.notlaruygulamasi.databinding.ActivityNotKayitBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NotKayitActivity : AppCompatActivity() {

   // private lateinit var vt:VeritabaniYardimcisi
    private lateinit var refNotlar:DatabaseReference

    private lateinit var tasarim : ActivityNotKayitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityNotKayitBinding.inflate(layoutInflater)
        setContentView(tasarim.root)

        tasarim.toolbarNotKayit.title = "Not Kayıt"
        setSupportActionBar(tasarim.toolbarNotKayit)

      //  vt = VeritabaniYardimcisi(this)

        val db = FirebaseDatabase.getInstance() // izin alma
        refNotlar = db.getReference("notlar")

        tasarim.buttonKaydet.setOnClickListener {

            val ders_adi = tasarim.editTextTextDers.text.toString().trim()
            val not1 = tasarim.editTextNot1.text.toString().trim()
            val not2 = tasarim.editTextNot2.text.toString().trim()

            if (TextUtils.isEmpty(ders_adi)){
                Snackbar.make(tasarim.toolbarNotKayit,"Ders adını giriniz",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(not1)){
                Snackbar.make(tasarim.toolbarNotKayit,"1. Notu giriniz",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(not2)){
                Snackbar.make(tasarim.toolbarNotKayit,"2. Notu giriniz",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val not = Notlar("",ders_adi,not1.toInt(),not2.toInt())

            refNotlar.push().setValue(not)

            startActivity( Intent(this@NotKayitActivity,MainActivity::class.java))
            finish()
        }

    }
}