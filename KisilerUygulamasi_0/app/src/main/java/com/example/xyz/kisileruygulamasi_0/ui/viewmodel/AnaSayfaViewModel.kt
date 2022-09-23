package com.example.xyz.kisileruygulamasi_0.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xyz.kisileruygulamasi_0.data.entity.Kisiler
import com.example.xyz.kisileruygulamasi_0.data.repo.KisilerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnaSayfaViewModel @Inject constructor (var krepo : KisilerDaoRepository): ViewModel() {

    var kisilerListe = MutableLiveData<List<Kisiler>>()

    init {
        kisilerYukle()
        kisilerListe = krepo.kisileriGetir()
    }

    fun ara(aramaKelimesi:String){
        krepo.kisiAra(aramaKelimesi)
    }

    fun sil(kisi_id:String){
        krepo.kisiSil(kisi_id)
    }

    fun kisilerYukle(){
        krepo.tumKisileriAl()
    }

}