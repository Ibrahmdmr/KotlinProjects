package com.example.xyz.bayrakquizuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xyz.bayrakquizuygulamasi.databinding.ActivityMainBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity() {
    private lateinit var tasarim :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityMainBinding.inflate(layoutInflater)
        setContentView(tasarim.root)


        copyVeriTabani()

        tasarim.buttonBasla.setOnClickListener {

            startActivity(Intent(this@MainActivity,QuizActivity::class.java))

        }
    }

    fun copyVeriTabani() {
        val copyHelper = DatabaseCopyHelper(this)

    try {
       copyHelper.createDataBase()
       copyHelper.openDataBase()
    }catch (e:Exception){
        e.printStackTrace()
       }
    }

}