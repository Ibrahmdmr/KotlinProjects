package com.example.xyz.filmleruygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xyz.filmleruygulamasi.databinding.ActivityDetayBinding
import com.example.xyz.filmleruygulamasi.databinding.ActivityMainBinding

class DetayActivity : AppCompatActivity() {


    private lateinit var tasarim :ActivityDetayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(tasarim.root)


        val film = intent.getSerializableExtra("filmName") as Filmler

        tasarim.textViewFilmAdDetay.text = film.film_Ad
        tasarim.textViewFilmYilDetay.text = (film.film_yil).toString()
        tasarim.textViewFilmYonetmenDetay.text = film.yonetmen_ad.yonetmen_ad

        tasarim.imageViewResimDetay.setImageResource(resources.getIdentifier(film.film_resim
            ,"drawable",packageName))

    }
}