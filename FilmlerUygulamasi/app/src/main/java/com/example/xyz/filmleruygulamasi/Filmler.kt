package com.example.xyz.filmleruygulamasi

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable
@IgnoreExtraProperties
data class Filmler(
    var film_id:Int, var film_Ad:String, var film_yil:Int? = 0, var film_resim:String
    , var kategori_ad: Kategoriler
    , var yonetmen_ad: Yonetmenler
) : Serializable {
}