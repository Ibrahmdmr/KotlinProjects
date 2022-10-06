package com.example.xyz.filmleruygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xyz.filmleruygulamasi.databinding.ActivityMainBinding
import com.google.firebase.database.*
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity() {

    private lateinit var kategoriListe:ArrayList<Kategoriler>
    private lateinit var adapter: KategorilerAdapter
    //private lateinit var refKategoriler: DatabaseReference

    private lateinit var vt:VeritabaniYardimcisi

    private lateinit var tasarim :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityMainBinding.inflate(layoutInflater)
        setContentView(tasarim.root)

        veritabaniCopy()

        tasarim.toolbarKategori.title = "Kategoriler"
        setSupportActionBar(tasarim.toolbarKategori)

        tasarim.kategoriRv.setHasFixedSize(true)
        tasarim.kategoriRv.layoutManager = LinearLayoutManager(this)

        vt = VeritabaniYardimcisi(this)

        kategoriListe = Kategorilerdao().tumKategoriler(vt)

        adapter = KategorilerAdapter(this,kategoriListe)

        tasarim.kategoriRv.adapter = adapter



    }

    fun veritabaniCopy(){
        val copyHelper = DatabaseCopyHelper(this)

        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }


}