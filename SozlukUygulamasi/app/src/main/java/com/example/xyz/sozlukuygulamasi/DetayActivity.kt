package com.example.xyz.sozlukuygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xyz.sozlukuygulamasi.databinding.ActivityDetayBinding
import com.example.xyz.sozlukuygulamasi.databinding.ActivityMainBinding

class DetayActivity : AppCompatActivity() {
    private lateinit var tasarim : ActivityDetayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(tasarim.root )


        val kelime = intent.getSerializableExtra("nesne") as Kelimeler

        tasarim.textViewDetayIngilizce.text = kelime.ingilizce
        tasarim.textViewDetayTurkce.text = kelime.turkce


    }
}