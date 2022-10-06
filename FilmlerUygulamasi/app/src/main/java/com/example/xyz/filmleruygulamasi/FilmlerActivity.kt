package com.example.xyz.filmleruygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.xyz.filmleruygulamasi.databinding.ActivityFilmlerBinding
import com.example.xyz.filmleruygulamasi.databinding.ActivityMainBinding
import com.google.firebase.database.*

class FilmlerActivity : AppCompatActivity() {

    private lateinit var filmlerListe:ArrayList<Filmler>
    private lateinit var adapter: FilmlerAdapter
   // private lateinit var refFilmler: DatabaseReference

    private lateinit var vt:VeritabaniYardimcisi

    private lateinit var tasarim : ActivityFilmlerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityFilmlerBinding.inflate(layoutInflater)
        setContentView(tasarim.root)


        val kategori = intent.getSerializableExtra("kategoriName") as Kategoriler

        tasarim.toolbarFilmler.title = "Film : ${kategori.kategori_ad}"
        setSupportActionBar(tasarim.toolbarFilmler)

        tasarim.filmlerRv.setHasFixedSize(true)
        tasarim.filmlerRv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        vt = VeritabaniYardimcisi(this)

        filmlerListe = Filmlerdao().tumFilmlerByKaregoriId(vt,kategori.kategori_id)

        adapter = FilmlerAdapter(this,filmlerListe)

        tasarim.filmlerRv.adapter = adapter



    }



}