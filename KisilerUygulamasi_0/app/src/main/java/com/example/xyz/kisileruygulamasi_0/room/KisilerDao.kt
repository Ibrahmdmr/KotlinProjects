/*package com.example.xyz.kisileruygulamasi_0.room

import androidx.room.*
import com.example.xyz.kisileruygulamasi_0.data.entity.Kisiler

@Dao
interface KisilerDao {

    @Query("SELECT * FROM kisiler")
   suspend fun tumKisileriAl() : List<Kisiler>

    @Query("SELECT * FROM kisiler WHERE kisi_ad LIKE '%' || :aramaKelime || '%'")
    suspend fun kisiArama(aramaKelime:String) : List<Kisiler>

    @Insert
    suspend fun kisiEkle(kisi:Kisiler)

    @Update
    suspend fun kisiGuncelle(kisi:Kisiler)

    @Delete
    suspend fun kisiSil(kisi:Kisiler)



} */