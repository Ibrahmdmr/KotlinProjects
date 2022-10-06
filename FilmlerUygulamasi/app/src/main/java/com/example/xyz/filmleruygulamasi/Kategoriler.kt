package com.example.xyz.filmleruygulamasi

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable
@IgnoreExtraProperties
data class Kategoriler(var kategori_id:Int,var kategori_ad:String) : Serializable {
}